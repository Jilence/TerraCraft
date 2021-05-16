package de.docbrumm.terracraft.inventorys;

import com.google.gson.JsonObject;
import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.language.Language;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LanguageInventory extends TerraInventory<Language> {

    public LanguageInventory() {
        super(4, "language_inventory");
        for (String key : TerraCraft.getInstance().getLanguages().keySet()) {
            appentT(TerraCraft.getInstance().getLanguages().get(key));
        }
    }

    @Override
    public void renderPage(Inventory inventory, int page, Player player, JsonObject object, List<Language> contentOfPage) {
        AtomicInteger i = new AtomicInteger();
        contentOfPage.forEach(language -> {
            inventory.setItem(i.get(), language.getHead());
            i.getAndIncrement();
        });
    }

    @Override
    public void onClick(Inventory inventory, Player player, ItemStack itemStack) {
        player.sendMessage(itemStack.getI18NDisplayName());
    }

    @Override
    public void onClose(Inventory inventory, Player player) {

    }
}
