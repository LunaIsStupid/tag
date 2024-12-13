package luna.tag.commands.completers;

import luna.tag.management.FireworkManagement;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class FireworkCompleter implements TabCompleter {
    FireworkManagement fireworkManagement = new FireworkManagement();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        switch (args.length) {
            case 1:
                list.add("set");
                break;
            case 2, 3, 4:
                list.addAll(fireworkManagement.getFireworkColors().keySet());
                break;
        }
        return List.of();
    }
}
