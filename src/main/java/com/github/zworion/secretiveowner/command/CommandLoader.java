package com.github.zworion.secretiveowner.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/18 23:10
 */
public class CommandLoader {
    public static void commandRegister(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandPosition());
    }
}
