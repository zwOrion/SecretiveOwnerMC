package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.SecretiveOwner;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 方块的模型注册类
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
     * 注册方块事件
     *
     * @param event 事件
     */
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        registryModel(BlockLoader.grassBlock);
    }

    /**
     * 加载和处理方块模型
     *
     * @param block 方块
     */
    private static void registryModel(Block block) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(block.getRegistryName(), "inventory");

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
