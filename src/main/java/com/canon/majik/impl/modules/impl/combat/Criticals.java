package com.canon.majik.impl.modules.impl.combat;

import com.canon.majik.api.hooks.hooks.PacketHook;
import com.canon.majik.api.utils.client.ACNumbers;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @SubscribeEvent
    public void onPacket(PacketHook.Send event){
        if(event.getPacket() instanceof CPacketUseEntity){
            if(mc.player.onGround) {
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + ACNumbers.Criticals_NonStrict, mc.player.posZ, false));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + ACNumbers.Criticals_NonStrict2, mc.player.posZ, false));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
            }
        }
    }
}
