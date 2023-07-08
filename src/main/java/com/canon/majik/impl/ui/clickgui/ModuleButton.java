package com.canon.majik.impl.ui.clickgui;

import com.canon.majik.api.core.Initializer;
import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.setting.SettingType;
import com.canon.majik.impl.ui.clickgui.item.comps.ModeComp;
import com.canon.majik.impl.ui.clickgui.item.comps.NumberComp;
import com.canon.majik.impl.ui.clickgui.item.items.ItemKeyBind;
import com.canon.majik.impl.ui.clickgui.item.Item;
import com.canon.majik.impl.ui.clickgui.item.SettingComps;
import com.canon.majik.impl.ui.clickgui.item.comps.BooleanComp;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    private final Module module;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final Minecraft mc;
    private int offset;
    public boolean open;
    private final List<Item<?>> items = new ArrayList<>();
    private final List<SettingComps> setts = new ArrayList<>();

    public ModuleButton(Module module, int x, int y, int width, int height, Minecraft mc) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mc = mc;

        for (Setting setting : module.getSettings()) {
            if (setting.getSettingType() == SettingType.BOOL) {
                setts.add(new BooleanComp(setting, x, y, width, height));
            }
            if(setting.getSettingType() == SettingType.NUMBER){
                setts.add(new NumberComp(setting,x,y,width,height));
            }
            if(setting.getSettingType() == SettingType.MODE){
                setts.add(new ModeComp(setting,x,y,width,height));
            }
        }

        items.add(new ItemKeyBind(module, x, y, width, height));
    }

    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        int y = this.y + offset;
        RenderUtils.rect(x, y, width, height, 0x80000000);
        if(ClickGui.instance.cfont.getBoolean()){
            Initializer.CFont.drawString(module.getName(), x + 3, y + (height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f), this.module.isEnabled() ? 0xFF2B71F3 : -1);
        }else {
            mc.fontRenderer.drawStringWithShadow(module.getName(), x + 3, y + (height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f), this.module.isEnabled() ? 0xFF2B71F3 : -1);
        }
        int offsets = height;
        if (open) {
            for (Item<?> item : items) {
                offsets += item.drawScreen(mouseX, mouseY, partialTicks, offsets + offset);
            }
            for(SettingComps settingComps : setts){
                offsets += settingComps.drawScreen(mouseX, mouseY, partialTicks, offsets + offset);
            }
        }

        return offsets;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)){
            if (mouseButton == 0) {
                this.module.toggle();
            } else if (mouseButton == 1) {
                this.open = !this.open;
            }
        }

        if (open) {
            items.forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
            setts.forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (open) {
            items.forEach(item -> item.keyTyped(typedChar, keyCode));
            setts.forEach(item -> item.keyTyped(typedChar, keyCode));
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (open) {
            items.forEach(item -> item.mouseReleased(mouseX, mouseY, state));
            setts.forEach(item -> item.mouseReleased(mouseX, mouseY, state));
        }
    }

    public int getY() {
        return y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean bounding(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y + this.offset) return false;
        return mouseY <= this.y + this.offset + this.height;
    }

}
