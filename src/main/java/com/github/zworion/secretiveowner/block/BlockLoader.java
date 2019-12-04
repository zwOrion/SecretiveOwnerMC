package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.SecretiveOwner;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
     * 该方法用于注册方块
     *
     * @param event 事件
     */
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(grassBlock);
    }

    /**
     * 注册方块的物品形式
     *
     * @param event 事件
     */
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(getBlockItem(grassBlock, "secretiveowner", "grass_block", 1048576));
    }

    /**
     * 获得方块的物品形式
     *
     * @param block 方块
     * @param modId modDD
     * @param name  方块名
     * @return Item 方块的物品形式
     */
    private static Item getBlockItem(Block block, String modId, String name) {
        Item item = new ItemBlock(block).setRegistryName(modId, name);

        return item;
    }

    /**
     * 获得方块的物品形式
     * 可燃烧
     *
     * @param block    方块
     * @param modId    modID
     * @param name     名字
     * @param burnTime 烧炼时间
     * @return
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


//~ Formatted by Jindent --- http://www.jindent.com
