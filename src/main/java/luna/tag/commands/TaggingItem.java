package luna.tag.commands;

import luna.tag.management.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TaggingItem implements CommandExecutor {
    private final ItemManager itemManager = ItemManager.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player player) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("get")) {
                    player.getInventory().addItem(itemManager.getItem(player.getUniqueId()));
                    return true;
                } else {
                    return false;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    ItemStack item = new ItemStack(Material.valueOf(args[1].toUpperCase()));
                    ItemMeta meta = item.getItemMeta();
                    if (meta == null) {
                        player.sendMessage(ChatColor.RED + "Unknown Item");
                        return true;
                    }
                    meta.setDisplayName(itemManager.getItem(player.getUniqueId()).getItemMeta().getDisplayName());
                    itemManager.setItem(player.getUniqueId(), item);
                    return true;
                } else if (args[0].equalsIgnoreCase("name")) {
                    ItemStack item = itemManager.getItem(player.getUniqueId());
                    ItemMeta meta = item.getItemMeta();
                    if (meta == null) {
                        player.sendMessage(ChatColor.RED + "Unknown Item");
                        return true;
                    }
                    meta.setDisplayName(args[1].replaceAll("&", "§"));
                    item.setItemMeta(meta);
                    itemManager.setItem(player.getUniqueId(), item);
                    return true;
                }
            } else if (args.length >= 3) {
                if (args[0].equalsIgnoreCase("name")) {
                    ItemStack item = itemManager.getItem(player.getUniqueId());
                    ItemMeta meta = item.getItemMeta();
                    if (meta == null) {
                        player.sendMessage(ChatColor.RED + "Unknown Item");
                        return true;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String arg : args) {
                        if (!arg.equalsIgnoreCase("name")) {
                            sb.append(arg).append(" ");
                        }
                    }
                    meta.setDisplayName(sb.toString().replaceAll("&", "§"));
                    item.setItemMeta(meta);
                    itemManager.setItem(player.getUniqueId(), item);
                    return true;
                }
            }
        }
        return false;
    }
}
