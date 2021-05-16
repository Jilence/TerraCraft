package de.docbrumm.terracraft.inventorys;

import com.google.gson.JsonObject;
import de.docbrumm.terracraft.TerraCraft;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public abstract class TerraInventory<T> implements Listener {

    public HashMap<Player, Inventory> inventories = new HashMap();

    int height;
    String title;

    public TerraInventory(int height, String title) {
        this.height = height;
        this.title = title;
        Bukkit.getPluginManager().registerEvents(this, TerraCraft.getInstance());
    }

    public void openInventory(Player player) {
        JsonObject usersObject = TerraCraft.getInstance().getUsers().get(player).getLanguage().object();
        Inventory inventory = Bukkit.createInventory(player, 9*height, Component.text(usersObject.get(title).getAsString()));
        renderPage(inventory, 0, player, usersObject, getContentOfPage(0));
        player.openInventory(inventory);
    }

    private List<T> getContentOfPage(int searchingPage) {
        ArrayList<T> output = new ArrayList<>();
        int start = 9*height*searchingPage;
        int stop = (9*height*searchingPage) + 9*height;
        content.forEach((index, item) -> {
            if(index <= start && index <= stop) {
                output.add(item);
            }
        });
        return output;
    }

    @EventHandler
    public void onCloseListener(InventoryCloseEvent event) {
        if(!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        if (inventories.containsKey(player)) {
            inventories.remove(player);
            onClose(event.getInventory(), player);
        }
    }

    @EventHandler
    public void onClickListener(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getWhoClicked() == null || !(event.getWhoClicked() instanceof Player || !inventories.containsKey(event.getWhoClicked()))) {
            return;
        }
        event.setCancelled(true);
        onClick(inventories.get(event.getWhoClicked()), (Player) event.getWhoClicked(), event.getCurrentItem());
    }

    public abstract void renderPage(Inventory inventory, int page, Player player, JsonObject object, List<T> contentOfPage);
    public abstract void onClick(Inventory inventory, Player player, ItemStack itemStack);
    public abstract void onClose(Inventory inventory, Player player);

    public HashMap<Integer, T> content = new HashMap();

    public void appentT(T item) {
        content.put(content.size(), item);
    }

}
