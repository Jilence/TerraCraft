package de.docbrumm.terracraft.listener;

import de.docbrumm.terracraft.inventorys.LanguageInventory;
import de.docbrumm.terracraft.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        User user = new User(player.getUniqueId());

        if(!user.exist()){
            user.setLanguage("English");
            new LanguageInventory().openGUI(player);
            player.sendMessage("User ! exists");
        }else {
                if(user.hasNPC()){
                    player.sendMessage("Has NPC");
                }else {
                    player.sendMessage("open NPC Inv");
                }

        }


    }
}
