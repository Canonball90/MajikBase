package com.canon.majik.api.hooks.hooks;

import com.canon.majik.api.hooks.HookProcessor;
import net.minecraft.network.Packet;

public class PacketHook extends HookProcessor {

    private final Packet packet;

    public PacketHook(Packet packet){
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public static class Recieve extends PacketHook {
        public Recieve(Packet packet) {
            super(packet);
        }
    }

    public static class Send extends PacketHook {
        public Send(Packet packet) {
            super(packet);
        }
    }

    public static class PostRecieve extends PacketHook {
        public PostRecieve(Packet packet) {
            super(packet);
        }
    }

    public static class PostSend extends PacketHook {
        public PostSend(Packet packet) {
            super(packet);
        }
    }
}
