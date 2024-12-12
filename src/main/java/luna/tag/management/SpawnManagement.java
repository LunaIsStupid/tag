package luna.tag.management;

import luna.tag.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnManagement {
    private static volatile Map<String, SpawnManagement> instance = new HashMap<>();
    public static SpawnManagement getInstance(String mapName) {
        if (instance.get(mapName) == null) {
            instance.put(mapName, new SpawnManagement(mapName));
        }
        return instance.get(mapName);
    }

    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();
    private Map<String, Object> spawns;
    private String mapName;

    public SpawnManagement(String mapName) {
        try {
            spawns = config.getConfigurationSection("spawns" + mapName).getValues(false);
        } catch (Exception ignored) {
            spawns = new HashMap<>();
            config.createSection("spawns" + mapName, spawns);
            Main.getPlugin(Main.class).saveConfig();
            spawns = config.getConfigurationSection("spawns" + mapName).getValues(false);
        }
        this.mapName = mapName;
    }

    public void setSpawn(String spawnIdentifier, Location location) {
        spawns.put(spawnIdentifier, location);
        config.createSection("spawns" + mapName, spawns);
        Main.getPlugin(Main.class).saveConfig();
    }

    public Location getSpawn(String spawnIdentifier) {
        return (Location) spawns.get(spawnIdentifier);
    }

    public void clearSpawns() {
        spawns.clear();
    }

    public void removeSpawn(String spawnIdentifier) {
        spawns.remove(spawnIdentifier);
    }

    public Map<String, Object> getSpawns() {
        return spawns;
    }

    public Location getRandomSpawn() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(spawns.size());
        return (Location) spawns.values().toArray()[randomIndex];
    }

    public void setDefaultSpawn(String spawnIdentifier, Location location) {
        Map<String, Object> map;
        if (config.getConfigurationSection("spawns").getConfigurationSection("defaults") == null) {
            map = new HashMap<>();
            map.put(spawnIdentifier, location);
        } else {
            map = config.getConfigurationSection("spawns").getConfigurationSection("defaults").getValues(false);
        }
        config.getConfigurationSection("spawns").createSection("defaults", map);
    }

    public Location getDefaultSpawn(String spawnIdentifier) {
        Map<String, Object> map;
        if (config.getConfigurationSection("spawns").getConfigurationSection("defaults") == null) {
            return null;
        }
        map = config.getConfigurationSection("spawns").getConfigurationSection("defaults").getValues(false);
        return (Location) map.get(spawnIdentifier);
    }
}
