package com.github.zworion.secretiveowner.item;

import net.minecraft.item.Item;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 物品的注册类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
@Mod.EventBusSubscriber(modid = "secretiveowner")
public final class ItemLoader {

    /**
     * 金蛋
     */
    public static Item goldenEgg = new ItemGoldenEgg();

    /**
     * 物品注册事件
     *
     * @param event 事件
     */
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(goldenEgg);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
