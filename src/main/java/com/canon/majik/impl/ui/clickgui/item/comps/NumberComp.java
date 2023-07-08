package com.canon.majik.impl.ui.clickgui.item.comps;

import com.canon.majik.api.core.Initializer;
import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.ui.clickgui.item.SettingComps;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComp extends SettingComps {
    public NumberComp(Setting object, int x, int y, int width, int height) {
        super(object, x, y, width, height);
    }
    private boolean sliding = false;
    int renderWidthAnimation=0;

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        double diff = Math.min(width, Math.max(0, mouseX - x));
        int renderWidth = (int) (width * (getObject().getNumber().floatValue() - getObject().getMin().floatValue()) / (getObject().getMax().floatValue() - getObject().getMin().floatValue()));

        if (renderWidthAnimation<renderWidth)renderWidthAnimation++;
        if (renderWidthAnimation<renderWidth)renderWidthAnimation++;	//my eyes
        if (renderWidthAnimation<renderWidth)renderWidthAnimation++;
        if (renderWidthAnimation>renderWidth)renderWidthAnimation--;
        if (renderWidthAnimation>renderWidth)renderWidthAnimation--;
        if (renderWidthAnimation>renderWidth)renderWidthAnimation--;

        RenderUtils.rect(x, y, width, height, 0x80000000);
        RenderUtils.rect(x, y, renderWidthAnimation, height, 0xFF2B71F3);
        if(ClickGui.instance.cfont.getBoolean()){
            Initializer.CFont.drawString(getObject().getName() + "  " + getObject().getNumber(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }else {
            mc.fontRenderer.drawStringWithShadow(getObject().getName() + "  " + getObject().getNumber(), x + 5, y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f, getObject().getBoolean() ? 0xFF2B71F3 : -1);
        }
        if (sliding) {
            if (diff==0) {
                getObject().setNumber(getObject().getMin());
            } else {
                getObject().setNumber(roundToPlace((diff / width) * (getObject().getMax().floatValue() - getObject().getMin().floatValue()) + getObject().getMin().floatValue(), 2));
            }
        }
        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0)
                sliding = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        sliding = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    private double roundToPlace(double value, int place) {
        if (place < 0) {
            return value;
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
