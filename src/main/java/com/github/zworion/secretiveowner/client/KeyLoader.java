package com.github.zworion.secretiveowner.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/17 13:28
 */
public class KeyLoader {
    /**
     * 显示时间热键，默认绑定 H
     */
    public static KeyBinding showTime= new KeyBinding("key.secretiveowner.showTime", Keyboard.KEY_H, "key.categories.secretiveowner");
    /**
     *
     * @author ZWOrion
     * @date 2020/1/17 14:05
     *  注册绑定热键
     * @return void
     */
    public static void keyBindingRegistration(){
        //注册 显示时间
        ClientRegistry.registerKeyBinding(KeyLoader.showTime);
    }

}
