package com.github.zworion.secretiveowner;

import com.github.zworion.secretiveowner.config.ConfigLoader;
import com.github.zworion.secretiveowner.config.MyModConfigGuiFactory;
import com.github.zworion.secretiveowner.event.EventLoader;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

import com.github.zworion.secretiveowner.common.CommonProxy;
import com.github.zworion.secretiveowner.crafting.CraftingLoader;

import net.minecraft.init.Blocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * mod主类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
@Mod(
        modid = SecretiveOwner.MODID,
        name = SecretiveOwner.NAME,
        version = SecretiveOwner.VERSION,
        guiFactory = "com.github.zworion.secretiveowner.config.MyModConfigGuiFactory"
)
public class SecretiveOwner {

    /**
     * ModID
     */
    public static final String MODID = "secretiveowner";

    /**
     * Mod 名
     */
    public static final String NAME = "Secretive Owner";

    /**
     * Mod 版本
     */
    public static final String VERSION = "1.0.0";

    /**
     * 日志
     */
    private static Logger logger;

    /**
     * 实例化主类
     */
    @Mod.Instance(SecretiveOwner.MODID)
    public static SecretiveOwner instance;

    /**
     * 实例化客户端、服务端事件
     */
    @SidedProxy(
            clientSide = "com.github.zworion.secretiveowner.client.ClientProxy",
            serverSide = "com.github.zworion.secretiveowner.common.CommonProxy"
    )
    public static CommonProxy proxy;

    /**
     * 初始化事件
     *
     * @param event 事件
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);

        // 注册熔炼规则
        CraftingLoader.instance().registerSmelting();
        //实例化EventLoader，注册事件
        new EventLoader();

    }

    /**
     * 初始化结束
     *
     * @param event 事件
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    /**
     * 预初始化
     *
     * @param event 事件
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        new ConfigLoader(event);
    }
}
