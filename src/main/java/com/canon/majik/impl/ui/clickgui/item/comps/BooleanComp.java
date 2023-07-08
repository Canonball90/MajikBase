package com.canon.majik.impl.ui.clickgui.item.comps;

import com.canon.majik.api.core.Initializer;
import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.ui.clickgui.item.SettingComps;

import java.awt.*;

public class BooleanComp extends SettingComps {
    public BooleanComp(Setting object, int x, int y, int width, int height) {
        super(object, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        RenderUtils.rect(x, y, width, height, 0x80000000);
        if(ClickGui.instance.cfont.getBoolean()){
            Initializer.CFont.drawString(getObject().getName(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }else{
            mc.fontRenderer.drawStringWithShadow(getObject().getName(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }

        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0)
                getObject().setBoolean(!getObject().getBoolean());
        }
    }
}
