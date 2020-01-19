package com.github.zworion.secretiveowner.worldgen;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.block.BlockLoader;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/19 16:52
 */
public class WorldGeneratorGlowstone extends WorldGenerator {
    /**
     * 实例化 荧石矿石的 WorldGenMinable
     */
    private final WorldGenerator glowstoneGenerator = new WorldGenMinable(Blocks.GLOWSTONE.getDefaultState(), 16);

    /**
     * @param worldIn
     * @param rand
     * @param position
     * @return boolean
     * @author ZWOrion
     * @date 2020/1/19 18:51
     * 矿物生成逻辑
     */
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        //矿物生成事件
        if (TerrainGen.generateOre(worldIn, rand, this, position, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            //在一个区块循环生成50次
            for (int i = 0; i < 50; i++) {
                //获取区块的 x 坐标
                int posX = position.getX() + rand.nextInt(16);
                //获取区块内的 y 坐标
                int posY = 10 + rand.nextInt(72);
                //获取区块的 z 坐标
                int posZ = position.getZ() + rand.nextInt(16);
                //实例化 BlockPos 对象
                BlockPos blockPos = new BlockPos(posX, posY, posZ);
                //获取当前区块的气候
                Biome biome = worldIn.getBiomeForCoordsBody(blockPos);
                //判断气候降雨量
                if (biome.getRainfall() < rand.nextInt(65536)) {
                    //生成荧石矿石
                    glowstoneGenerator.generate(worldIn, rand, blockPos);
                }
            }
        }
        return true;
    }
}
