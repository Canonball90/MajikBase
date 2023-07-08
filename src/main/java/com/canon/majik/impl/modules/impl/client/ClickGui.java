package com.canon.majik.impl.modules.impl.client;

import com.canon.majik.api.core.Config;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.ui.clickgui.ClickGuiMain;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class ClickGui extends Module {

    public Setting guiHeight = setting("GuiHeight", 120, 0, 300);
    public Setting cfont = setting("CustomFont", false);

    Setting testSlider1 = setting("Sliderz", 4, 0, 10);
    Setting testBool = setting("Boolean", true);
    Setting mode;

    public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.CLIENT);
        instance = this;
        ArrayList<String> options = new ArrayList<>();
            options.add("one");
            options.add("two");
            options.add("three");
        mode = setting("Mode", "one", options);
        add(guiHeight);
        add(testSlider1);
        add(testBool);
        add(mode);
        add(cfont);
    }

    public static ClickGui instance;

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGuiMain());
        super.onEnable();
    }

    @Override
    public void onUpdate() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            this.toggle();
            Config.saveConfig();
        }
        super.onUpdate();
    }

    @Override
    public void onDisable() {
        Config.saveConfig();
        super.onDisable();
    }
}
