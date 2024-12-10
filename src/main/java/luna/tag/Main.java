package luna.tag;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.reloadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
