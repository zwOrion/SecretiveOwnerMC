package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.item.Item;

import net.minecraft.item.ItemArmor;
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
     * 红石镐
     */
    public static Item redStonePickAxe = new ItemRedstonePickaxe();

    /**
     * 红石苹果
     */
    public static Item redStoneApple = new ItemRedstoneApple();
    /**
     * 红石头盔
     */
    public static ItemArmor redStoneHelmet = new ItemRedstoneArmor.Helmet();
    /**
     * 红石胸甲
     */
    public static ItemArmor redStoneChestplate = new ItemRedstoneArmor.Chestplate();
    /**
     * 红石护腿
     */
    public static ItemArmor redStoneLeggings = new ItemRedstoneArmor.Leggings();
    /**
     * 红石靴子
     */
    public static ItemArmor redStoneBoots = new ItemRedstoneArmor.Boots();
    /**
     * 水银桶
     */
    public static ItemBucketMercury bucketMercury = new ItemBucketMercury();

    /**
     * 物品注册事件
     *
     * @param event 事件
     */
    /**
     * @param event 物品注册事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 23:29
     * 注册物品的类
     */
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        SecretiveOwner.logger.info("注册物品 >> {}", goldenEgg.getRegistryName());
        //注册金蛋
        event.getRegistry().register(goldenEgg);
        //注册红石镐
        event.getRegistry().register(redStonePickAxe);
        //注册红石苹果
        event.getRegistry().register(redStoneApple);
        //注册红石头盔
        event.getRegistry().register(redStoneHelmet);
        //注册红石胸甲
        event.getRegistry().register(redStoneChestplate);
        //注册红石护腿
        event.getRegistry().register(redStoneLeggings);
        //注册红石靴子
        event.getRegistry().register(redStoneBoots);
        //注册水银桶
        event.getRegistry().register(bucketMercury);
    }
}

