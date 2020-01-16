package com.github.zworion.secretiveowner.enchantment;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/15 19:33
 * 燃烧附魔类
 */
public class EnchantmentFireBurn extends Enchantment {
    /**
     * 玩家实体的手部位置，包括主副手
     */
    public static EntityEquipmentSlot[] HANDS = new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};

    public EnchantmentFireBurn() {
        //附魔稀有等级
        //附魔加到的工具
        //工具位置
        super(Rarity.COMMON, EnumEnchantmentType.DIGGER, HANDS);
        //设置附魔的国际化键名
        this.setName("fireBurn");
        //设置附魔的注册名
        this.setRegistryName(SecretiveOwner.MODID, "fire_burn");
    }

    /**
     * Returns the minimum level that the enchantment can have.
     * 返回附魔的最低等级
     */
    @Override
    public int getMinLevel() {
        return 1;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     * 返回附魔的最高等级
     */
    @Override
    public int getMaxLevel() {
        return 1;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     * 该附魔需要的的最低等级
     *
     * @param enchantmentLevel
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 2;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     * 该附魔需要的最高等级
     *
     * @param enchantmentLevel
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     * 设置附魔和精准采集和时运共存
     *
     * @param ench
     */
    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && Enchantment.getEnchantmentID(ench) != Enchantment.getEnchantmentID(Enchantments.SILK_TOUCH) && Enchantment.getEnchantmentID(ench) != Enchantment.getEnchantmentID(Enchantments.FORTUNE);
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     * 设置附魔可以作用于剪刀
     *
     * @param stack
     */
    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() == Items.SHEARS ? true : super.canApply(stack);
    }
}
