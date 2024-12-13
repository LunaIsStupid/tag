package luna.tag.management;

import java.util.*;

import luna.tag.Main;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;

public class FireworkManagement {
    private final Map<String, Color> colors;
    private FileConfiguration config = Main.getPlugin(Main.class).getConfig();

    public FireworkManagement() {
        colors = new HashMap<>();
        colors.put("WHITE", Color.WHITE);
        colors.put("LIGHT_GRAY", Color.fromRGB(158, 157, 178));
        colors.put("GRAY", Color.GRAY);
        colors.put("BLACK", Color.BLACK);
        colors.put("BROWN", Color.fromRGB(121, 70, 33));
        colors.put("RED", Color.RED);
        colors.put("ORANGE", Color.ORANGE);
        colors.put("YELLOW", Color.YELLOW);
        colors.put("LIME", Color.LIME);
        colors.put("GREEN", Color.GREEN);
        colors.put("CYAN", Color.fromRGB(28, 97, 132));
        colors.put("LIGHT_BLUE", Color.fromRGB(143, 185, 244));
        colors.put("BLUE", Color.BLUE);
        colors.put("PURPLE", Color.PURPLE);
        colors.put("MAGENTA", Color.fromRGB(203, 105, 197));
        colors.put("PINK", Color.fromRGB(247, 180, 214));
    }

    public Map<String, Color> getFireworkColors() {
        return colors;
    }

    public FireworkEffect getFirework(UUID id) {
        FireworkEffect.Builder firework = FireworkEffect.builder();
        firework.with(FireworkEffect.Type.BALL_LARGE);
        if (config.getConfigurationSection("fireworks") != null) {
            Map<String, Object> fwcolors = config.getConfigurationSection("fireworks").getValues(false);
            if (config.getColor("fireworks." + id.toString() + ".color1") != null) {
                firework.withColor(config.getColor("fireworks." + id.toString() + ".color1"));
                firework.withColor(config.getColor("fireworks." + id.toString() + ".color2"));
                firework.withColor(config.getColor("fireworks." + id.toString() + ".color3"));
            } else {
                firework.withColor(colors.get("RED"));
                firework.withColor(colors.get("LIME"));
                firework.withColor(colors.get("LIGHT_BLUE"));
            }
        } else {
            Map<String, Object> fwcolors = new HashMap<>();
            config.createSection("fireworks", fwcolors);
            firework.withColor(colors.get("RED"));
            firework.withColor(colors.get("LIME"));
            firework.withColor(colors.get("LIGHT_BLUE"));
            Main.getPlugin(Main.class).saveConfig();
        }
        return firework.build();
    }

    public void setFirework(UUID id, String colorID, String color) throws Exception {
        Map<String, Object> fwcolors;
        boolean passed = false;
        boolean passed2 = false;
        if (config.getConfigurationSection("fireworks") != null) {
            fwcolors = config.getConfigurationSection("fireworks").getValues(false);
        } else {
            fwcolors = new HashMap<>();
        }
        switch (colorID) {
            case "color1" -> {
                for (String key : colors.keySet()) {
                    if (color.equals(key)) {
                        fwcolors.put(id.toString() + ".color1", colors.get(key));
                        passed = true;
                    }
                }
                passed2 = true;
            }
            case "color2" -> {
                for (String key : colors.keySet()) {
                    if (color.equals(key)) {
                        fwcolors.put(id.toString() + ".color2", colors.get(key));
                        passed = true;
                    }
                }
                passed2 = true;
            }
            case "color3" -> {
                for (String key : colors.keySet()) {
                    if (color.equals(key)) {
                        fwcolors.put(id.toString() + ".color3", colors.get(key));
                        passed = true;
                    }
                }
                passed2 = true;
            }
        }
        if (!passed) {
            throw new Exception("Invalid color: " + color);
        }
        if (!passed2) {
            throw new Exception("Invalid color ID: " + colorID);
        }
        config.createSection("fireworks", fwcolors);
        Main.getPlugin(Main.class).saveConfig();
    }
}
