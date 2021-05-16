package de.docbrumm.terracraft.user;

import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.language.Language;
import de.docbrumm.terracraft.util.ConfigUtil;

import java.util.UUID;

public class User {

    UUID uuid;


    public User(UUID uuid){
        this.uuid = uuid;
    }

    public Language getLanguage(){

        return TerraCraft.getInstance().getLanguage(new ConfigUtil(ConfigUtil.Configs.PLAYER_CONFIG).getString(uuid + ".language"));

    }

    public boolean hasNPC(){
        return new ConfigUtil(ConfigUtil.Configs.NPC_CONFIG).contains(String.valueOf(uuid));

    }

    public boolean hasLanguage(){
        return getLanguage() != null;
    }

    public boolean exist(){
        return new ConfigUtil(ConfigUtil.Configs.PLAYER_CONFIG).contains(String.valueOf(uuid));

    }

    public void setLanguage(String language){
        new ConfigUtil(ConfigUtil.Configs.PLAYER_CONFIG).set(uuid + ".language", language);
    }

}
