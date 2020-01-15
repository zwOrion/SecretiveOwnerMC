package com.github.zworion.secretiveowner.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/13 19:52
 * 事件注册类
 */
public class EventLoader {
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
        EventLoader.EVENT_BUS.register(this);
    }

    /**
     * 实例化一个EventBus
     */
    public static final EventBus EVENT_BUS = new EventBus();

    /**
     * @author ZWOrion
     * @date 2020/1/15 13:48
     * 玩家点击草方块事件
     */
    @Cancelable
    public static class PlayerRightClickGrassBlockEvent extends PlayerEvent {
        public final BlockPos pos;
        public final World world;

        public PlayerRightClickGrassBlockEvent(EntityPlayer player, BlockPos pos, World world) {
            super(player);
            this.pos = pos;
            this.world = world;
        }
    }

    /**
     * @param event 玩家右击事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/15 13:50
     * 订阅玩家点击草块事件
     */
    @SubscribeEvent
    public void onPlayerClickGrassBlock(PlayerRightClickGrassBlockEvent event) {
        if (!event.world.isRemote) {
            BlockPos pos = event.pos;
            Entity tnt = new EntityTNTPrimed(event.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, null);
            event.world.spawnEntity(tnt);
        }
    }

    /**
     * @param event 玩家与实体交互事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/15 13:51
     * 订阅玩家给猪喂食小麦或者小麦种子事件
     */
    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        //获取事件玩家实体
        EntityPlayer player = event.getEntityPlayer();
        //判断交互对象是否是猪
        if (event.getTarget() instanceof EntityPig) {
            //获取猪实体的信息
            EntityPig pig = (EntityPig) event.getTarget();
            //获取玩家主手中的物品
            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            //判断玩家手中物品是否是小麦或者小麦种子
            boolean b = (stack.areCapsCompatible(new ItemStack(Items.WHEAT)) || stack.areCapsCompatible(new ItemStack(Items.WHEAT_SEEDS)));
            if (b) {
                //设置玩家伤害来源
                //伤害来源于猪的爆炸
                player.attackEntityFrom((new DamageSource("byPig")).setDifficultyScaled().setExplosion(), 25.0F);
                //在交互猪实体位置生成爆炸
                player.world.createExplosion(pig, pig.posX, pig.posY, pig.posZ, 100.0F, false);
                //猪死亡
                pig.setDead();
            }
        }
    }
}
