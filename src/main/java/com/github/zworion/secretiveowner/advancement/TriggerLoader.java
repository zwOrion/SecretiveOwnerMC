package com.github.zworion.secretiveowner.advancement;

import com.github.zworion.secretiveowner.advancement.critereon.WorseThanPigTrigger;
import net.minecraft.advancements.CriteriaTriggers;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/18 15:41
 */
public class TriggerLoader {
    public static WorseThanPigTrigger WORSE_THAN_PIG_TRIGGER;

    public static void registerTrigger(){
        WORSE_THAN_PIG_TRIGGER = CriteriaTriggers.register(new WorseThanPigTrigger());
    }
}
