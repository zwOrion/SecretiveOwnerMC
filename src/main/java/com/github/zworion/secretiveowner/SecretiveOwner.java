package com.github.zworion.secretiveowner;

import com.github.zworion.secretiveowner.common.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SecretiveOwner.MODID, name = SecretiveOwner.NAME, version = SecretiveOwner.VERSION)
public class SecretiveOwner {
    public static final String MODID = "secretiveowner";
    public static final String NAME = "Secretive Owner";
    public static final String VERSION = "1.0.0";
    private static Logger logger;
    @Mod.Instance(SecretiveOwner.MODID)
    public static SecretiveOwner instance;
    @SidedProxy(clientSide = "com.github.zworion.secretiveowner.client.ClientProxy", serverSide = "com.github.zworion.secretiveowner.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
