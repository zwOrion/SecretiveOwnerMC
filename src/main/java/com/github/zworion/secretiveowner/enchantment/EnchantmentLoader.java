package com.github.zworion.secretiveowner.enchantment;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
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

}
