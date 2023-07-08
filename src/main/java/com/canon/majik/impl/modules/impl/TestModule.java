package com.canon.majik.impl.modules.impl;

import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;
import org.lwjgl.input.Keyboard;

public class TestModule extends Module {

    Setting test = setting("TestBool", true);

    public TestModule() {
        super("TestModule", Keyboard.KEY_R, Category.CLIENT);
        add(test);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
