package org.example1.versionfinalmc.FireBow;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
public class FireBowManager implements Listener {

    private Plugin plugin;

    public FireBowManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                ItemStack bow = shooter.getInventory().getItemInMainHand();
                if (bow != null && bow.getType() == Material.BOW && hasFireBowEffect(bow)) {
                    if (event.getEntity() instanceof LivingEntity) {
                        LivingEntity damagedEntity = (LivingEntity) event.getEntity();
                    }
                }
            }
        }
    }

    private boolean hasFireBowEffect(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("Arc FireBow")) {
            return true;
        }
        return false;
    }
}
