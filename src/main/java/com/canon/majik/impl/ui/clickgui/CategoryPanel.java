package com.canon.majik.impl.ui.clickgui;

import com.canon.majik.api.core.Initializer;
import com.canon.majik.api.utils.render.RenderUtils;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.modules.impl.client.ClickGui;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    private final Category category;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int dragX;
    private int dragY;
    private boolean dragging;
    private final Minecraft mc;
    private final List<ModuleButton> moduleButtons = new ArrayList<>();
    private boolean open = true;
    private int lineHeight;

    public CategoryPanel(Category category, int x, int y, int width, int height, Minecraft mc) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
        this.dragX = 0;
        this.dragY = 0;
        this.mc = mc;

        for (Module module : Initializer.moduleManager.getModules()) {
            if (module.getCategory() == this.category) {
                moduleButtons.add(new ModuleButton(module, x, y, width, height, mc));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.rect(x, y, width, height, 0xFF2B71F3);
        if(ClickGui.instance.cfont.getBoolean()){
            Initializer.CFont.drawString(category.name(),
                    x + width / 2f - mc.fontRenderer.getStringWidth(category.name()) / 2f,
                    y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f,
                    -1);
        }else {
            mc.fontRenderer.drawStringWithShadow(category.name(),
                    x + width / 2f - mc.fontRenderer.getStringWidth(category.name()) / 2f,
                    y + height / 2f - mc.fontRenderer.FONT_HEIGHT / 2f,
                    -1);
        }
        RenderUtils.invokeScissor(x, y+15, x+width, 200);
        if (this.open) {
            int offset = height;
            for (ModuleButton moduleButton : this.moduleButtons) {
                offset += moduleButton.drawScreen(mouseX, mouseY, partialTicks, offset);
                if(boundingArea(mouseX,mouseY)){
                    scrollWheelCheck();
//                    moduleButton.scrollWheelCheck();
                }
            }
        }
        RenderUtils.releaseScissor();
        if(moduleButtons.isEmpty() && open){
            lineHeight = 27;
        } else if (open) {
            lineHeight = 200;
        }else{
            lineHeight = 27;
        }
        RenderUtils.drawOutlineRect(x,y,x+width, lineHeight,new Color(0xFF2B71F3), 2f);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        moduleButtons.forEach(moduleButton -> moduleButton.mouseClicked(mouseX, mouseY, mouseButton));

        if (boundingPanel(mouseX, mouseY) && mouseButton == 1) this.open = !this.open;
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (this.open) moduleButtons.forEach(moduleButton -> moduleButton.keyTyped(typedChar, keyCode));
    }


    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.open)
            moduleButtons.forEach(moduleButton -> moduleButton.mouseReleased(mouseX, mouseY, state));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updatePosition(int x, int y) {
        if (!dragging) return;
        setX(x - dragX);
        setY(y - dragY);
    }

    public boolean boundingArea(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y) return false;
        return mouseY <= this.y + 120;
    }


    public boolean boundingPanel(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y) return false;
        return mouseY <= this.y + this.height;
    }

    public void scrollWheelCheck() {
        int dWheel = Mouse.getDWheel();
        if(dWheel < 0) moduleButtons.forEach(panel -> panel.setY((int) RenderUtils.scrollAnimate(panel.getY() + 10,panel.getY(), 0.5f)));
        else if(dWheel > 0) moduleButtons.forEach(panel -> panel.setY((int) RenderUtils.scrollAnimate(panel.getY() - 10,panel.getY(), 0.5f)));
    }
}
