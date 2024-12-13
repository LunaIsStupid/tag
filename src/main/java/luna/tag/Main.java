package luna.tag;

import luna.tag.commands.*;
import luna.tag.commands.completers.FireworkCompleter;
import luna.tag.commands.completers.GameCompleter;
import luna.tag.commands.completers.TaggingItemCompleter;
import luna.tag.events.TagEvent;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.reloadConfig();
        getCommand("taggingitem").setExecutor(new TaggingItem());
        getCommand("spoon").setExecutor(new SetDebug());
        getCommand("game").setExecutor(new Game());
        getCommand("firework").setExecutor(new Firework());
        getCommand("game").setTabCompleter(new GameCompleter());
        getCommand("taggingitem").setTabCompleter(new TaggingItemCompleter());
        getCommand("firework").setTabCompleter(new FireworkCompleter());

        getServer().getPluginManager().registerEvents(new TagEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
