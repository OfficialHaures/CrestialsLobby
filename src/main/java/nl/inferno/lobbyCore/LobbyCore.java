package nl.inferno.lobbyCore;

import nl.inferno.lobbyCore.Listeners.AntiTNTListener;
import nl.inferno.lobbyCore.Listeners.PlayerEvents;
import nl.inferno.lobbyCore.NPC.NpcCommand;
import nl.inferno.lobbyCore.NPC.NpcListeners;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbyCore extends JavaPlugin {

    public static LobbyCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("spawnnpc").setExecutor(new NpcCommand());
        getServer().getPluginManager().registerEvents(new NpcListeners(), this);
        getServer().getPluginManager().registerEvents(new AntiTNTListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
