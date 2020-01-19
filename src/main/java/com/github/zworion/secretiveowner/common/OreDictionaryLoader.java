package com.github.zworion.secretiveowner.common;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 20:09
 */
public class OreDictionaryLoader {
    public OreDictionaryLoader() {
        //将红石和荧石粉的矿词互通
        List<ItemStack> dustRedstones = OreDictionary.getOres("dustRedstone");
        List<ItemStack> dustGlowstones = OreDictionary.getOres("dustGlowstone");
        for (ItemStack itemStack : dustGlowstones) {
            OreDictionary.registerOre("dustRedstone", itemStack);
        }
        for (ItemStack itemStack : dustRedstones) {
            OreDictionary.registerOre("dustGlowstone", itemStack);
        }
        //注册矿物辞典
        oreDictionaryRegister();
    }
    public void oreDictionaryRegister(){
        //注册草植物的矿物辞典，包含所有的草
        OreDictionary.registerOre("glassPlant", new ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE));
    }
}
