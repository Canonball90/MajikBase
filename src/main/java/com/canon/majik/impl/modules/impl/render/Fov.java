package com.canon.majik.impl.modules.impl.render;

import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;

public class Fov extends Module {

    Setting fov = setting("Amount", 90, 0, 180);

    public Fov() {
        super("Fov", Category.RENDER);
        add(fov);
    }

    @Override
    public void onUpdate() {
        mc.gameSettings.fovSetting = fov.getNumber().floatValue();
        super.onUpdate();
    }

    @Override
    public void onDisable() {
        mc.gameSettings.fovSetting = mc.gameSettings.fovSetting;
        super.onDisable();
    }
}
