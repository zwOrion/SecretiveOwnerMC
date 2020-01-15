package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 红石镐
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/06
 */
public class ItemRedstonePickaxe extends ItemPickaxe {

    /**
     * 红石镐参数(工具等级，耐久，效率，力度，附魔等级值)
     */
    public static final Item.ToolMaterial REDSTONE = EnumHelper.addToolMaterial("REDSTONE", 3, 16, 16.0f, 0.0f, 10);

    public ItemRedstonePickaxe() {
        //设置红石镐各项参数
        super(REDSTONE);
        //设置国际化名的键名
        this.setTranslationKey("secretiveowner.redStonePickaxe");
        //设置创造模式物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //设置红石镐注册名
        this.setRegistryName("secretiveowner", "redstone_pickaxe");
    }
}
