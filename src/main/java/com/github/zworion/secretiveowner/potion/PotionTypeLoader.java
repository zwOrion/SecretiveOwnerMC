package com.github.zworion.secretiveowner.potion;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/17 11:51
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class PotionTypeLoader {
    public static PotionType potionType = new PotionType(new PotionEffect(PotionLoader.potionFallProtection));
    @SubscribeEvent
    public static void potionTypeRegistration(RegistryEvent.Register<PotionType> event) {
        event.getRegistry().registerAll(potionType.setRegistryName(SecretiveOwner.MODID, "potionType"));
    }
}
