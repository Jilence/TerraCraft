package de.docbrumm.terracraft.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemBuilder {

    private ItemStack itemStack;

    private ArrayList<Component> loreArray = new ArrayList<>();


    /**
     * create an itemstack with material and amount
     *
     * @param material Material that the itemstack should have
     * @param amount   amount of the item
     */
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * create an itemstack with material and amount
     *
     * @param itemStack existing ItemStack that the ItemBuilder should use
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * build the ItemStack
     *
     * @return the ItemStack
     */
    public ItemStack build() {
        return this.itemStack;
    }

    /**
     * set the DisplayName of the ItemStack
     *
     * @param displayName DisplayName for the ItemStack
     */
    public ItemBuilder setName(String displayName) {
        setMeta(m -> m.displayName(Component.text(displayName)));
        return this;
    }

    /**
     * add a Lore line to the ItemStack
     *
     * @param lore Lore line
     */
    public ItemBuilder addLoreLine(String lore) {
        setMeta(m -> {
            if (!m.hasLore()) {
                m.lore(new ArrayList<>());
            }
            m.lore().add(Component.text(lore));
        });
        return this;
    }

    /**
     * add glow effect to the ItemStack
     */
    public ItemBuilder glow() {
        itemStack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        itemStack.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * set the Skull Meta to a UUID
     *
     * @param uuid uuid of the Player
     */
    public ItemBuilder setSkullOwner(UUID uuid) {
        setMeta(SkullMeta.class, m -> m.setOwningPlayer(Bukkit.getOfflinePlayer(uuid)));
        return this;
    }

    public <T extends ItemMeta> ItemBuilder setMeta(Class<T> clazz, Consumer<@NotNull T> consumer) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return this;
        if (clazz.isAssignableFrom(itemMeta.getClass())) {
            consumer.accept((T) itemMeta);
        }
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public <T extends ItemMeta> ItemBuilder setMeta(Consumer<ItemMeta> meta) {
        return setMeta(ItemMeta.class, meta);
    }

    /**
     * set the Amount of the ItemStack
     *
     * @param amount amount of the ItemStack
     */
    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }
}