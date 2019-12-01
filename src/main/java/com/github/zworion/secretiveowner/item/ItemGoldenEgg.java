package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.creativetab.SOCreativeTab;
import net.minecraft.item.Item;

public class ItemGoldenEgg extends Item {
    public ItemGoldenEgg() {
        super();
        this.setTranslationKey("secretiveowner.goldenEgg").setCreativeTab(SOCreativeTab.SO_TAB).setRegistryName("secretiveowner","golden_egg");
    }
}
