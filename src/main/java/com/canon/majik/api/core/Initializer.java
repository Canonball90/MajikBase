package com.canon.majik.api.core;

import com.canon.majik.api.utils.Globals;
import com.canon.majik.api.utils.turok.render.font.TurokFont;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class Initializer implements Globals {

    public static Logger logger;
    public static ModuleManager moduleManager;
    public static ForgeManager forgeManager;

    public static TurokFont CFont = new TurokFont(new Font("Arial", 0, 16), true, true);

    public void preInit(FMLPreInitializationEvent event){
        Display.setTitle(Globals.name + " " + Globals.version);
        logger = event.getModLog();
    }

    public void init(){
        logger.info("Init");
        moduleManager = new ModuleManager();
        forgeManager = new ForgeManager();
    }

    public void postInit(){
        Config.loadConfig();
    }

}
