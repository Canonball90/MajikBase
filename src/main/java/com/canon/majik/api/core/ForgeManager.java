package com.canon.majik.api.core;

import com.canon.majik.api.hooks.hooks.LoopHook;
import com.canon.majik.api.hooks.hooks.TickHook;
import com.canon.majik.api.utils.Globals;
import com.canon.majik.impl.modules.api.Module;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ForgeManager implements Globals {

    public ForgeManager(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e){
        if(nullCheck()) return;
        try{
            if(Keyboard.isCreated()){
                if(Keyboard.getEventKeyState()){
                    int keyCode = Keyboard.getEventKey();
                    if(keyCode <= 0){
                        return;
                    }
                    for(Module m : Initializer.moduleManager.getModules()){
                        if(m.getKey() == keyCode && keyCode > 0){
                            m.toggle();
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onUpdate(TickHook hook){
        Initializer.moduleManager.onUpdate();
    }

    @SubscribeEvent
    public void onLoop(LoopHook hook){
        Initializer.moduleManager.onLoop();
    }
}
