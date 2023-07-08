package com.canon.majik.impl.modules.impl.movement;

import com.canon.majik.api.utils.player.PlayerUtils;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;

import java.util.ArrayList;

public class Sprint extends Module {

    Setting mode;

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
        ArrayList<String> modes = new ArrayList<>();

        modes.add("Rage");
        modes.add("Legit");

        mode = setting("Mode", "Legit", modes);

        add(mode);
    }

    @Override
    public void onLoop() {
        if(mode.getMode().equalsIgnoreCase("Legit")){
            if(PlayerUtils.isMoving()){
                mc.player.setSprinting(true);
            }
        }else{
            mc.player.setSprinting(true);
        }
        super.onLoop();
    }
}
