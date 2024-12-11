package luna.tag.games;

import luna.tag.management.ItemManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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

    public void startGame() {
        // placeholder
    }

    public void stopGame() {
        // placeholder
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
