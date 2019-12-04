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

    private static CraftingLoader craftingLoader = new CraftingLoader();

    private CraftingLoader() {
    }


    public static CraftingLoader instance() {
        return craftingLoader;
    }

    /**
     * 注册熔炼规则
     */
    public void registerSmelting() {
        GameRegistry.addSmelting(BlockLoader.grassBlock, new ItemStack(ItemLoader.goldenEgg), 0.5F);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
