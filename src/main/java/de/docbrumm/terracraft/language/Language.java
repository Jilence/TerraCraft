package de.docbrumm.terracraft.language;

import com.google.gson.JsonObject;
import de.docbrumm.terracraft.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
        ItemStack skull = new ItemBuilder(Material.PLAYER_HEAD, 1).setName(name).addLoreLine("§7Click here to select §2" + name).build();
        UUID hashAsId = new UUID(base64.hashCode(), base64.hashCode());
        Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64 + "\"}]}}}"
        );
        return skull;
    }
}
