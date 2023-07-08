package com.canon.majik.api.core;

import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;
import com.canon.majik.impl.setting.SettingType;
import net.minecraft.client.Minecraft;
import org.json.JSONObject;

import java.io.*;

public class Config {

    static JSONObject config = new JSONObject();

    static File configFile = new File(getDir() + new File("modules.json"));


    public static void saveConfig() {


        for (Module m : Initializer.moduleManager.getModules()) {
            JSONObject settings = new JSONObject();

            for (Setting setting : m.getSettings()) {


                if (setting.getSettingType() == SettingType.BOOL) {
                    settings.put(setting.getName(), setting.getBoolean());
                } else if (setting.getSettingType() == SettingType.NUMBER) {
                    settings.put(setting.getName(), setting.getNumber());
                } else if (setting.getSettingType() == SettingType.MODE) {
                    settings.put(setting.getName(), setting.getMode());
                }

            }
            settings.put("key", m.getKey());
            settings.put("toggled", m.isEnabled());

            config.put(m.getName(), settings);
        }

        System.out.println(config.toString());

        try {
            configFile.getParentFile().mkdirs();
            configFile.createNewFile();


            FileWriter file = new FileWriter(configFile);
            file.write(config.toString());
            file.close();
            System.out.println("Saved config successfully");
        } catch (IOException e) {
            System.out.println("Failed to save config");
            e.printStackTrace();
        }
    }

    public static void loadConfig() {


        try {
            configFile.createNewFile();
        } catch (IOException ignored) {

        }

        JSONObject config = readJSONFile(configFile.getAbsolutePath());

        if (config == null) {
            return;
        }

        for (Module m : Initializer.moduleManager.getModules()) {

            JSONObject settings = null;

            try {
                settings = config.getJSONObject(m.getName());
            } catch (Exception e) {
                System.out.println(String.format("Module %s has not been initialized yet", m.getName()));
            }

            if (settings == null){
                continue;
            }

            for (Setting setting : m.getSettings()) {
                String settingName = setting.getName();

                if (setting.getSettingType() == SettingType.BOOL) {
                    setting.setBoolean(settings.getBoolean(settingName));
                } else if (setting.getSettingType() == SettingType.NUMBER) {
                    setting.setNumber(settings.getDouble(settingName));
                } else if (setting.getSettingType() == SettingType.MODE) {
                    setting.setMode(settings.getString(settingName));
                }
            }
            //avoid nullpointers for things defined on enabled
            m.setToggle(true);

            m.setToggle(settings.getBoolean("toggled"));
            m.setKey(settings.getInt("key"));

        }
    }


    private static JSONObject readJSONFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);
            String jsonString = reader.readLine();
            JSONObject json = new JSONObject(jsonString);
            reader.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getDir() {
        return Minecraft.getMinecraft().gameDir.getAbsoluteFile() + "/majik/";
    }
}
