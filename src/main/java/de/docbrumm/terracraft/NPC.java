package de.docbrumm.terracraft;

import de.docbrumm.terracraft.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class NPC {

    OfflinePlayer owner;
    String name, skin;
    Location location;
    ConfigUtil util;

    public NPC(OfflinePlayer owner,
               String name,
               Location location,
               String skin,
               ConfigUtil configUtil) {
        this.owner = owner;
        this.name = name;
        this.location = location;
        this.skin = skin;
        this.util = configUtil;
    }

    public NPC(OfflinePlayer player) {
        owner = player;
        loadData();
    }

    public void save() {
        set("name", name);
        set("location", location);
        set("skin", skin);
    }

    public void loadData() {
        name = (String) get("name");
        skin = (String) get("skin");
        location = (Location) get("location");
    }

    private Object get(String key) {
        return util.get(owner.getUniqueId() + "." + key);
    }

    private void set(String key, Object any) {
        util.set(owner.getUniqueId() + "." + key, any);
    }
}
