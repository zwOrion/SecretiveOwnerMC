package com.github.zworion.secretiveowner.block.fluid;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import com.github.zworion.secretiveowner.fluid.FluidLoader;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 22:27
 * 水银流体方块
 */
public class BlockFluidMercury extends BlockFluidClassic {
    public BlockFluidMercury() {
        //水银流体，流体材质
        super(FluidLoader.fluidMercury, Material.WATER);
        //设置国际化键名
        this.setTranslationKey("fluidMercury");
        //设置创造物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //设置注册名
        this.setRegistryName(SecretiveOwner.MODID, "fluid_mercury");
    }

}
