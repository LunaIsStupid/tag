package luna.tag.commands.completers;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TaggingItemCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> list = new ArrayList<>();
        if (commandSender.hasPermission("tag.item")) {
            if (commandSender instanceof Player) {
                if (args.length == 1) {
                    list.add("set");
                    list.add("get");
                    list.add("name");
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("set")) {
                        for (Material material : Material.values()) {
                            list.add(material.name().toLowerCase());
                        }
                    }
                } else if (!args[0].equals("name")) {
                    list.add("stop it");
                } else {
                    list.add("<name>");
                }
            }
        }
        return list;
    }
}
