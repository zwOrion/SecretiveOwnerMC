package com.github.zworion.secretiveowner.entity;

import com.github.zworion.secretiveowner.item.ItemLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/26 9:25
 */
public class EntityGoldenEgg extends EntityThrowable {
    public EntityGoldenEgg(World worldIn) {
        super(worldIn);
    }

    public EntityGoldenEgg(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityGoldenEgg(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     *
     * @param result
     */
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit instanceof EntityChicken) {
                EntityChicken originalChicken = (EntityChicken) result.entityHit;
                EntityGoldenChicken goldenChicken = new EntityGoldenChicken(this.world);
                goldenChicken.setGrowingAge(originalChicken.getGrowingAge());
                goldenChicken.setLocationAndAngles(originalChicken.posX, originalChicken.posY, originalChicken.posZ, originalChicken.rotationYaw, originalChicken.rotationPitch);
                originalChicken.setDead();
                this.world.spawnEntity(goldenChicken);
            } else {
                this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(ItemLoader.goldenEgg)));
            }
            this.setDead();
        }
    }

}
