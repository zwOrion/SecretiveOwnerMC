package com.github.zworion.secretiveowner.crafting;

import com.github.zworion.secretiveowner.block.BlockLoader;
import com.github.zworion.secretiveowner.item.ItemLoader;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 熔炉熔炼规则
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/03
 */
public class CraftingLoader {
    /**
     * 实例化私有对象，单例模式
     */
    private static CraftingLoader craftingLoader = new CraftingLoader();

    /**
     * @return
     * @author ZWOrion
     * @date 2020/1/8 23:04
     * 私有化构造方法
     */
    private CraftingLoader() {
    }

    /**
     * @return com.github.zworion.secretiveowner.crafting.CraftingLoader
     * @author ZWOrion
     * @date 2020/1/8 23:05
     * 提供公有方法供其他类获得 CraftingLoader 对象
     */
    public static CraftingLoader instance() {
        return craftingLoader;
    }

    /**
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 23:06
     * 注册熔炼规则
     */
    public void registerSmelting() {
        //注册熔炼规则(待烧炼物品，烧炼后物品，经验值)
        GameRegistry.addSmelting(BlockLoader.grassBlock, new ItemStack(ItemLoader.goldenEgg), 0.5F);
    }
}

