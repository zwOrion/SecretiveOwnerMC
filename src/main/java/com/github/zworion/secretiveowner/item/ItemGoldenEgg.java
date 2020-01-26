package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.config.DefineConfig;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import com.github.zworion.secretiveowner.entity.EntityGoldenEgg;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

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

    /**
     * Called when the equipped item is right clicked.
     *
     * @param worldIn
     * @param playerIn
     * @param handIn
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!playerIn.capabilities.isCreativeMode) {
            itemStack.shrink(1);
        }
        if (!worldIn.isRemote) {
            EntityGoldenEgg goldenEgg = new EntityGoldenEgg(worldIn, playerIn);
            goldenEgg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(goldenEgg);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }
}
