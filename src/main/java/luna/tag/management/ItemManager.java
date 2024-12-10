package luna.tag.management;

import luna.tag.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemManager {
    // dont create multiple of this class plz
    private int instanceCount;
    private static volatile ItemManager instance;
    private ItemManager() {
        instanceCount++;
        try {
            taggingItems = config.getConfigurationSection("items").getValues(false);
        } catch (Exception ignored) {
            taggingItems = new HashMap<>();
            config.createSection("items", taggingItems);
            taggingItems = config.getConfigurationSection("items").getValues(false);
        }
    }

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
        }
        return instance;
    }
    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();

    private Map<String, Object> taggingItems;

    public void setItem(UUID uuid, ItemStack item) {
        taggingItems.put(uuid.toString(), item);
        config.createSection("items", taggingItems);
        Main.getPlugin(Main.class).saveConfig();
    }
}
