package com.github.zworion.secretiveowner.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/29 18:30
 */
public class TileEntityLoader {

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, Block block) {
        ResourceLocation resourceLocation = block.getRegistryName();
        GameRegistry.registerTileEntity(tileEntityClass, resourceLocation);
    }
}
