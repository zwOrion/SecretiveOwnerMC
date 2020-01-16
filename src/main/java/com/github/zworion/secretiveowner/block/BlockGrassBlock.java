package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;

import com.github.zworion.secretiveowner.event.EventLoader;
import com.github.zworion.secretiveowner.event.PlayerRightClickGrassBlockEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 草块类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/03
 */
public class BlockGrassBlock extends Block {
    public BlockGrassBlock() {
        super(Material.GROUND);
        //设置方块的国际化名的键名
        this.setTranslationKey("secretiveowner.grassBlock");
        //设置方块的注册名
        this.setRegistryName("secretiveowner", "grass_block");
        //设置方块所属的创造模式物品栏
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        //设置方块的硬度
        this.setHardness(0.5F);
        //设置方块被击打、破坏、或放置时的声音
        this.setSoundType(blockSoundType);
    }

    /**
     * Called when the block is right clicked by a player.
     * 方块被玩家右击
     *
     * @param worldIn
     * @param pos
     * @param state
     * @param playerIn
     * @param hand
     * @param facing
     * @param hitX
     * @param hitY
     * @param hitZ
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //获取玩家右击草块事件
        PlayerRightClickGrassBlockEvent event = new PlayerRightClickGrassBlockEvent(playerIn, pos, worldIn);
        //发布该事件
        EventLoader.EVENT_BUS.post(event);
        //判断事件是否被取消并且判断是否在服务端
        if (!event.isCanceled() && !worldIn.isRemote) {
            //替换草块为空气
            worldIn.setBlockToAir(pos);
            return true;
        }
        return false;
    }
}
