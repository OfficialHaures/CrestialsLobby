package nl.inferno.lobbyCore.Listeners;

import nl.inferno.lobbyCore.LobbyCore;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class AntiTNTListener implements Listener {

    private final LobbyCore plugin;

    public AntiTNTListener(LobbyCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
        List<Block> blocks = event.blockList();

        if (!blocks.isEmpty()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                for (Block block : blocks) {
                    block.getState().update(true, false);
                }
            }, 100L);
        }
    }
}
