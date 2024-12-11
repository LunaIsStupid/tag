package luna.tag.commands.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GameCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> list = new ArrayList<String>();
        if (args.length == 1) {
            list.add("start");
            list.add("end");
            return list;
        } else if (args.length == 2) {
            if (args[0].equals("start")) {
                list.add("hotpotato");
                return list;
            }
        } else {
            return list;
        }
        return list;
    }
}
