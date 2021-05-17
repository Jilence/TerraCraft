package de.docbrumm.terracraft.listener;

import de.docbrumm.terracraft.npc.NPC;
import de.docbrumm.terracraft.TerraCraft;
import de.docbrumm.terracraft.inventorys.LanguageInventory;
import de.docbrumm.terracraft.user.User;
import de.docbrumm.terracraft.util.ConfigUtil;
import de.docbrumm.terracraft.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {
    
    LanguageInventory languageInventory = new LanguageInventory();

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
                        new Util().getSkinDataByPlayerName("l4zs"),
                        new ConfigUtil(ConfigUtil.Configs.NPC_CONFIG)
                ),
                new ConfigUtil(ConfigUtil.Configs.PLAYER_CONFIG)
        );
        TerraCraft.getInstance().getUsers().put(player, user);
        System.out.println(user.getLanguage().name());
        if(!user.exists()) {
            languageInventory.openInventory(player);
        }
        user.loadIfPlayedBefore();
        player.teleport(player.getWorld().getHighestBlockAt(0, 0).getLocation().add(0, 1, 0));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        TerraCraft.getInstance().getUsers().get(event.getPlayer()).save();
    }
}
