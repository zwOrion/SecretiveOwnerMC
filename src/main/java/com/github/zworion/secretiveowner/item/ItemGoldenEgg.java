package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.config.DefineConfig;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 金蛋
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
public class ItemGoldenEgg extends Item {

    /**
     * @param itemStack 物品ItemStack
     * @return the fuel burn time for this itemStack in a furnace.
     * Return 0 to make it not act as a fuel.
     * Return -1 to let the default vanilla logic decide.
     */
    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return DefineConfig.diamondBurnTime;
    }

    /**
     * 设置金蛋的相关属性
     */
    public ItemGoldenEgg() {
        super();
        this
                //设置国际化名的键名
                .setTranslationKey("secretiveowner.goldenEgg")
                //设置创造物品栏
                .setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB)
                //设置注册名
                .setRegistryName("secretiveowner", "golden_egg");

    }
}
