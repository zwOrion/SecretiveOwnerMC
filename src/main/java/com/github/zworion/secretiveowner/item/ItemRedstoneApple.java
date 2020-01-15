package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * 红石苹果类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/03
 */
public class ItemRedstoneApple extends ItemFood {
    /**
     * @return
     * @author ZWOrion
     * @date 2020/1/8 23:33
     * 设置红石苹果的基本属性
     */
    public ItemRedstoneApple() {
        //设置食物的回复饥饿值，相对饱和度，可否被狼食用
        super(4, 0.6F, false);
        //设置食物总是可以被食用
        this.setAlwaysEdible();
        //设置食物的国际化名的键名
        this.setTranslationKey("secretiveowner.redStoneApple");
        //设置食物的创造物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //设置食物的注册名
        this.setRegistryName("secretiveowner", "redstone_apple");

    }

    /**
     * @param stack   ItemStack
     * @param worldIn 世界
     * @param player  玩家
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 23:38
     * 设置食物的食用效果
     */
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 3));
            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
            }
        }
    }
}
