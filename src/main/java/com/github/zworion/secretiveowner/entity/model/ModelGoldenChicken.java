package com.github.zworion.secretiveowner.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/22 15:23
 */
public class ModelGoldenChicken extends ModelBase {
    /**
     * 头
     */
    public ModelRenderer head;
    /**
     * 嘴
     */
    public ModelRenderer bill;
    /**
     * 下巴
     */
    public ModelRenderer chin;
    /**
     * 身躯
     */
    public ModelRenderer body;
    /**
     * 右腿
     */
    public ModelRenderer rightLeg;
    /**
     * 左腿
     */
    public ModelRenderer leftLeg;
    /**
     * 右翼
     */
    public ModelRenderer rightWing;
    /**
     * 左翼
     */
    public ModelRenderer leftWing;

    public ModelGoldenChicken() {
        //设置头部材质位置
        this.head = new ModelRenderer(this, 0, 0);
        //设置模型的大小以及移动坐标系圆点
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
        //设置实体生物的内禀坐标相对于旋转中心点的位置关系
        this.head.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.bill = new ModelRenderer(this, 14, 0);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
        this.bill.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.chin = new ModelRenderer(this, 14, 4);
        this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
        this.chin.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 26, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.rightLeg.setRotationPoint(-2.0F, 19.0F, 1.0F);
        this.leftLeg = new ModelRenderer(this, 26, 0);
        this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.leftLeg.setRotationPoint(1.0F, 19.0F, 1.0F);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 6);
        this.rightWing.setRotationPoint(-3.0F, 15.5F, 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 6);
        this.leftWing.setRotationPoint(3.0F, 15.5F, 0.0F);
    }

    /**
     * @param entity          实体本身
     * @param limbSwing       实体生物运动的四肢的量
     * @param limbSwingAmount 实体生物运动的四肢的速度
     * @param rotateFloat     实体四肢的固有摆动
     * @param rotateYaw       头部旋转的Yaw值
     * @param rotatePitch     头部旋转Pitch值
     * @param scale           设置实体尺寸
     * @return void
     * @author ZWOrion
     * @date 2020/1/23 13:22
     * 渲染
     */

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
                       float rotatePitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, rotateFloat, rotateYaw, rotatePitch, scale, entity);
        this.head.render(scale);
        this.bill.render(scale);
        this.chin.render(scale);
        this.body.render(scale);
        this.rightLeg.render(scale);
        this.leftLeg.render(scale);
        this.rightWing.render(scale);
        this.leftWing.render(scale);
    }

    /**
     * @param limbSwing       实体生物运动的四肢的量
     * @param limbSwingAmount 实体生物运动的四肢的速度
     * @param rotateFloat     实体四肢的固有摆动
     * @param rotateYaw       头部旋转的Yaw值
     * @param rotatePitch     头部旋转Pitch值
     * @param scale           设置实体尺寸
     * @param entity          实体
     * @return void
     * @author ZWOrion
     * @date 2020/1/23 13:26
     * 设置实体的旋转角度
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
                                  float rotatePitch, float scale, Entity entity) {
        this.head.rotateAngleX = rotatePitch / (180F / (float) Math.PI);
        this.head.rotateAngleY = rotateYaw / (180F / (float) Math.PI);
        this.bill.rotateAngleX = this.head.rotateAngleX;
        this.bill.rotateAngleY = this.head.rotateAngleY;
        this.chin.rotateAngleX = this.head.rotateAngleX;
        this.chin.rotateAngleY = this.head.rotateAngleY;
        this.body.rotateAngleX = (float) (Math.PI / 2.0D);
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightWing.rotateAngleZ = 1.5F * rotateFloat;
        this.leftWing.rotateAngleZ = -1.5F * rotateFloat;
    }

}
