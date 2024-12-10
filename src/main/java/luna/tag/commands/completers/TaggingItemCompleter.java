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
        List<String> list = new ArrayList<String>();
        if (commandSender.hasPermission("tag.item")) {
            if (commandSender instanceof Player) {
                if (args.length == 1) {
                    list.add("set");
                    list.add("get");
                    // for now name is ignored cuz im lazy
                    list.add("name");
                } else if (args.length == 2) {
                    for (Material material : Material.values()) {
                        list.add(material.name().toLowerCase());
                    }
                } else {
                    list.add("stop it");
                }
            }
        }

        return list;
    }
}
