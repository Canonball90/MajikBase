package com.canon.majik.api.hooks;

import net.minecraftforge.fml.common.eventhandler.Event;

public class HookProcessor extends Event {

    int stage;

    public HookProcessor(){

    }

    public HookProcessor(int stage){
        this.stage = stage;
    }

    public int getStage(){
        return stage;
    }
}
