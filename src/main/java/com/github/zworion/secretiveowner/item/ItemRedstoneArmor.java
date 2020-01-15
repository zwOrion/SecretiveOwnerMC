package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/13 21:54
 * 红石盔甲构造类
 */
public class ItemRedstoneArmor extends ItemArmor implements ISpecialArmor {
    /**
     * 自定义材质
     * name 材质名
     * textureName 材质名,默认位置在 assets.secretiveowner.textures.models.armor
     * durability 耐久值
     * reductionAmounts 参数的四个元素表示对应盔甲的头盔、胸甲、护腿、和靴子抵御伤害的能力
     * enchantability 附魔系数
     * soundEvent 声音事件
     * toughness 韧性
     */
    public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelper.addArmorMaterial("REDSTONE",
            SecretiveOwner.MODID + ":" + "redstone", 10, new int[]{2, 6, 4, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public ItemRedstoneArmor(EntityEquipmentSlot equipmentSlotIn) {
        super(REDSTONE_ARMOR, 0, equipmentSlotIn);
    }

    /**
     * Called by RenderBiped and RenderPlayer to determine the armor texture that
     * should be use for the currently equipped item.
     * This will only be called on instances of ItemArmor.
     * <p>
     * Returning null from this function will use the default value.
     * 调用以确定应该用于当前装备的装备的装甲纹理。 这只会在ItemArmor的实例上调用
     *
     * @param stack  ItemStack for the equipped armor
     * @param entity The entity wearing the armor
     * @param slot   The slot the armor is in
     * @param type   The subtype, can be null or "overlay"
     * @return Path of texture to bind, or null to use default
     */
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        //判断材质加载位置是否是在腿部
        char index = this.armorType == EntityEquipmentSlot.LEGS ? '2' : '1';
        //返回材质位置
        return SecretiveOwner.MODID + ":textures/models/armor/redstone_layer_" + index + ".png";
    }

    /**
     * Retrieves the modifiers to be used when calculating armor damage.
     * <p>
     * Armor will higher priority will have damage applied to them before
     * lower priority ones. If there are multiple pieces of armor with the
     * same priority, damage will be distributed between them based on there
     * absorption ratio.
     * 检索在计算装甲伤害时要使用的修改器。
     * 优先级较高的护甲将在优先级较低的护甲之前受到伤害。
     * 如果有多个具有相同优先级的装甲，则伤害将根据那里的吸收率分配
     *
     * @param player The entity wearing the armor.
     * @param armor  The ItemStack of the armor item itself.
     * @param source The source of the damage, which can be used to alter armor
     *               properties based on the type or source of damage.
     * @param damage The total damage being applied to the entity
     * @param slot   The armor slot the item is in.
     * @return A ArmorProperties instance holding information about how the armor effects damage.
     */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        //优先权，吸收比，最大吸收值
        return new ArmorProperties(0, 1.0, 100);
    }

    /**
     * Get the displayed effective armor.
     *
     * @param player The player wearing the armor.
     * @param armor  The ItemStack of the armor item itself.
     * @param slot   The armor slot the item is in.
     * @return The number of armor points for display, 2 per shield.
     */
    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return 0;
    }

    /**
     * Applies damage to the ItemStack. The mod is responsible for reducing the
     * item durability and stack size. If the stack is depleted it will be cleaned
     * up automatically.
     *
     * @param entity The entity wearing the armor
     * @param stack  The ItemStack of the armor item itself.
     * @param source The source of the damage, which can be used to alter armor
     *               properties based on the type or source of damage.
     * @param damage The amount of damage being applied to the armor
     * @param slot   The armor slot the item is in.
     */
    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {

    }

    /**
     * 红石头盔类
     *
     * @author ZWOrion
     * @date 2020/1/15 14:03
     */
    public static class Helmet extends ItemRedstoneArmor {
        public Helmet() {
            super(EntityEquipmentSlot.HEAD);
            //设置创造物品栏
            this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
            //设置国际化键名
            this.setTranslationKey("secretiveowner.helmet");
            //设置物品注册名
            this.setRegistryName(SecretiveOwner.MODID, "redstone_helmet");
        }
    }

    /**
     * @author ZWOrion
     * @date 2020/1/15 14:04
     * 红石胸甲类
     */
    public static class Chestplate extends ItemRedstoneArmor {
        public Chestplate() {
            super(EntityEquipmentSlot.CHEST);
            this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
            this.setTranslationKey("secretiveowner.chestplate");
            this.setRegistryName(SecretiveOwner.MODID, "redstone_chestplate");
        }
    }

    /**
     * @author ZWOrion
     * @date 2020/1/15 14:05
     * 红石护腿类
     */
    public static class Leggings extends ItemRedstoneArmor {
        public Leggings() {
            super(EntityEquipmentSlot.LEGS);
            this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
            this.setTranslationKey("secretiveowner.leggings");
            this.setRegistryName(SecretiveOwner.MODID, "redstone_leggings");
        }
    }

    /**
     * @author ZWOrion
     * @date 2020/1/15 14:05
     * 红石靴子类
     */
    public static class Boots extends ItemRedstoneArmor {
        public Boots() {
            super(EntityEquipmentSlot.FEET);
            this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
            this.setTranslationKey("secretiveowner.boots");
            this.setRegistryName(SecretiveOwner.MODID, "redstone_boots");
        }
    }
}
