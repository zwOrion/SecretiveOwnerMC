package com.github.zworion.secretiveowner.entity.render;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.github.zworion.secretiveowner.entity.EntityGoldenChicken;
import com.github.zworion.secretiveowner.entity.model.ModelGoldenChicken;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 15:20
 * 金鸡模型渲染类
 */
public class RenderGoldenChicken extends RenderLiving<EntityGoldenChicken> {
    /**
     * 材质模型所在位置
     */
    private static final ResourceLocation GOLDEN_CHICKEN_TEXTURE = new ResourceLocation(
            SecretiveOwner.MODID + ":" + "textures/entity/golden_chicken.png");

    public RenderGoldenChicken(RenderManager renderManager) {
        //渲染管理器，实体渲染模型，阴影大小
        super(renderManager, new ModelGoldenChicken(), 0.5F);
    }

    /**
     *
     * @author ZWOrion
     * @date 2020/1/22 20:44
     * 渲染前的回调进行处理
     * @param entity
     * @param partialTickTime
     * @return void
     */
    @Override
    protected void preRenderCallback(EntityGoldenChicken entity, float partialTickTime) {
        //将鸡的模型大小设置为2.5倍
        GlStateManager.scale(2.5F, 2.5F, 2.5F);
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/22 20:47
     * 指明渲染的材质
     * @param entity
     * @return net.minecraft.util.ResourceLocation
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityGoldenChicken entity) {
        return RenderGoldenChicken.GOLDEN_CHICKEN_TEXTURE;
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/22 20:44
     * 呈现所需显示的实体模型
     * @param entity
     * @param x
     * @param y
     * @param z
     * @param entityYaw
     * @param partialTicks
     * @return void
     */
    @Override
    public void doRender(EntityGoldenChicken entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
