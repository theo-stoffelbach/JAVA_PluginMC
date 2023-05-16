package org.example1.versionfinalmc.IceBow;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AceBowManager implements Listener {

    private Plugin plugin;
    private AceBowProjectile aceBowProjectile;

    public AceBowManager(Plugin plugin) {
        this.plugin = plugin;
        this.aceBowProjectile = new AceBowProjectile();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                ItemStack bow = shooter.getItemInHand();
                if (bow != null && bow.getType() == Material.BOW && hasAceBowEffect(bow)) {
                    aceBowProjectile.onArrowShot(arrow);
                }
            }
        }
    }

    private boolean hasAceBowEffect(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("Arc AceBow")) {
            return true;
        }
        return false;
    }
}
