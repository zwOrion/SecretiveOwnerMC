package com.github.zworion.secretiveowner.fluid;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.block.BlockLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 22:22
 * 流体注册类
 */
public class FluidLoader {
    public static Fluid fluidMercury = new FluidMercury();

    public FluidLoader() {
        //注册水银
        fluidMercury = fluidRegistry(fluidMercury);
    }

    /**
     * @param fluidIn 需要被注册流体
     * @return net.minecraftforge.fluids.Fluid
     * @author ZWOrion
     * @date 2020/1/21 21:18
     * 注册流体
     */
    public Fluid fluidRegistry(Fluid fluidIn) {
        if (FluidRegistry.isFluidRegistered(fluidIn)) {
            SecretiveOwner.logger.info("Found fluid {}, the registration is canceled. ", fluidIn.getName());
            fluidIn = FluidRegistry.getFluid(fluidIn.getName());
        } else {
            SecretiveOwner.logger.info("注册流体", fluidIn.getName());
            FluidRegistry.registerFluid(fluidIn);
        }
        return fluidIn;
    }

    /**
     * @return void
     * @author ZWOrion
     * @date 2020/1/21 21:20
     * 注册流体材质
     */
    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        //注册水银的材质
        registerFluidRender((BlockFluidBase) BlockLoader.fluidMercuryBlock, "fluid_mercury");
    }

    /**
     * @param blockFluidBase
     * @param blockStateName
     * @return void
     * @author ZWOrion
     * @date 2020/1/21 21:21
     * 注册方块模型
     */
    @SideOnly(Side.CLIENT)
    public static void registerFluidRender(BlockFluidBase blockFluidBase, String blockStateName) {
        //资源位置(blockstates/fluid下)
        final ResourceLocation location1 = new ResourceLocation(SecretiveOwner.MODID, "fluid/fluid" );
        //获取方块对应流体物品
        final Item itemFluid = Item.getItemFromBlock(blockFluidBase);
        SecretiveOwner.logger.info("渲染流体模型 >> {}", blockStateName);
        //添加Item的模型变量逻辑
        ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {

                return new ModelResourceLocation(location1, blockStateName);
            }
        });
        //添加Block的模型变量逻辑
        ModelLoader.setCustomStateMapper(FluidLoader.fluidMercury.getBlock(), new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(location1, blockStateName);
            }
        });
    }

}
