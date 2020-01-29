package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.SecretiveOwner;

import com.github.zworion.secretiveowner.block.fluid.BlockFluidMercury;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.GameData;

/**
 * 方块注册类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public final class BlockLoader {
    /**
     * 草方块
     */
    public static Block grassBlock = new BlockGrassBlock();
    /**
     * 水银液体对应方块
     */
    public static Block fluidMercuryBlock = new BlockFluidMercury();
    /**
     * 金属炉
     */
    public static Block metalFurnaceBlock = new BlockMetalFurnace();
    /**
     * 金属炉对应物品方块
     */
    public static ItemBlock metalFurnaceItemBlock = new ItemMultiTexture(metalFurnaceBlock, metalFurnaceBlock, new ItemMultiTexture.Mapper() {
        @Override
        public String apply(ItemStack var1) {
            return BlockMetalFurnace.EnumMaterial.values()[var1.getMetadata() >> 3].getName();
        }
    });

    /**
     * @param event 方块注册事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:23
     * 该方法用于注册方块
     */
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {

        SecretiveOwner.logger.info("注册方块 >> {}", grassBlock.getRegistryName());
        //注册草方块
        event.getRegistry().register(grassBlock);

        SecretiveOwner.logger.info("注册方块 >> {}", fluidMercuryBlock.getRegistryName());
        //注册水银方块
        event.getRegistry().register(fluidMercuryBlock);
        SecretiveOwner.logger.info("注册方块 >> {}", metalFurnaceBlock.getRegistryName());
        //注册金属炉方块
        event.getRegistry().register(metalFurnaceBlock);
    }

    /**
     * @param event 方块的物品形式注册事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:24
     * 注册方块的物品形式
     */
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        SecretiveOwner.logger.info("注册方块物品形式 >> {}", grassBlock.getRegistryName());
        //注册草方块的物品形式
        event.getRegistry().register(getBlockItem(grassBlock, "secretiveowner", "grass_block", 1048576));
        //注册流体的物品形式
        event.getRegistry().register(getBlockItem(fluidMercuryBlock, "secretiveowner", "fluid_Mercury"));
        //注册金属炉的物品形式
        event.getRegistry().register(metalFurnaceItemBlock.setRegistryName("metal_furnace"));
        //绑定金属炉和其对应物品方块的数据
        GameData.getBlockItemMap().put(metalFurnaceBlock, metalFurnaceItemBlock);
    }

    /**
     * @param block 方块实体
     * @param modId modID
     * @param name  方块名
     * @return net.minecraft.item.Item 方块的物品形式
     * @author ZWOrion
     * @date 2020/1/8 22:25
     * 获得方块的物品形式
     */
    private static Item getBlockItem(Block block, String modId, String name) {
        Item item = new ItemBlock(block).setRegistryName(modId, name);

        return item;
    }

    /**
     * @param block    方块
     * @param modId    modID
     * @param name     名字
     * @param burnTime 烧炼时间
     * @return net.minecraft.item.Item
     * @author ZWOrion
     * @date 2020/1/8 22:27
     * 获得可作为燃料的方块的物品形式
     */
    private static Item getBlockItem(Block block, String modId, String name, int burnTime) {
        Item item = new ItemBlock(block) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return burnTime;
            }
        }.setRegistryName(modId, name);

        return item;
    }
}
