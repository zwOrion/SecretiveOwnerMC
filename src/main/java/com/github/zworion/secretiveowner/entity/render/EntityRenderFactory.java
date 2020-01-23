package com.github.zworion.secretiveowner.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 15:15
 * 实体模型工厂类
 */
public class EntityRenderFactory<E extends Entity> implements IRenderFactory<E> {
    /**
     * 获取Class实例
     */
    private final Class<? extends Render<E>> renderClass;

    public EntityRenderFactory(Class<? extends Render<E>> renderClass) {
        this.renderClass = renderClass;
    }
    /**
     *
     * @author ZWOrion
     * @date 2020/1/22 20:24
     * 创建渲染模型
     * @param manager
     * @return net.minecraft.client.renderer.entity.Render<E>
     */
    @Override
    public Render<E> createRenderFor(RenderManager manager) {
        try {
            //根据类创建一个渲染模型
            return renderClass.getConstructor(RenderManager.class).newInstance(manager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
