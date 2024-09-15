package nl.inferno.lobbyCore.Items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {
    private static final String INVENTORY_TITLE = ChatColor.BLUE + "Player Profile";
    private static final int INVENTORY_SIZE = 54;

    public static void openProfileGUI(Player player) {
        Inventory profileInventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_TITLE);

        // Player head
        ItemStack playerHead = createPlayerHead(player);
        profileInventory.setItem(4, playerHead);

        // Statistics
        ItemStack statsItem = createItem(Material.BOOK, ChatColor.GOLD + "Statistics",
                ChatColor.GRAY + "Kills: " + getPlayerKills(player),
                ChatColor.GRAY + "Deaths: " + getPlayerDeaths(player),
                ChatColor.GRAY + "K/D Ratio: " + getPlayerKDRatio(player));
        profileInventory.setItem(20, statsItem);

        // Achievements
        ItemStack achievementsItem = createItem(Material.GOLDEN_APPLE, ChatColor.GOLD + "Achievements",
                ChatColor.GRAY + "Click to view your achievements");
        profileInventory.setItem(22, achievementsItem);

        // Settings
        ItemStack settingsItem = createItem(Material.REDSTONE_TORCH, ChatColor.GOLD + "Settings",
                ChatColor.GRAY + "Click to change your settings");
        profileInventory.setItem(24, settingsItem);

        // Friends
        ItemStack friendsItem = createItem(Material.PLAYER_HEAD, ChatColor.GOLD + "Friends",
                ChatColor.GRAY + "Click to manage your friends");
        profileInventory.setItem(38, friendsItem);

        // Cosmetics
        ItemStack cosmeticsItem = createItem(Material.CHEST, ChatColor.GOLD + "Cosmetics",
                ChatColor.GRAY + "Click to view your cosmetics");
        profileInventory.setItem(40, cosmeticsItem);

        // Ranks
        ItemStack ranksItem = createItem(Material.DIAMOND, ChatColor.GOLD + "Ranks",
                ChatColor.GRAY + "Your current rank: " + getPlayerRank(player));
        profileInventory.setItem(42, ranksItem);

        player.openInventory(profileInventory);
    }

    private static ItemStack createPlayerHead(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(ChatColor.YELLOW + player.getName() + "'s Profile");
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemStack createItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> loreList = new ArrayList<>();
        for (String line : lore) {
            loreList.add(line);
        }
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    // Placeholder methods - replace these with actual data retrieval methods
    private static int getPlayerKills(Player player) {
        return 100; // Example value
    }

    private static int getPlayerDeaths(Player player) {
        return 50; // Example value
    }

    private static double getPlayerKDRatio(Player player) {
        return 2.0; // Example value
    }

    private static String getPlayerRank(Player player) {
        return "VIP"; // Example value
    }
}
