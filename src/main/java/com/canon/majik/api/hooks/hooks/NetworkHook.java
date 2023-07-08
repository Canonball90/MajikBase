package com.canon.majik.api.hooks.hooks;

import com.canon.majik.api.hooks.HookProcessor;
import net.minecraft.network.Packet;

public class NetworkHook extends HookProcessor {
    public Packet mPacket;

    public NetworkHook(Packet pPacket) {
        super();
        mPacket = pPacket;
    }

    public Packet GetPacket() {
        return mPacket;
    }

    public Packet getPacket() {
        return mPacket;
    }

}
