package com.github.zworion.secretiveowner.entity;

import com.github.zworion.secretiveowner.item.ItemLoader;
import com.github.zworion.secretiveowner.world.storage.loot.LootLoader;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
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

    public EntityGoldenChicken(World worldIn) {
        super(worldIn);
        //设置实体的碰撞箱大小
        this.setSize(1.2F, 1.75F);
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

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(wingSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(wingSpeed).setBaseValue(1 + this.rand.nextDouble());
    }
}
