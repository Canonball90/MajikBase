package com.canon.majik.api.utils.player;

import com.canon.majik.api.utils.Globals;

public class PlayerUtils implements Globals {

    public static boolean isMoving(){
        return mc.gameSettings.keyBindForward.isKeyDown() ||
                mc.gameSettings.keyBindBack.isKeyDown() ||
                mc.gameSettings.keyBindLeft.isKeyDown() ||
                mc.gameSettings.keyBindRight.isKeyDown();
    }

}
