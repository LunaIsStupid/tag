package luna.tag.commands;

import luna.tag.games.HotPotato;
import luna.tag.games.common.RoundTimer;
import luna.tag.management.GameManagement;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Game implements CommandExecutor {

    GameManagement gm = GameManagement.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("start")) {
                if (args[1].equalsIgnoreCase("hotpotato")) {
                    HotPotato.getInstance().startRound("no");
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                HotPotato.getInstance().startRound("no");
            } else {
                if (gm.getGameName().equalsIgnoreCase("HotPotato")) {
                    RoundTimer.getInstance().stop();
                    gm.clearPlayers();
                    gm.clearLosers();
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
