package com.canon.majik.api.core;

import com.canon.majik.api.utils.Globals;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.modules.impl.TestModule;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import com.canon.majik.impl.modules.impl.combat.Aura;
import com.canon.majik.impl.modules.impl.combat.Criticals;
import com.canon.majik.impl.modules.impl.misc.Spammer;
import com.canon.majik.impl.modules.impl.movement.Sprint;
import com.canon.majik.impl.modules.impl.render.Fov;
import com.canon.majik.impl.modules.impl.render.FullBright;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class ModuleManager implements Globals {

    ArrayList<Module> modules;

    public ModuleManager(){
        (modules = new ArrayList<Module>()).clear();
        MinecraftForge.EVENT_BUS.register(this);
        initModule();
    }

    public void initModule(){
        modules.add(new TestModule());
        modules.add(new ClickGui());
        modules.add(new Fov());
        modules.add(new Aura());
        modules.add(new Sprint());
        modules.add(new FullBright());
        modules.add(new Criticals());
        modules.add(new Spammer());
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void onUpdate(){
        if(nullCheck()) return;
        for(Module m : getModules()){
            if(m.isEnabled()){
                m.onUpdate();
            }
        }
    }

    public void onLoop(){
        if(nullCheck()) return;
        for(Module m : getModules()){
            if(m.isEnabled()){
                m.onLoop();
            }
        }
    }
}
