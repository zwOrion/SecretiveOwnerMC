package com.github.zworion.secretiveowner.common;


import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.advancement.TriggerLoader;
import com.github.zworion.secretiveowner.crafting.CraftingLoader;
import com.github.zworion.secretiveowner.entity.FakePlayerLoader;
import com.github.zworion.secretiveowner.fluid.FluidLoader;
import com.github.zworion.secretiveowner.worldgen.WorldGeneratorLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 服务器端
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
public class CommonProxy {
    /**
     * @param event 初始化事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:56
     * 初始化
     */
    public void init(FMLInitializationEvent event) {
        SecretiveOwner.logger.info("服务器初始化 >> {}", "注册触发器");
        //注册触发器
        TriggerLoader.registerTrigger();
        SecretiveOwner.logger.info("服务器初始化 >> {}", "注册熔炼规则");
        // 注册熔炼规则
        CraftingLoader.instance().registerSmelting();
        SecretiveOwner.logger.info("服务器初始化 >> {}", "注册矿物生成规则");
        //注册矿物生成
        new WorldGeneratorLoader();
        SecretiveOwner.logger.info("服务器初始化 >> {}", "注册矿物辞典");
        //注册矿物辞典
        new OreDictionaryLoader();
        SecretiveOwner.logger.info("服务器初始化 >> {}", "注册FakePlayer");
        new FakePlayerLoader();
    }

    /**
     * @param event 初始化之后事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:56
     * 初始化后
     */
    public void postInit(FMLPostInitializationEvent event) {
        SecretiveOwner.logger.info("服务器初始化后 >> {}", "");
    }

    /**
     * @param event 预初始化事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:57
     * 预初始化
     */
    public void preInit(FMLPreInitializationEvent event) {
        SecretiveOwner.logger.info("服务器预初始化 >> {}", "注册流体");
        //注册流体
        new FluidLoader();
    }
}

