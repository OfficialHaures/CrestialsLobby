package nl.inferno.lobbyCore.NPC;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;

public class NpcListeners implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();

        if (clickedEntity instanceof Villager && clickedEntity.hasMetadata("npcCommand")) {
            event.setCancelled(true);
            String npcName = clickedEntity.getCustomName();

            MetadataValue metadata = clickedEntity.getMetadata("npcCommand").get(0);
            String command = metadata.asString();

            // Voer de opgeslagen command uit
            Bukkit.dispatchCommand(player, command);

            player.sendMessage("Je hebt op NPC " + npcName + " geklikt en de command uitgevoerd: " + command);
        }
    }
}
