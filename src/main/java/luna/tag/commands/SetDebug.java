package luna.tag.commands;

import luna.tag.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class SetDebug implements CommandExecutor {
    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("true")) {
                config.set("debug", true);
                Main.getPlugin(Main.class).saveConfig();
                Main.getPlugin(Main.class).reloadConfig();
            } else {
                config.set("debug", false);
                Main.getPlugin(Main.class).saveConfig();
                Main.getPlugin(Main.class).reloadConfig();
            }
            return true;
        } else {
            return false;
        }
    }
}
