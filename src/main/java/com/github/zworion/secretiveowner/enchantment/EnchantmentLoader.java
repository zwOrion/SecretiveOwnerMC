package com.github.zworion.secretiveowner.enchantment;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/15 21:37
 * 注册附魔事件
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class EnchantmentLoader {
    /**
     * 火焰灼烧附魔
     */
    public static EnchantmentFireBurn fireBurn = new EnchantmentFireBurn();

    public EnchantmentLoader() {
    }

    /**
     * @param event
     * @return void
     * @author ZWOrion
     * @date 2020/1/16 11:05
     * 注册附魔事件
     */
    @SubscribeEvent
    public static void onEnchantmentRegistration(RegistryEvent.Register<Enchantment> event) {
        //注册火焰灼烧附魔
        event.getRegistry().registerAll(fireBurn);
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/16 12:21
     * 火焰灼烧附魔对应方块掉落事件
     * @param event 方块掉落事件
     * @return void
     */
    @SubscribeEvent
    public static void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        //判断事件发生是否在服务器上以及是否是玩家收获
        if (!event.getWorld().isRemote && event.getHarvester() != null) {
            //获取玩家主手物品
            ItemStack mainStack = event.getHarvester().getHeldItemMainhand();
            //获取玩家副手武平
            ItemStack offStack = event.getHarvester().getHeldItemOffhand();
            //判断物品是否有火焰灼烧附魔并且判断是不是剪刀
            if (EnchantmentHelper.getEnchantmentLevel(fireBurn, mainStack) > 0 && mainStack.getItem() != Items.SHEARS) {
                //循环遍历掉落物
                for (int i = 0; i < event.getDrops().size(); i++) {
                    //获取掉落物stack
                    ItemStack stack = event.getDrops().get(i);
                    //获取掉落物经过熔炉熔炼后的产物
                    ItemStack newStack = FurnaceRecipes.instance().getSmeltingResult(stack);
                    //判断物品是否有熔炼后的产物
                    if (newStack != null) {
                        //将产物Stack复制
                        newStack = newStack.copy();
                        //设置产物的数量
                        newStack.setCount(stack.getCount());
                        //设置掉落物变为熔炼后的产物
                        event.getDrops().set(i, newStack);
                    } else if (stack != null) {
                        //获取方块
                        Block block = Block.getBlockFromItem(stack.getItem());
                        //判断方块是否为空
                        boolean b = (block == null);
                        //判断方块是否会着火
                        boolean b2 = (block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.DOWN)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.EAST)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.WEST)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.UP)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.SOUTH)
                                || block.isFlammable(event.getWorld(), event.getPos(), EnumFacing.NORTH));
                        //如果方块存在并且方块可以着火就移除方块
                        if (!b && b2) {
                            event.getDrops().remove(i);
                        }
                    }
                }
            }
        }
    }
}
