package com.github.zworion.secretiveowner.entity;

import com.github.zworion.secretiveowner.world.storage.loot.LootLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 12:58
 * 黄金鸡
 */
public class EntityGoldenChicken extends EntityChicken {
    public static final IAttribute wingSpeed = new RangedAttribute(null, "secretiveowner.GoldenChicken.wingSpeed", 1.5D, 0.0D, 4.0D).setDescription("Wing Speed").setShouldWatch(true);
    public static final DataParameter dataParameter = EntityDataManager.createKey(EntityGoldenChicken.class, DataSerializers.BYTE);

    public EntityGoldenChicken(World worldIn) {
        super(worldIn);
        //设置实体的碰撞箱大小
        this.setSize(1.2F, 1.75F);
        //注册实体AI
        this.tasks.addTask(8, new AiStackBlock(this));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }


    /**
     * @return net.minecraft.util.ResourceLocation
     * @author ZWOrion
     * @date 2020/1/23 23:42
     * 设置掉落物品战利品表
     * 不知道是1.12.2的坑，教程用dropFewItems()可以掉落
     */
    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootLoader.ENTITES_GOLDENCHICKEN;
    }

    /**
     * @return void
     * @author ZWOrion
     * @date 2020/1/25 14:32
     * 生物实体属性
     */
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(wingSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(wingSpeed).setBaseValue(1 + this.rand.nextDouble());
    }

    /**
     * @return void
     * @author ZWOrion
     * @date 2020/1/25 14:33
     * 实体初始化
     */
    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataManager.register(dataParameter, new Byte((byte) 0));
    }

    /**
     * @param player
     * @param hand
     * @return boolean
     * @author ZWOrion
     * @date 2020/1/25 14:33
     * 实体交互
     */
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!super.processInteract(player, hand)) {
            Byte b = (Byte) this.dataManager.get(dataParameter);
            this.dataManager.set(dataParameter, new Byte((byte) ((b + 1) % 5)));
        }
        return true;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     *
     * @param compound
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.dataManager.set(dataParameter, compound.getByte("WingSpeedMultiplier"));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     *
     * @param compound
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("WingSpeedMultiplier", (Byte) this.dataManager.get(dataParameter));
    }

    /**
     * @author ZWOrion
     * @version 1.0.0
     * @date 2020/1/25 18:27
     * 设置黄金鸡的自定义AI
     */
    protected static class AiStackBlock extends EntityAIBase {
        //鸡实体
        private final EntityGoldenChicken entity;

        public AiStackBlock(EntityGoldenChicken entity) {
            this.entity = entity;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask() {
            //获取实体位置
            BlockPos entityPos = entity.getPosition();
            //判断鸡周围是否是空气
            if (entity.world.isAirBlock(entityPos.up())) {
                BlockPos pos1 = new BlockPos(entityPos.getX() - 20, entityPos.getY() - 20, entityPos.getZ() - 20);
                BlockPos pos2 = new BlockPos(entityPos.getX() + 20, entityPos.getY() + 20, entityPos.getZ() + 20);
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(pos1, pos2);
                for (Object e : entity.world.getEntitiesWithinAABB(EntityItem.class, axisAlignedBB)) {
                    ItemStack stack = ((EntityItem) e).getItem();
                    Block block = Block.getBlockFromItem(stack.getItem());
                    if (block != null) {
                        entity.setLocationAndAngles(entity.posX, entity.posY + 1, entity.posZ, entity.rotationYaw, entity.rotationPitch);
                        entity.world.setBlockState(entityPos, block.getDefaultState());
                        stack.setCount(stack.getCount() - 1);
                        if (stack.getCount() <= 0) {
                            ((EntityItem) e).setDead();
                        }
                    }
                }
            }
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return this.entity.world.getGameRules().getBoolean("mobGriefing");
        }
    }
}
