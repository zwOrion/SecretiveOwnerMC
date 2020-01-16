package com.github.zworion.secretiveowner.config;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/09 16:30
 */
@Config(modid = SecretiveOwner.MODID)
@Config.LangKey("config.secretiveowner.general")
public class DefineConfig {

    @Config.Comment("金蛋燃烧时间")
    @Config.LangKey("config.secretiveowner.general.diamondBurnTime")
    @Config.Name("DiamondBurnTime")
    @Config.RangeInt(min = 1, max = 123456)
    public static int diamondBurnTime = 680;
    
    @Mod.EventBusSubscriber(modid = "secretiveowner")
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(SecretiveOwner.MODID)) {
                ConfigManager.sync(SecretiveOwner.MODID, Config.Type.INSTANCE);
            }
        }
    }
}

