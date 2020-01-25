package com.github.zworion.secretiveowner.entity;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.entity.render.EntityRenderFactory;
import com.github.zworion.secretiveowner.entity.render.RenderGoldenChicken;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 13:06
 */
@Mod.EventBusSubscriber(modid = SecretiveOwner.MODID)
public class EntityLoader {
    private static int nextID = 0;

    /**
     * @param event
     * @return void
     * @author ZWOrion
     * @date 2020/1/22 14:22
     * 注册实体
     */
    @SubscribeEvent
    public static void onEntityRegistation(RegistryEvent.Register<EntityEntry> event) {
        //注册黄金鸡
        //创建一个实体构建器
        event.getRegistry().register(EntityEntryBuilder.create()
                //设置实体的类
                .entity(EntityGoldenChicken.class)
                //设置实体ID 和其对应网络ID
                .id(new ResourceLocation(SecretiveOwner.MODID, "golden_chicken"), nextID++)
                //设置实体名
                .name("goldenChicken")
                //设置实体追踪信息，追踪范围、更新频率、是否发送速度更新
                .tracker(80, 3, false)
                //构建实体
                .build());
        //注册对应的蛋(实体ID,主要颜色，次要颜色)
        EntityRegistry.registerEgg(new ResourceLocation(SecretiveOwner.MODID, "golden_chicken"), 0xffff66, 0x660000);
        //生物群系数组
        Biome[] biomes = new Biome[]{Biomes.PLAINS,Biomes.BEACH,Biomes.BIRCH_FOREST,Biomes.DEFAULT};
        //注册实体所属生物群系
        registerEntitySpawn(EntityGoldenChicken.class, 8, 2, 4, EnumCreatureType.CREATURE, biomes);
    }

    /**
     * @return void
     * @author ZWOrion
     * @date 2020/1/22 20:04
     * 注册实体模型
     */
    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        //注册黄金鸡模型
        registerEntityRender(EntityGoldenChicken.class, RenderGoldenChicken.class);
    }

    /**
     * @param entityClass
     * @param render
     * @return void
     * @author ZWOrion
     * @date 2020/1/22 20:08
     * 注册实体模型
     */
    @SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render) {
        SecretiveOwner.logger.info("注册实体模型 >> {}", entityClass.getName());
        //注册实体模型
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<>(render));
    }
    /**
     * 
     * @author ZWOrion
     * @date 2020/1/25 14:30
     * 注册生物实体生成
     * @param entityClass
     * @param spawnWeight
     * @param min
     * @param max
     * @param typeOfCreature
     * @param biomes 
     * @return void
     */
    private static void registerEntitySpawn(Class<? extends Entity> entityClass, int spawnWeight, int min,
                                            int max, EnumCreatureType typeOfCreature, Biome[] biomes)
    {
        if (EntityLiving.class.isAssignableFrom(entityClass))
        {
            Class<? extends EntityLiving> entityLivingClass = entityClass.asSubclass(EntityLiving.class);
            EntityRegistry.addSpawn(entityLivingClass, spawnWeight, min, max, typeOfCreature, biomes);
        }
    }
}
