package com.github.zworion.secretiveowner.world.storage.loot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/23 21:15
 * 战利品表文件夹，好坑，不知道为啥这个注册战利品表必须在 minecraft 文件夹下
 */
public class LootLoader {
    /**
     * 金鸡战利品表
     */
    public static final ResourceLocation ENTITES_GOLDENCHICKEN = register("minecraft", "entities/golden_chicken");
    /**
     *
     * @author ZWOrion
     * @date 2020/1/23 23:44
     * 注册战利品表
     * @param nameSpace
     * @param id
     * @return net.minecraft.util.ResourceLocation
     */
    private static ResourceLocation register(String nameSpace, String id) {
        return LootTableList.register(new ResourceLocation(nameSpace, id));
    }

}
