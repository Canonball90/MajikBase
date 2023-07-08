package com.canon.majik.impl.modules.impl.misc;

import com.canon.majik.api.utils.turok.util.TurokTick;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;

import java.util.Random;

public class Spammer extends Module {

    Setting speed = setting("Speed", 20, 0, 50);
    Setting antiSpam = setting("AntiSpam", false);
    int x = 0;
    String[] words = {
            "Majik hack on tope",
            "fuck niggas i like majik hack",
            "ooga booga!!!! did i scare you?",
            "Bing Bong Majik Hack rill china",
            "Dont like skid clients? join Majik Hack",
            "Wanna see a majik trick? join majik hack",
            "ez majik hack on tope"
    };

    Random random = new Random();
    TurokTick timer = new TurokTick();

    public Spammer() {
        super("Spammer", Category.MISC);
        add(speed);
        add(antiSpam);
    }

    @Override
    public void onLoop() {
        if(x == 0 && timer.isPassedMS(speed.getNumber().floatValue())){
            mc.player.sendChatMessage(words[x]);
            x++;
        }
        if(x == 1 && timer.isPassedMS(speed.getNumber().floatValue())){
            mc.player.sendChatMessage(words[x]);
            x++;
        }
        if(x == 2 && timer.isPassedMS(speed.getNumber().floatValue())){
            mc.player.sendChatMessage(words[x]);
            x++;
        }
        super.onLoop();
    }
}
