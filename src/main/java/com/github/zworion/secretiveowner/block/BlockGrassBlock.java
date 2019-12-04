package com.github.zworion.secretiveowner.block;

import com.github.zworion.secretiveowner.creativetab.SecretiveOwnerCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 草方块类
 *
 * @author ZWOrion
 * @version 1.0.0
 * @date 19/12/03
 */
public class BlockGrassBlock extends Block {

    /**
     * 设置草方块的属性
     */
    public BlockGrassBlock() {
        super(Material.GROUND);
        this.setTranslationKey("secretiveowner.grassBlock");
        this.setRegistryName("secretiveowner", "grass_block");
        this.setCreativeTab(SecretiveOwnerCreativeTab.SO_TAB);
        this.setHardness(0.5F);
        this.setSoundType(blockSoundType);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
