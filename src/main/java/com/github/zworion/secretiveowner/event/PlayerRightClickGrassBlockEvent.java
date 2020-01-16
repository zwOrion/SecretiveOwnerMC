package com.github.zworion.secretiveowner.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/16 14:52
 * 玩家右击草块事件
 */
@Cancelable
public class PlayerRightClickGrassBlockEvent extends PlayerEvent {
    public final BlockPos pos;
    public final World world;

    public PlayerRightClickGrassBlockEvent(EntityPlayer player, BlockPos pos, World world) {
        super(player);
        this.pos = pos;
        this.world = world;
    }
}
