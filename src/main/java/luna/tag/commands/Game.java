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
                    try {
                        HotPotato.getInstance().startRound("no");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                try {
                    HotPotato.getInstance().startRound("no");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
