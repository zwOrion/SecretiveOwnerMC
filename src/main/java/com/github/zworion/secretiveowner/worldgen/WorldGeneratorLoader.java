package com.github.zworion.secretiveowner.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 16:44
 */
public class WorldGeneratorLoader {
    /**
     * 实例化 WorldGeneratorGlowstone 对象
     */
    private static WorldGenerator generatorGlowstone = new WorldGeneratorGlowstone();
    /**
     * 声明位置
     */
    private static BlockPos pos;

    public WorldGeneratorLoader() {
        //注册事件
        MinecraftForge.ORE_GEN_BUS.register(WorldGeneratorLoader.class);
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/19 18:47
     * 矿物生成结束事件
     * @param event
     * @return void
     */
    @SubscribeEvent
    public static void onOreGenPost(OreGenEvent.Post event) {
        //判断矿物生成的区块是否是已经记录过的区块
        if (!event.getPos().equals(pos)) {
            //获取当前区块
            pos = event.getPos();
            //生成荧石矿物
            generatorGlowstone.generate(event.getWorld(), event.getRand(), event.getPos());

        }
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/19 18:49
     * 矿物生成事件
     * @param event
     * @return void
     */
    @SubscribeEvent
    public static void onOreGenGenerateMinAble(OreGenEvent.GenerateMinable event) {
        //判断生成的矿物是否是安山岩
        if (event.getType() == OreGenEvent.GenerateMinable.EventType.ANDESITE) {
            //拒绝安山岩生成
            event.setResult(Event.Result.DENY);
        }
    }
}
