package org.example1.versionfinalmc.CreeperBow;

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

public class CreeperBowManager implements Listener {

    private Plugin plugin;
    private CreeperProjectile creeperProjectile;

    public CreeperBowManager(Plugin plugin) {
        this.plugin = plugin;
        this.creeperProjectile = new CreeperProjectile();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                ItemStack bow = shooter.getInventory().getItemInMainHand();
                if (bow != null && bow.getType() == Material.BOW && hasCreeperBowEffect(bow)) {
                    creeperProjectile.explodeArrow(arrow);
                }
            }
        }
    }

    private boolean hasCreeperBowEffect(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("Arc du Creeper")) {
            return true;
        }
        return false;
    }
}
