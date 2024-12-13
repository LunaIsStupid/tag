package luna.tag.commands;

import luna.tag.management.FireworkManagement;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class Firework implements CommandExecutor {
    FireworkManagement firework = new FireworkManagement();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1].toUpperCase());
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully set firework!");
                    return spawnFirework(player);
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1].toUpperCase());
                        firework.setFirework(player.getUniqueId(), "color2", args[2].toUpperCase());
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully set firework!");
                    return spawnFirework(player);
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("set")) {
                    try {
                        firework.setFirework(player.getUniqueId(), "color1", args[1].toUpperCase());
                        firework.setFirework(player.getUniqueId(), "color2", args[2].toUpperCase());
                        firework.setFirework(player.getUniqueId(), "color3", args[3].toUpperCase());
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Error: " + e.getMessage());
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully set firework!");
                    return spawnFirework(player);
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("test")) {
                    return spawnFirework(player);
                }
            }
        }
        return false;
    }

    private boolean spawnFirework(Player player) {
        org.bukkit.entity.Firework firework = player.getWorld().spawn(player.getLocation(), org.bukkit.entity.Firework.class);
        FireworkEffect fireworkEffect = this.firework.getFirework(player.getUniqueId());
        FireworkMeta data = firework.getFireworkMeta();
        data.clearEffects();
        data.addEffect(fireworkEffect);
        data.setPower(1);
        firework.setFireworkMeta(data);
        firework.detonate();
        return true;
    }
}
