package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT,modid = SecretiveOwner.MODID)
public final class ModelMapper {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ItemLoader.goldenEgg, 0, new ModelResourceLocation(new ResourceLocation("secretiveowner", "golden_egg"), "inventory"));
    }
}
