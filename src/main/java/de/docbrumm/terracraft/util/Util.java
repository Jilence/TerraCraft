package de.docbrumm.terracraft.util;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.fllip.entity.api.entity.type.fakeplayer.SkinData;

import java.io.InputStreamReader;
import java.net.URL;

public class Util {

    public SkinData getSkinDataByPlayerName(String playerName){
        try {
            URL url = new URL("https://apimojang.com/users/profiles/minecraft" + playerName);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile" + uuid + "?unsigned=false");
            InputStreamReader reader2 = new InputStreamReader(url2.openStream());
            JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();

            String value = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();

            return new SkinData(value, signature);

        }catch (Exception e){
            return null;
        }
    }

}
