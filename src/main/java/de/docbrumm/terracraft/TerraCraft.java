package de.docbrumm.terracraft;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import de.docbrumm.terracraft.language.Language;
import de.docbrumm.terracraft.listener.PlayerConnectionListener;
import de.docbrumm.terracraft.user.User;
import de.docbrumm.terracraft.world.WorldGenerator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class TerraCraft extends JavaPlugin {

    private final HashMap<String, Language> languages = new HashMap<>();
    private HashMap<Player, User> users = new HashMap<>();

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        return new WorldGenerator();
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        if (!initializeLanguages()) {
            getLogger().warning("Unable to initialize Languages.");
            getLogger().warning("Enable Debug mode for more Information.");
            return;
        }
        getLogger().info("Loaded Languages [§a" + languages.size() + "§r].");
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(), this);
    }

    @Override
    public void onDisable() {
        
    }

    public boolean initializeLanguages() {
        try {
            saveResource("languages/english.json", false);
            Arrays.stream(new File("plugins/" + getName() + "/languages/").listFiles()).forEach(file -> {
                try {
                    JsonObject object = new JsonParser().parse(new JsonReader(new FileReader(file))).getAsJsonObject();
                    JsonObject properties = object.getAsJsonObject("properties");
                    languages.put(properties.get("name").getAsString(), new Language(file.getName(), properties.get("name").getAsString(), properties.get("base64").getAsString(), object));
                } catch (FileNotFoundException ignored) {}
            });
            return true;
        } catch (Exception e) {
            if (getConfig().getBoolean("debug")) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static TerraCraft getInstance() {
        return getPlugin(TerraCraft.class);
    }

    public HashMap<String, Language> getLanguages() {
        return languages;
    }

    public HashMap<Player, User> getUsers() {
        return users;
    }
}
