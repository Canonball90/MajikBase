package com.canon.majik.impl.setting;

import com.canon.majik.impl.modules.api.Module;

import java.awt.*;
import java.util.ArrayList;

public class Setting {

    private String name;
    private Module parent;
    private SettingType settingType;

    private boolean bVal;

    private Number nVal;
    private Number minVal;
    private Number maxVal;

    private String sval;
    private ArrayList<String> options;

    private Color cVal;

    public Setting(String name, boolean bVal, Module parent){
        this.name = name;
        this.bVal = bVal;
        this.parent = parent;
        this.settingType = SettingType.BOOL;
    }

    public Setting(String name, Number nVal, Number minVal, Number maxVal, Module parent){
        this.name = name;
        this.nVal = nVal;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.parent = parent;
        this.settingType = SettingType.NUMBER;
    }

    public Setting(String displayName, Module parent, String sval, ArrayList<String> options){
        this.name = displayName;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.settingType = SettingType.MODE;
    }

    public Setting(String name, Color cVal, Module parent){
        this.name = name;
        this.cVal = cVal;
        this.parent = parent;
        this.settingType = SettingType.COLOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public SettingType getSettingType() {
        return settingType;
    }

    public void setSettingType(SettingType settingType) {
        this.settingType = settingType;
    }

    public boolean getBoolean() {
        return bVal;
    }

    public void setBoolean(boolean bVal) {
        this.bVal = bVal;
    }

    public Number getNumber() {
        return nVal;
    }

    public void setNumber(Number nVal) {
        this.nVal = nVal;
    }

    public Number getMin() {
        return minVal;
    }

    public void setMin(Number minVal) {
        this.minVal = minVal;
    }

    public Number getMax() {
        return maxVal;
    }

    public void setMax(Number maxVal) {
        this.maxVal = maxVal;
    }

    public String getMode(){
        return this.sval;
    }

    public void setMode(String in){
        this.sval = in;
    }

    public ArrayList<String> getOptions(){
        return this.options;
    }

    public Color getColor() {
        return cVal;
    }

    public void setColor(Color cVal) {
        this.cVal = cVal;
    }

}
