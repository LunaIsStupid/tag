package luna.tag.commands;

import luna.tag.management.MapManager;
import luna.tag.management.SpawnManagement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Map implements CommandExecutor {
    private MapManager mapManager = new MapManager();
    private SpawnManagement spawnManager = SpawnManagement.getInstance(mapManager.getCurrentMapName());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "Current map: " + mapManager.getCurrentMapName());
            return true;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("set")) {
                mapManager.setCurrentMap(args[1]);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.teleport(spawnManager.getDefaultSpawn(mapManager.getCurrentMapName()));
                }
                sender.sendMessage(ChatColor.GREEN + "Current map: " + mapManager.getCurrentMapName());
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                mapManager.removeMap(args[1]);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("add")) {
                if (sender instanceof Player player) {
                    if (player.getEquipment() != null && player.getEquipment().getItemInMainHand().getType() != Material.AIR) {
                        mapManager.setMap(player.getEquipment().getItemInMainHand().getItemMeta().getDisplayName(), player.getEquipment().getItemInMainHand());
                        return true;
                    }
                }
            } else if (args[0].equalsIgnoreCase("randomize")) {
                mapManager.setCurrentMap(mapManager.getRandomMap().getItemMeta().getDisplayName());
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.teleport(spawnManager.getDefaultSpawn(mapManager.getCurrentMapName()));
                }
                sender.sendMessage(ChatColor.GREEN + "Current map: " + mapManager.getCurrentMapName());
                return true;
            }
        }
        return false;
    }
}
