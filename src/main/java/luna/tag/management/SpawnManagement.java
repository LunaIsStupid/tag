package luna.tag.management;

import luna.tag.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnManagement {
    private static volatile SpawnManagement instance;
    public static SpawnManagement getInstance() {
        if (instance == null) {
            instance = new SpawnManagement();
        }
        return instance;
    }

    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();
    private Map<String, Object> spawns;

    public SpawnManagement() {
        try {
            spawns = config.getConfigurationSection("spawns").getValues(false);
        } catch (Exception ignored) {
            spawns = new HashMap<>();
            config.createSection("spawns", spawns);
            Main.getPlugin(Main.class).saveConfig();
            spawns = config.getConfigurationSection("spawns").getValues(false);
        }
    }

    public Map<String, Object> getSpawns() {
        return spawns;
    }

    public void addSpawn(String spawn, Location location) {
        spawns.put(spawn, location);
        config.createSection("spawns", spawns);
        Main.getPlugin(Main.class).saveConfig();
    }

    public void removeSpawn(String spawn) {
        spawns.remove(spawn);
        config.createSection("spawns", spawns);
        Main.getPlugin(Main.class).saveConfig();
    }

    public Location getRandomSpawn() {
        Random rand = new Random();
        int random = rand.nextInt(spawns.size());
        Location loc = (Location) spawns.get(random);
        return loc;
    }

    public void clearSpawns() {
        spawns.clear();
    }

    public void setDefaultSpawn(Location location) {
        spawns.put("default", location);
        config.createSection("spawns", spawns);
        Main.getPlugin(Main.class).saveConfig();
    }

    public Location getDefaultSpawn() {
        return (Location) spawns.get("default");
    }
}
