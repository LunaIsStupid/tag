package luna.tag.commands;

import luna.tag.management.FireworkManagement;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Firework implements CommandExecutor {
    FireworkManagement firework = new FireworkManagement();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1]);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                    }
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1]);
                        firework.setFirework(player.getUniqueId(), "color2", args[2]);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                    }
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1]);
                        firework.setFirework(player.getUniqueId(), "color2", args[2]);
                        firework.setFirework(player.getUniqueId(), "color3", args[3]);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                    }
                }
            }
        }
        return false;
    }
}
