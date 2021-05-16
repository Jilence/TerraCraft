package de.docbrumm.terracraft.util;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material Item, short subID) {
        item = new ItemStack(Item, 1, subID);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material Item) {
        this(Item, (short)0);
    }


    public ItemBuilder setName(String Name) {
        meta.setDisplayName(Name);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int Lvl) {
        meta.addEnchant(enchantment, Lvl, true);
        return this;
    }
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }
    public ItemBuilder addFlag(ItemFlag Flag) {
        meta.addItemFlags(Flag);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setAmount(int Amount) {
        item.setAmount(Amount);
        return this;
    }
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}