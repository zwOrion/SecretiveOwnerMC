package com.github.zworion.secretiveowner.client;

import com.github.zworion.secretiveowner.common.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 客户端
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/04
 */
public class ClientProxy extends CommonProxy {
    /**
     * @param event 初始化事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:56
     * 初始化
     */
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    /**
     * @param event 初始化之后事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:56
     * 初始化后
     */
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    /**
     * @param event 预初始化事件
     * @return void
     * @author ZWOrion
     * @date 2020/1/8 22:57
     * 预初始化
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        /* new ItemLoader(event); */
    }
}


