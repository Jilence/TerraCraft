package de.docbrumm.terracraft.npc;

import de.docbrumm.terracraft.util.ConfigUtil;
import de.docbrumm.terracraft.util.Util;
import de.fllip.entity.api.EntityAPI;
import de.fllip.entity.api.configuration.FakePlayerEntityConfiguration;
import de.fllip.entity.api.entity.animation.EntityAnimationType;
import de.fllip.entity.api.entity.interact.EntityInteractAction;
import de.fllip.entity.api.entity.type.fakeplayer.SkinData;
import de.fllip.entity.api.entity.update.EntityUpdateType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class NPC {

    OfflinePlayer owner;
    String name;
    SkinData skin;
    Location location;
    ConfigUtil util;

    public NPC(OfflinePlayer owner,
               String name,
               Location location,
               SkinData skin,
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
        skin = (SkinData) get("skin");
        location = (Location) get("location");
    }


    private Object get(String key) {
        return util.get(owner.getUniqueId() + "." + key);
    }

    private void set(String key, Object any) {
        util.set(owner.getUniqueId() + "." + key, any);
    }
}
