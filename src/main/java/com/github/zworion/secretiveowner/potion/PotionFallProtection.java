package com.github.zworion.secretiveowner.potion;

import com.github.zworion.secretiveowner.SecretiveOwner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/16 14:08
 */
public class PotionFallProtection extends Potion {
    /**
     * 指明资源位置
     */
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(SecretiveOwner.MODID + ":" + "textures/gui/potion.png");
    private static String uuid = MathHelper.getRandomUUID().toString();
    public PotionFallProtection() {
        //设置药水是否有害，设置粒子效果
        super(false, 0x7F0000);
        //设置药水的注册名
        this.setRegistryName(SecretiveOwner.MODID, "fall_protection");
        //设置药水国际化名
        this.setPotionName("potion.fallProtection");
        //设置药水可以影响的属性效果
        this.registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH,uuid,3.5,2);
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
    }

    /**
     * Returns true if the potion has a associated status icon to display in then inventory when active.
     */
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    /**
     * Called to draw the this Potion onto the player's inventory when it's active.
     * This can be used to e.g. render Potion icons from your own texture.
     *
     * @param effect the active PotionEffect
     * @param gui    the gui instance
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param z      the z level
     */
    @Override
    public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) {
        //绑定药水图标材质位置
        Minecraft.getMinecraft().getTextureManager().bindTexture(RESOURCE_LOCATION);
        //绘制药水效果图标
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 256, 256);
    }

    /**
     * Called to draw the this Potion onto the player's ingame HUD when it's active.
     * This can be used to e.g. render Potion icons from your own texture.
     *
     * @param effect the active PotionEffect
     * @param gui    the gui instance
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param z      the z level
     * @param alpha  the alpha value, blinks when the potion is about to run out
     */
    @Override
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
        //绑定药水图标材质位置
        Minecraft.getMinecraft().getTextureManager().bindTexture(RESOURCE_LOCATION);
        //绘制药水效果图标
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 256, 256);
        //super.renderHUDEffect(effect, gui, x, y, z, alpha);
    }
}
