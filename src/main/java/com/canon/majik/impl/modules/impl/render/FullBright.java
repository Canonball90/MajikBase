package com.canon.majik.impl.modules.impl.render;

import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;
import net.minecraft.init.MobEffects;

import java.util.ArrayList;

public class FullBright extends Module {

    Setting mode;

    public FullBright() {
        super("FullBright", Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

        options.add("Gamma");
        options.add("Potion");

        mode = setting("Mode", "Gamma", options);
    }

    @Override
    public void onEnable() {
        if(mode.getMode().equalsIgnoreCase("Gamma")){
            mc.gameSettings.gammaSetting = 1000f;
        }else{
            //mc.player.addPotionEffect(MobEffects.NIGHT_VISION.getAttributeModifierAmount());
        }
        super.onEnable();
    }
}
