package com.github.zworion.secretiveowner.client;

import com.github.zworion.secretiveowner.common.CommonProxy;
import com.github.zworion.secretiveowner.item.ItemLoader;

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
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        /* new ItemLoader(event); */
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
