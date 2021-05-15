package de.docbrumm.terracraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class TerraInventory<T> implements Listener {

    int height = 3, start = 0, stop = height*9-1;
    String title = "undefined";
    HashMap<Player, Inventory> inventories = new HashMap();

    public TerraInventory(int height, String title) {
        this.height = height;
        this.title = title;
    }

    public TerraInventory(int height, String title, int start, int stop) {
        this.height = height;
        this.title = title;
    }

    public void appendContent(T contentItem) {
        System.out.println(content.size() / (start - stop));
        content.put(content.size() / (start - stop), contentItem);
    }

    private Inventory buildInventory(Player player) {
        return Bukkit.createInventory(player, height*9, title);
    }

    public void openInventory(Player player) {
        Inventory inventory = buildInventory(player);
        inventories.put(player, inventory);
        renderPage(player, 0, inventory, getContentOfPage(0));
        player.openInventory(inventory);
    }

    public void setContent(int page, T item) {
        content.put(page, item);
    }

    public abstract void renderPage(Player player, int page, Inventory inventory, List<T> content);

    private HashMap<Integer, T> content = new HashMap();

    public List<T> getContentOfPage(int page) {
        List<T> items = new ArrayList<>();
        for (Integer contentKey : content.keySet()) {
            if(contentKey == page) {
                items.add(content.get(contentKey));
            }
        }
        return items;
    }


}
