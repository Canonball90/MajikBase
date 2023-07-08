package com.canon.majik.impl.ui.clickgui.item.comps;

import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.ui.clickgui.item.SettingComps;

public class ColorComp extends SettingComps {
    public ColorComp(Setting object, int x, int y, int width, int height) {
        super(object, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        RenderUtils.rect(x, y, width, height, 0x80000000);
        mc.fontRenderer.drawStringWithShadow(getObject().getName(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);

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
