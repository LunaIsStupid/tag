package luna.tag.games.common;

import luna.tag.games.HotPotato;
import luna.tag.management.GameManagement;
import org.bukkit.scheduler.BukkitRunnable;

public class RoundTimer {
    // placeholder
    private int instanceCount;
    private static volatile RoundTimer instance;
    public static RoundTimer getInstance() {
        if (instance == null) {
            instance = new RoundTimer();
        }
        return instance;
    }
    private RoundTimer() {
        instanceCount++;
    }

    private int time;

    public void setTimer(int time) {
        this.time = time;
    }

    BukkitRunnable task;

    GameManagement gm = GameManagement.getInstance();

    public void start() {
        task = new BukkitRunnable() {
            public void run() {
                if (time == 0) {
                    if (gm.getGameName().equalsIgnoreCase("HotPotato")) {
                        HotPotato.getInstance().stopRound();
                    }
                    this.cancel();
                } else {
                    if (gm.getGameName().equalsIgnoreCase("HotPotato")) {
                        HotPotato.getInstance().updateGame(time);
                    }
                    time--;
                }
            }
        };
    }

    public void stop() {
        task.cancel();
        time = 0;
    }
}
