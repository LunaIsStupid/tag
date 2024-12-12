package luna.tag.management;

import luna.tag.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapManager {
    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();
    public ItemStack getCurrentMap() {
        if (config.get("currentmap") == null) {
            config.set("currentmap", getRandomMap());
            return config.getItemStack("currentmap");
        } else {
            return config.getItemStack("currentmap");
        }
    }

    public String getCurrentMapName() {
        if (config.get("currentmap") == null) {
            config.set("currentmap", getRandomMap());
            return config.getItemStack("currentmap").getItemMeta().getDisplayName();
        } else {
            return config.getItemStack("currentmap").getItemMeta().getDisplayName();
        }
    }

    public ItemStack getRandomMap() {
        if (config.getConfigurationSection("maps") == null) {
            Map<String, Object> map = new HashMap<>();
            config.createSection("maps", map);
            Main.getPlugin(Main.class).saveConfig();
            return null;
        } else {
            Random rand = new Random();
            int random = rand.nextInt(config.getConfigurationSection("maps").getValues(false).size());
            Map<String, Object> maps = config.getConfigurationSection("maps").getValues(false);
            return (ItemStack) maps.values().toArray()[random];
        }
    }

    public void setMap(String map, ItemStack item) {
        Map<String, Object> maps = config.getConfigurationSection("maps").getValues(false);
        maps.put(map, item);
        config.createSection("maps", maps);
        Main.getPlugin(Main.class).saveConfig();
    }

    public void removeMap(String map) {
        Map<String, Object> maps = config.getConfigurationSection("maps").getValues(false);
        maps.remove(map);
        config.createSection("maps", maps);
        Main.getPlugin(Main.class).saveConfig();
    }

    public void setCurrentMap(String map) throws NullPointerException {
        Map<String, Object> maps = config.getConfigurationSection("maps").getValues(false);
        if (maps.containsKey(map)) {
            config.set("currentmap", maps.get(map));
        } else {
            throw new NullPointerException("Map " + map + " not found");
        }
    }
}
