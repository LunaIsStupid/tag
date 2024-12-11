package luna.tag.events;

import luna.tag.Main;
import luna.tag.management.GameManagement;
import luna.tag.management.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class TagEvent implements Listener {
    private GameManagement gm = GameManagement.getInstance();
    private ItemManager im = ItemManager.getInstance();

    @EventHandler
    public void onTagEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
        if (event.getDamager() instanceof Player damager) {
            if (event.getEntity() instanceof Player damaged) {
                if (gm.getGameOngoing() || Main.getPlugin(Main.class).getConfig().getBoolean("debug")) {
                    if (damager.getEquipment().getItemInMainHand().isSimilar(im.getItem(damager.getUniqueId()))) {
                        damager.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
                        damaged.getEquipment().setItemInMainHand(im.getItem(damaged.getUniqueId()));
                        Vector launchDirection = damager.getLocation().getDirection();
                        launchDirection.multiply(1.7);
                        launchDirection.setY(0.4);
                        damaged.setVelocity(launchDirection);
                    }
                }
            }
            return;
        }
    }
}
