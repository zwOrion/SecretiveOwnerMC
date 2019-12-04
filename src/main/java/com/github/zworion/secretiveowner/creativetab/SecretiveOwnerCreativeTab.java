package com.github.zworion.secretiveowner.creativetab;

import com.github.zworion.secretiveowner.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 创造模式物品栏
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
public final class SecretiveOwnerCreativeTab {

    /**
     * 一个物品栏的实例
     */
    public static final CreativeTabs SO_TAB = new CreativeTabs("secretiveowner.so_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.goldenEgg);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImageName("secretiveowner.png");
}


//~ Formatted by Jindent --- http://www.jindent.com
