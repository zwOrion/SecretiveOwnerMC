package com.github.zworion.secretiveowner.creativetab;

import com.github.zworion.secretiveowner.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class SOCreativeTab {
    public static final CreativeTabs SO_TAB = new CreativeTabs("secretiveowner.so_tab") {
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.goldenEgg);
        }
    };
}
