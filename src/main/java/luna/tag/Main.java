package luna.tag;

import luna.tag.commands.SetDebug;
import luna.tag.commands.TaggingItem;
import luna.tag.commands.completers.TaggingItemCompleter;
import luna.tag.events.TagEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.reloadConfig();
        getCommand("taggingitem").setExecutor(new TaggingItem());
        getCommand("spoon").setExecutor(new SetDebug());
        getCommand("taggingitem").setTabCompleter(new TaggingItemCompleter());
        getServer().getPluginManager().registerEvents(new TagEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
