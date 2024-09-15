package nl.inferno.lobbyCore.NPC;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;
import nl.inferno.lobbyCore.LobbyCore;

import java.util.Arrays;

public class NpcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Deze command kan alleen door spelers worden gebruikt.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Gebruik: /spawnnpc <naam> <command>");
            return true;
        }

        String npcName = args[0];
        String npcCommand = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        Location location = player.getLocation();

        Villager npc = (Villager) player.getWorld().spawnEntity(location, EntityType.VILLAGER);
        npc.setCustomName(npcName);
        npc.setCustomNameVisible(true);
        npc.setAI(false);
        npc.setInvulnerable(true);
        npc.setMetadata("npcCommand", new FixedMetadataValue(LobbyCore.plugin, npcCommand));

        player.sendMessage("NPC " + npcName + " is gespawnd op jouw locatie met command: " + npcCommand);
        return true;
    }
}
