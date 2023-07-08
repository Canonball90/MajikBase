package com.canon.majik.impl.ui.clickgui.item.comps;

import com.canon.majik.api.core.Initializer;
import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.ui.clickgui.item.SettingComps;

public class ModeComp extends SettingComps {
    public ModeComp(Setting object, int x, int y, int width, int height) {
        super(object, x, y, width, height);
    }
    private int modeIndex;

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        RenderUtils.rect(x, y, width, height, 0x80000000);
        if(ClickGui.instance.cfont.getBoolean()){
            Initializer.CFont.drawString(getObject().getName() + ": " + getObject().getMode(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }else {
            mc.fontRenderer.drawStringWithShadow(getObject().getName() + ": " + getObject().getMode(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }
        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            int maxIndex = 0;
            if (mouseButton == 0) {
                maxIndex = getObject().getOptions().size() - 1;
                ++this.modeIndex;
                if (this.modeIndex > maxIndex) {
                    this.modeIndex = 0;
                }
                this.getObject().setMode(this.getObject().getOptions().get(this.modeIndex));
            }

        }
    }
}
