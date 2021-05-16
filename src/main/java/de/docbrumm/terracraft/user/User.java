package de.docbrumm.terracraft.user;

import de.docbrumm.terracraft.NPC;
import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.language.Language;
import de.docbrumm.terracraft.util.ConfigUtil;

import java.util.UUID;

public class User {

    UUID uuid;
    Language language;
    int level;
    NPC npc;
    ConfigUtil util;

    public User(UUID uuid,
                Language language,
                int level,
                NPC npc,
                ConfigUtil util) {
        this.uuid = uuid;
        this.language = language;
        this.level = level;
        this.npc = npc;
        this.util = util;
    }

    public boolean exists() {
        return util.contains(uuid.toString());
    }

    public void loadIfPlayedBefore() {
        if (exists()) {
            language = TerraCraft.getInstance().getLanguages().get(get("language"));
            level = (int) get("level");
        }
    }

    private Object get(String key) {
        return util.get(uuid.toString() + "." + key);
    }

    public void save() {
        save("language", language.name());
        save("level", level);
        npc.save();
    }

    private void save(String key, Object any) {
        util.set(uuid.toString() + "." + key, any);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Language getLanguage() {
        return language;
    }

    public int getLevel() {
        return level;
    }

    public NPC getNpc() {
        return npc;
    }

    public ConfigUtil getUtil() {
        return util;
    }
}