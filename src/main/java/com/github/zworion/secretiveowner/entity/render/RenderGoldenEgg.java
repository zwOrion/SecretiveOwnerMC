package com.github.zworion.secretiveowner.entity.render;

import com.github.zworion.secretiveowner.entity.EntityGoldenEgg;
import com.github.zworion.secretiveowner.item.ItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/26 10:15
 */
public class RenderGoldenEgg extends RenderSnowball<EntityGoldenEgg> {
    public RenderGoldenEgg(RenderManager renderManagerIn) {
        super(renderManagerIn, ItemLoader.goldenEgg, Minecraft.getMinecraft().getRenderItem());
    }
}
