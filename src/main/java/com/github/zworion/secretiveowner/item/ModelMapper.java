package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.SecretiveOwner;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 物品模型注册类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
@Mod.EventBusSubscriber(
        value = Side.CLIENT,
        modid = SecretiveOwner.MODID
)
public final class ModelMapper {

    /**
     * 注册物品模型
     *
     * @param event 事件
     */
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        registryModel(ItemLoader.goldenEgg);
    }

    /**
     * 加载、处理物品模型
     *
     * @param item 物品
     */
    private static void registryModel(Item item) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");

        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
