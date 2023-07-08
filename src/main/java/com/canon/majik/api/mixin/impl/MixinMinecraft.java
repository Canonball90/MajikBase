package com.canon.majik.api.mixin.impl;

import com.canon.majik.api.hooks.hooks.LoopHook;
import com.canon.majik.api.hooks.hooks.TickHook;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "runTick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci){
        TickHook th = new TickHook();
        MinecraftForge.EVENT_BUS.post(th);
    }

    @Inject(method = "runGameLoop", at = @At("HEAD"))
    public void onLoop(CallbackInfo ci){
        LoopHook lh = new LoopHook();
        MinecraftForge.EVENT_BUS.post(lh);
    }

}
