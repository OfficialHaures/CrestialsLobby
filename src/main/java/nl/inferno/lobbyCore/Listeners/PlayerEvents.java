package nl.inferno.lobbyCore.Listeners;

import nl.inferno.lobbyCore.Items.Profile;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        givePlayerItems(player);
        player.setGameMode(GameMode.ADVENTURE);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (item != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK ||
                action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)) {
            if (item.getType() == Material.PLAYER_HEAD && item.hasItemMeta() &&
                    item.getItemMeta().getDisplayName().contains("Profile")) {
                event.setCancelled(true);
                Profile.openProfileGUI(player);
            }
        }
    }

    private void givePlayerItems(Player player) {
        player.getInventory().clear();

        ItemStack item1 = createItem(Material.COMPASS, "Game Selector");
        ItemStack item2 = createItem(Material.NETHER_STAR, "Lobby Selector");
        ItemStack item3 = Profile.createItem(Material.PLAYER_HEAD, "Profile", "Click to view your profile");

        player.getInventory().setItem(0, item1);
        player.getInventory().setItem(4, item2);
        player.getInventory().setItem(8, item3);
    }

    private ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
