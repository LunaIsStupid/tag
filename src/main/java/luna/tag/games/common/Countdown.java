package luna.tag.games.common;

import luna.tag.Main;
import luna.tag.games.HotPotato;
import luna.tag.management.GameManagement;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown {
    private GameManagement game = GameManagement.getInstance();
    int time = 3;

    public void start() {
        BukkitRunnable task = new BukkitRunnable() {
            public void run() {
                if (time == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.BLUE + "T" + ChatColor.AQUA + "A" + ChatColor.BLUE + "G" + ChatColor.AQUA + "!", "", 2, 5,2);
                    }
                    if (game.getGameName().equalsIgnoreCase("HotPotato")) {
                        HotPotato.getInstance().startRound("continue");
                    }
                    this.cancel();
                } else if (time == 1) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.GREEN + "1", "", 5, 10, 5);
                    }
                    time--;
                } else if (time == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.YELLOW + "2", "", 5, 10, 5);
                    }
                    time--;
                } else if (time == 3) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.RED + "3", "", 5, 10, 5);
                    }
                    time--;
                }
            }
        };
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
    }
}
