package com.github.zworion.secretiveowner.sound;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 12:24
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class SoundLoader {
    /**
     * 自定义音效
     */
    public static final SoundEvent TEST_SOUND = new SoundEvent(new ResourceLocation(SecretiveOwner.MODID, "test"));
    /**
     *
     * @author ZWOrion
     * @date 2020/1/19 15:19
     * 对音效进行注册
     * @param event
     * @return void
     */
    @SubscribeEvent
    public static void onSoundEventRegistration(RegistryEvent.Register<SoundEvent> event){
        //注册音效
        event.getRegistry().register(TEST_SOUND.setRegistryName(new ResourceLocation(SecretiveOwner.MODID, "test")));
    }
}
