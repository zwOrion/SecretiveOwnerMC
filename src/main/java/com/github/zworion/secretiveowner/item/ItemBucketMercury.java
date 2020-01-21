package com.github.zworion.secretiveowner.item;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.block.BlockLoader;
import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;
import com.github.zworion.secretiveowner.fluid.FluidLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/21 18:52
 * 水银桶
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class ItemBucketMercury extends ItemBucket {
    public ItemBucketMercury() {
        super(BlockLoader.fluidMercuryBlock);
        //设置容器为BUCKET，保证参与合成后可以留下桶
        this.setContainerItem(Items.BUCKET);
        //设置国际化键名
        this.setTranslationKey("secretiveowner.bucketMercury");
        //设置创造模式物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //设置注册名
        this.setRegistryName(SecretiveOwner.MODID, "bucket_mercury");
    }

    /**
     * @param event
     * @return void
     * @author ZWOrion
     * @date 2020/1/21 21:37
     * 桶的填充事件
     */
    @SubscribeEvent
    public static void onFillBucket(FillBucketEvent event) {
        //获取交互方块的位置
        BlockPos blockPos = event.getTarget().getBlockPos();
        //获取方块数据
        IBlockState blockState = event.getWorld().getBlockState(blockPos);
        //查找方块对应流体
        Fluid fluid = FluidRegistry.lookupFluidForBlock(blockState.getBlock());
        //判断是否有对应流体
        if (fluid != null && new Integer(0).equals(blockState.getValue(BlockFluidBase.LEVEL))) {
            //判断流体是否是水银
            if (new FluidStack(FluidLoader.fluidMercury, 1).equals(new FluidStack(fluid, 1))) {
                //设置填充后的桶为水银桶
                event.setFilledBucket(new ItemStack(ItemLoader.bucketMercury));
                //设置该位置方块为空气
                event.getWorld().setBlockToAir(blockPos);
                //设置事件结果
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
