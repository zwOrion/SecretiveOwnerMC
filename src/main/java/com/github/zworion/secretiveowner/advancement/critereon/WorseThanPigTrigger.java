package com.github.zworion.secretiveowner.advancement.critereon;

import com.github.zworion.secretiveowner.SecretiveOwner;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.*;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/17 17:25
 */
public class WorseThanPigTrigger implements ICriterionTrigger<WorseThanPigTrigger.Instance> {
    /**
     * 触发器的唯一ID
     */
    private static final ResourceLocation ID = new ResourceLocation(SecretiveOwner.MODID, "worse_than_pig");
    /**
     * 集中管理所有进度用到的该触发条件的具体实例
     */
    private final Map<PlayerAdvancements, WorseThanPigTrigger.Listeners> listeners = new HashMap<>();

    /**
     * @return net.minecraft.util.ResourceLocation
     * @author ZWOrion
     * @date 2020/1/17 19:07
     * 该 Criterion 唯一 ID 的访问器
     */
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<WorseThanPigTrigger.Instance> listener) {
        WorseThanPigTrigger.Listeners worseThanPigTriggerListeners = this.listeners.get(playerAdvancementsIn);
        if (worseThanPigTriggerListeners == null) {
            worseThanPigTriggerListeners = new WorseThanPigTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, worseThanPigTriggerListeners);
        }
        worseThanPigTriggerListeners.addListener(listener);
    }

    /**
     * @param playerAdvancementsIn
     * @param listener
     * @return void
     * @author ZWOrion
     * @date 2020/1/17 19:11
     * 清除不用的监听器，避免占用资源。由 Minecraft 内部统一管理。
     */
    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<WorseThanPigTrigger.Instance> listener) {
        WorseThanPigTrigger.Listeners worseThanPigTriggerListeners = this.listeners.get(playerAdvancementsIn);
        if (worseThanPigTriggerListeners != null) {
            worseThanPigTriggerListeners.removeListener(listener);
            if (worseThanPigTriggerListeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    /**
     * @param playerAdvancementsIn
     * @return void
     * @author ZWOrion
     * @date 2020/1/17 19:12
     * 直接取消对某个玩家进度的跟踪
     */
    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     * 抽象工厂方法，实际上是反序列化器
     *
     * @param json
     * @param context
     */
    @Override
    public WorseThanPigTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        EntityPredicate entitypredicate = EntityPredicate.deserialize(json.get("entity"));
        ItemPredicate itemPredicate = ItemPredicate.deserialize(json.get("item"));
        return new WorseThanPigTrigger.Instance(entitypredicate, itemPredicate);
    }

    /**
     * @param player
     * @param entity
     * @param item
     * @return void
     * @author ZWOrion
     * @date 2020/1/17 19:15
     * 该触发器的入口，需要在合适的时间与位置调用
     */
    public void tigger(EntityPlayerMP player, Entity entity, ItemStack item) {
        WorseThanPigTrigger.Listeners worseThanPigTriggerListeners = this.listeners.get(player.getAdvancements());
        if (worseThanPigTriggerListeners != null) {
           worseThanPigTriggerListeners.trigger(player, entity, item);
        }

    }

    /**
     * @author ZWOrion
     * @date 2020/1/17 19:16
     * 抽象工厂方法生产出的触发器实例
     */
    public static final class Instance extends AbstractCriterionInstance {
        private EntityPredicate entityPredicate;
        private ItemPredicate itemPredicate;

        public Instance(EntityPredicate entityPredicate, ItemPredicate itemPredicate) {

            // 进行必要的初始化操作
            // AbstractCriterionInstance 的构造器要求传入的是这个进度唯一的
            // 识别 ID，即 ICriterionTrigger.getId() 的返回值。
            super(WorseThanPigTrigger.ID);
            this.entityPredicate = entityPredicate;
            this.itemPredicate = itemPredicate;
        }

        /**
         * @param player
         * @param entity
         * @param item
         * @return boolean
         * @author ZWOrion
         * @date 2020/1/18 14:35
         * 每一个触发器实例都有其独立的判据。
         */
        public boolean test(EntityPlayerMP player, Entity entity, ItemStack item) {
            this.itemPredicate.test(item);
            if (!this.itemPredicate.test(item)) {
                return false;
            } else {
                return this.entityPredicate.test(player, entity);
            }
        }
    }

    public static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener<WorseThanPigTrigger.Instance>> listeners = new HashSet<>();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void addListener(ICriterionTrigger.Listener<WorseThanPigTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void removeListener(ICriterionTrigger.Listener<WorseThanPigTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger(EntityPlayerMP player, Entity entity, ItemStack item) {
            List<ICriterionTrigger.Listener<WorseThanPigTrigger.Instance>> list = null;
            for (ICriterionTrigger.Listener<WorseThanPigTrigger.Instance> listener : this.listeners) {
                if ((listener.getCriterionInstance()).test(player, entity, item)) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(listener);
                }
            }
            if (list != null) {
                for (ICriterionTrigger.Listener<WorseThanPigTrigger.Instance> listener2 : list) {
                    listener2.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
