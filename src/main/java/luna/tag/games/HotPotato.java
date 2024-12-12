package luna.tag.games;

import luna.tag.Main;
import luna.tag.games.common.Countdown;
import luna.tag.games.common.RoundTimer;
import luna.tag.management.GameManagement;
import luna.tag.management.ItemManager;
import luna.tag.management.MapManager;
import luna.tag.management.SpawnManagement;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class HotPotato {
    private int instanceCount;
    private static volatile HotPotato instance;
    public HotPotato() { instanceCount++; }
    public static HotPotato getInstance() {
        if (instance == null) {
            instance = new HotPotato();
        }
        return instance;
    }

    private ItemManager itemManager = ItemManager.getInstance();
    private GameManagement gameManagement = GameManagement.getInstance();
    RoundTimer timer = RoundTimer.getInstance();

    public void startRound(String go) {
        // placeholder
        if (go.equalsIgnoreCase("continue")) {
            gameManagement.clearPlayers();
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.getInventory().clear();
            }
            Player[] OnlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
            for (UUID list: gameManagement.getLosers()) {
                Player toBeRemoved = Bukkit.getPlayer(list);
                for (int i=0; i<OnlinePlayers.length; i++) {
                    Player p = OnlinePlayers[i];
                    if (p.getGameMode().equals(GameMode.SPECTATOR)) { OnlinePlayers[i].remove(); }
                    if (p == toBeRemoved) continue;
                    OnlinePlayers[i].remove();
                }
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(OnlinePlayers.length);
            Player tagger1 = OnlinePlayers[randomIndex];
            tagger1.getEquipment().setItemInMainHand(ItemManager.getInstance().getItem(tagger1.getUniqueId()));
            if (OnlinePlayers.length >= 5) {
                randomIndex = rand.nextInt(OnlinePlayers.length);
                Player tagger2 = OnlinePlayers[randomIndex];
                tagger2.getEquipment().setItemInMainHand(ItemManager.getInstance().getItem(tagger2.getUniqueId()));
            }
            for (Player p : OnlinePlayers) {
                gameManagement.addPlayer(p.getUniqueId());
                if (!Main.getPlugin(Main.class).getConfig().getBoolean("debug")) {
                    SpawnManagement.getInstance(new MapManager().getCurrentMapName()).getRandomSpawn();
                }
            }
            timer.setTimer(45);
            timer.start();
        } else {
            gameManagement.setGameName("HotPotato");
            gameManagement.setGameOngoing(true);
            new Countdown().start();
        }
    }

    public void stopRound() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().containsAtLeast(itemManager.getItem(player.getUniqueId()), 1)) {
                gameManagement.addLoser(player.getUniqueId());
            }
            if (!Main.getPlugin(Main.class).getConfig().getBoolean("debug")) {
                SpawnManagement.getInstance(new MapManager().getCurrentMapName()).getDefaultSpawn(new MapManager().getCurrentMapName());
            }
        }
        if (gameManagement.getLosers().size() != gameManagement.getPlayers().size() -1) {
            startRound("continue");
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!gameManagement.getLosers().contains(player.getUniqueId())) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + " WINS!!!");
                }
            }
            gameManagement.clearPlayers();
            gameManagement.clearLosers();
            timer.stop();
            gameManagement.setGameOngoing(false);
        }
    }

    public void updateGame(int time) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().containsAtLeast(itemManager.getItem(player.getUniqueId()), 1))  {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "You have the hot potato!! Time Left: " + time));
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "Run!! Time Left: " + time));
            }
        }
    }
}
