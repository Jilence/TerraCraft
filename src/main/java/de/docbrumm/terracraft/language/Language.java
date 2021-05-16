package de.docbrumm.terracraft.language;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

/**
 * A Dataclass for the Languages.
 *
 * @param base64 A Base64 String that will be appended to the Head
 * @param fileName How the file is called in './plugins/TerraCraft/languages/'
 * @param name How the Language is called.
 */

public record Language(String fileName, String name, String base64, JsonObject object) {

    public ItemStack getHead() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        UUID hashAsId = new UUID(base64.hashCode(), base64.hashCode());
        Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64 + "\"}]}}}"
        );
        SkullMeta itemMeta = (SkullMeta) skull.getItemMeta();
        itemMeta.setDisplayName(name);
        skull.setItemMeta(itemMeta);
        return skull;
    }
}
