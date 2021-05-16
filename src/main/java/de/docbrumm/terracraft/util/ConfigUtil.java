package de.docbrumm.terracraft.util;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {


    private static File file;
    private static YamlConfiguration currentConfig;

    public enum Configs {

        NPC_CONFIG("./plugins/TerraCraft", "npcConfig.yml"),
        PLAYER_CONFIG("./plugins/TerraCraft", "playerConfig.yml");

        String path, name;

        Configs(String path, String name) {
            this.path = path;
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public String getName() {
            return name;
        }
    }


    public ConfigUtil(Configs config) {
        File dir = new File(config.getPath());
        if(!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, config.getName());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        currentConfig = YamlConfiguration.loadConfiguration(file);
    }

    public ConfigUtil getInstance() {
        return this;
    }

    public boolean contains(String path) {
        return currentConfig.contains(path);
    }

    public void set(String path, Object value) {
        currentConfig.set(path, value);
        try {
            currentConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object get(String path) {
        if(!contains(path)) {
            return null;
        }
        return currentConfig.get(path);
    }

    public String getString(String path){
        if(!contains(path)){
            return null;
        }
        return currentConfig.getString(path);
    }

    public boolean getBool(String path) {
        if(!contains(path)) {
            set(path, false);
        }
        return (boolean) currentConfig.get(path);
    }

    public void toggleBool(String path) {
        boolean bool = getBool(path);
        set(path, !bool);
    }



    public int getInt(String path) {
        if(!contains(path)) {
            set(path, 1);
        }
        return (int) currentConfig.get(path);
    }

    public int getInt(String path, int defaultInt) {
        if(!contains(path)) {
            set(path, defaultInt);
        }
        return (int) currentConfig.get(path);
    }

}
