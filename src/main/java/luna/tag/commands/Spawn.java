package luna.tag.commands;

import luna.tag.management.MapManager;
import luna.tag.management.SpawnManagement;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class Spawn implements CommandExecutor {
    private MapManager mapManager = new MapManager();
    private SpawnManagement spawnManager = SpawnManagement.getInstance(mapManager.getCurrentMapName());

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.teleport(spawnManager.getDefaultSpawn(mapManager.getCurrentMapName()));
                return true;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(ChatColor.GREEN + "Spawn successfully set!");
                    spawnManager.setSpawn(args[1], player.getLocation());
                    return true;
                }
            } else if (args.length >= 3) {
                player.sendMessage(ChatColor.RED + "Please include the spawn name as a single word.");
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
