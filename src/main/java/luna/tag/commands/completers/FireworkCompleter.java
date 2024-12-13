package luna.tag.commands.completers;

import luna.tag.management.FireworkManagement;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FireworkCompleter implements TabCompleter {
    FireworkManagement fireworkManagement = new FireworkManagement();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        return switch (args.length) {
            case 1 -> {
                list.add("set");
                yield StringUtil.copyPartialMatches(args[0].toLowerCase(), list, new ArrayList<>());
            }
            case 2, 3, 4 -> {
                list.addAll(fireworkManagement.getFireworkColors().keySet());
                list.replaceAll(String::toLowerCase);
                if (!args[0].equalsIgnoreCase("set")) {
                    yield Collections.emptyList();
                }
                yield StringUtil.copyPartialMatches(args[args.length - 1], list, new ArrayList<>());
            }
            default -> List.of();
        };
    }
}
