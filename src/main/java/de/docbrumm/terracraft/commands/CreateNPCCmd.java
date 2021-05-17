package de.docbrumm.terracraft.commands;

import com.comphenix.protocol.PacketType;
import de.docbrumm.terracraft.npc.NPC;
import de.docbrumm.terracraft.util.ConfigUtil;
import de.docbrumm.terracraft.util.ItemBuilder;
import de.docbrumm.terracraft.util.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreateNPCCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.isOp()){

                ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);

                new NPC(player, "TestNPC", player.getLocation(), new Util().getSkinDataByPlayerName("MoMMde"), new ConfigUtil(ConfigUtil.Configs.NPC_CONFIG)).create(true, itemStack);
            }
        }
        return false;
    }
}
