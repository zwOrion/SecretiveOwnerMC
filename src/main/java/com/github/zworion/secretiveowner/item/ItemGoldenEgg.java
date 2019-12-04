package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import net.minecraft.item.Item;

/**
 * 金蛋
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
public class ItemGoldenEgg extends Item {

    /**
     * 设置金蛋的相关属性
     */
    public ItemGoldenEgg() {
        super();
        this.setTranslationKey("secretiveowner.goldenEgg")
                .setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB)
                .setRegistryName("secretiveowner", "golden_egg");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
