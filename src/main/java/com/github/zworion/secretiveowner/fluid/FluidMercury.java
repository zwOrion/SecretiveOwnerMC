package com.github.zworion.secretiveowner.fluid;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 22:00
 */
public class FluidMercury extends Fluid {
    /**
     * 流体静止的时候使用的贴图位置
     */
    public static final ResourceLocation still = new ResourceLocation(SecretiveOwner.MODID + ":" + "fluid/mercury_still");
    /**
     * 流体流动的时候使用的贴图位置
     */
    public static final ResourceLocation flowing = new ResourceLocation(SecretiveOwner.MODID + ":" + "fluid/mercury_flow");

    public FluidMercury() {
        super("fluid_mercury", still, flowing);
        //设置本地化键名
        this.setUnlocalizedName("fluidMercury");
        //设置流体密度
        this.setDensity(13600);
        //设置流体粘度
        this.setViscosity(750);
        //设置流体亮度
        this.setLuminosity(0);
        //设置流体粘度
        this.setTemperature(300);
        //设置物品不是气体
        this.setGaseous(false);
    }
}
