package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.SecretiveOwner;

import com.github.zworion.secretiveowner.fluid.FluidLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        SecretiveOwner.logger.info("加载方块模型事件 >> {}","");
        rigisterStateMapper(BlockLoader.metalFurnaceBlock, new StateMap.Builder().withName(BlockMetalFurnace.MATERIAL).withSuffix("_furnace").build());
        registryModel(BlockLoader.grassBlock);
        //注册流体模型
        FluidLoader.registerRenders();
        //加载铁炉模型
        registryModel(BlockLoader.metalFurnaceBlock, 0, "secretiveowner:iron_furnace");
        //加载金炉模型
        registryModel(BlockLoader.metalFurnaceBlock, 8, "secretiveowner:gold_furnace");
    }

    /**
     * @param block 方块实体
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:47
     * 加载和处理方块模型
     */
    private static void registryModel(Block block) {
        SecretiveOwner.logger.info("加载方块材质模型 >> {}", block.getRegistryName());
        //获取方块模型资源路径(方块的注册名，固定字符串)（src/main/resources/assets/fmltutor/blockstates/<方块id>.json）
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(block.getRegistryName(), "inventory");
        //加载方块资源模型
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, modelResourceLocation);
    }
    /**
     * @param block 方块实体
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:47
     * 加载和处理方块模型
     */
    @SideOnly(Side.CLIENT)
    private static void registryModel(Block block,int metadata,String name) {
        SecretiveOwner.logger.info("加载方块材质模型 >> {}", block.getRegistryName());
        //获取方块模型资源路径(方块的注册名，固定字符串)（src/main/resources/assets/fmltutor/blockstates/<方块id>.json）
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(name, "inventory");
        //加载方块资源模型
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, modelResourceLocation);
    }
    private static void rigisterStateMapper(Block block, IStateMapper mapper){
        ModelLoader.setCustomStateMapper(block, mapper);
    }
}
