package de.docbrumm.terracraft.gui.inventorys;

import de.docbrumm.terracraft.gui.TerraInventory;
import de.docbrumm.terracraft.language.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import de.docbrumm.terracraft.TerraCraft;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LanguageTerraInventory extends TerraInventory<Language> implements CommandExecutor {

    public LanguageTerraInventory() {
        super(5, "Language");
    }

    @Override
    public void renderPage(Player player, int page, Inventory inventory, List<Language> content) {

    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            openInventory((Player) commandSender);
        } else {
            commandSender.sendMessage("You need to be an Player");
        }
        return true;
    }
}
