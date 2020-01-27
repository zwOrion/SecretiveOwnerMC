package com.github.zworion.secretiveowner.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/26 11:41
 */
public class FakePlayerLoader {
    private static GameProfile gameProfile;
    private static WeakReference<EntityPlayerMP> fakePlayer2;
    private static FakePlayer fakePlayer;
    public FakePlayerLoader(){
        gameProfile = new GameProfile(UUID.randomUUID(),"[SOTutor]");
        fakePlayer2 = new WeakReference<>(null);
    }
    public static WeakReference<EntityPlayerMP> getFakePlayer(WorldServer server){
        if (fakePlayer2.get()==null){
            fakePlayer2 = new WeakReference<>(FakePlayerFactory.get(server, gameProfile));
        }else {
            fakePlayer2.get().world = server;
        }
        return fakePlayer2;
    }
    public static FakePlayer getNewFakePlayer(WorldServer server){
        if (fakePlayer==null){
            fakePlayer = FakePlayerFactory.get(server, gameProfile);
        }else {
            fakePlayer.world = server;
        }
        return fakePlayer;
    }
}
