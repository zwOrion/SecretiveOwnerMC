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
     * 一个物品栏的实例,并设置其背景图(src\main\resources\assets\minecraft\textures\gui\container\creative_inventory)
     */
    public static final CreativeTabs SO_TAB = new CreativeTabs("secretiveowner.so_tab") {
        /**
         *
         * @author ZWOrion
         * @date 2020/1/8 23:12
         *  设置物品栏的图标
         * @return net.minecraft.item.ItemStack
         */
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.goldenEgg);
        }

        /**
         *
         * @author ZWOrion
         * @date 2020/1/8 23:13
         *  设置搜索框
         * @return boolean
         */
        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImageName("secretiveowner.png");
}

