package com.canon.majik.api.utils;

import net.minecraft.client.Minecraft;

public interface Globals {

    String name = "Majik";
    String modid = "majik";
    String version = "0.1";

    Minecraft mc = Minecraft.getMinecraft();

    default boolean nullCheck(){
        return mc.player == null || mc.world == null;
    }
}
