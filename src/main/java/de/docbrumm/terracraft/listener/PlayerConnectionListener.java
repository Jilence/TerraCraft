package de.docbrumm.terracraft.listener;

import de.docbrumm.terracraft.NPC;
import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.inventorys.LanguageInventory;
import de.docbrumm.terracraft.language.Language;
import de.docbrumm.terracraft.user.User;
import de.docbrumm.terracraft.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        User user = new User(
                player.getUniqueId(),
                TerraCraft.getInstance().getLanguages().get("English"),
                0,
                new NPC(
                        player,
                        player.getName(),
                        player.getLocation(),
                        "",
                        new ConfigUtil(ConfigUtil.Configs.NPC_CONFIG)
                ),
                new ConfigUtil(ConfigUtil.Configs.PLAYER_CONFIG)
        );
        TerraCraft.getInstance().getUsers().put(player, user);
        System.out.println(user.getLanguage().name());
        if(!user.exists()) {
            new LanguageInventory().openInventory(player);
        }
        user.loadIfPlayedBefore();
        player.teleport(player.getWorld().getHighestBlockAt(0, 0).getLocation().add(0, 1, 0));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        TerraCraft.getInstance().getUsers().get(event.getPlayer()).save();
    }
}
