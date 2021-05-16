package de.docbrumm.terracraft.inventorys;

import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.language.Language;
import de.docbrumm.terracraft.user.User;
import de.docbrumm.terracraft.util.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LanguageInventory {








    public void openGUI(Player player){

        User user = new User(Objects.requireNonNull(player).getUniqueId());


        String title = user.getLanguage().object().get("languageInventoryTitle").getAsString();

        Inventory inventory = Bukkit.createInventory(null, 3 * 9, Component.text(title));
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("").setLore("").addFlag(ItemFlag.HIDE_ATTRIBUTES).build());
        }

        int i = 11;

        for(String languageName : TerraCraft.getInstance().getLanguages().keySet()){
            Language language = TerraCraft.getInstance().getLanguages().get(languageName);
            inventory.setItem(i, language.getHead());
            i++;
        }

        player.openInventory(inventory);
    }


}
