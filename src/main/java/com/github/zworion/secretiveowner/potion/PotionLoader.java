package com.github.zworion.secretiveowner.potion;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/16 14:06
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class PotionLoader {
    /**
     * 摔落保护
     */
    public static Potion potionFallProtection = new PotionFallProtection();

    /**
     * @param event 注册事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/16 22:09
     * 注册药水
     */
    @SubscribeEvent
    public static void onPotionRegistration(RegistryEvent.Register<Potion> event) {
        SecretiveOwner.logger.info("注册药水效果 >> {}", "");
        //注册摔落保护
        event.getRegistry().registerAll(potionFallProtection);
    }

}
