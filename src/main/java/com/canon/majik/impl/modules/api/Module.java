package com.canon.majik.impl.modules.api;

import com.canon.majik.api.utils.Globals;
import com.canon.majik.api.utils.client.ChatUtils;
import com.canon.majik.impl.setting.Setting;
import net.minecraftforge.common.MinecraftForge;

import java.awt.*;
import java.util.ArrayList;

public class Module implements Globals {

    private String name,description;
    private boolean toggle;
    private int key;
    private Category category;
    ArrayList<Setting> settings = new ArrayList<>();

    public Module(String name, Category category){
        this.name = name;
        this.category = category;
    }

    public Module(String name, int key, Category category){
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public Module(String name, String description, int key, Category category){
        this.name = name;
        this.description = description;
        this.key = key;
        this.category = category;
    }

    public ArrayList<Setting> getSettings(){
        return settings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;

        if(toggle){
            onEnable();
        }else{
            onDisable();
        }
    }

    public void toggle(){
        this.toggle = !toggle;

        if(this.toggle){
            onEnable();
        }else{
            onDisable();
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void onEnable(){
        MinecraftForge.EVENT_BUS.register(this);
        if(nullCheck()) return;
        ChatUtils.tempMessage(getName() + " enabled", 2);
    }

    public void onDisable(){
        MinecraftForge.EVENT_BUS.unregister(this);
        if(nullCheck()) return;
        ChatUtils.tempMessage(getName() + " disabled", 1);
    }

    public void onUpdate(){}

    public void onLoop(){}

    public void add(Setting setting){
        settings.add(setting);
    }

    //Boolean
    public Setting setting(String name, boolean val){
        Setting set = new Setting(name, val, this);
        return set;
    }

    //Number
    public Setting setting(String name, Number val, Number min, Number max){
        Setting set = new Setting(name,val,min,max,this);
        return set;
    }

    //Mode
    public Setting setting(String name, String val,ArrayList<String> vals){
        Setting set = new Setting(name,this,val,vals);
        return set;
    }

    //Color
    public Setting setting(String name, Color val){
        Setting set = new Setting(name, val, this);
        return set;
    }
}

