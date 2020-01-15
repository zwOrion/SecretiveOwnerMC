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
     * @param event 方块模型注册事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:46
     * 注册方块模型事件
     */
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        registryModel(BlockLoader.grassBlock);
    }

    /**
     * @param block 方块实体
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:47
     * 加载和处理方块模型
     */
    private static void registryModel(Block block) {
        //获取方块模型资源路径(方块的注册名，固定字符串)（src/main/resources/assets/fmltutor/blockstates/<方块id>.json）
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(block.getRegistryName(), "inventory");
        //加载方块资源模型
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
    }
}
