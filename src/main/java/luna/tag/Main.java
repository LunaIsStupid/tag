package luna.tag;

import luna.tag.commands.TaggingItem;
import luna.tag.commands.completers.TaggingItemCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.reloadConfig();
        getCommand("taggingitem").setExecutor(new TaggingItem());
        getCommand("taggingitem").setTabCompleter(new TaggingItemCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
