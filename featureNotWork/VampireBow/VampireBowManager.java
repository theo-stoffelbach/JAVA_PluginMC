package org.example1.versionfinalmc.VampireBow;

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

public class VampireBowManager implements Listener {

    private Plugin plugin;
    private VampireBowEffect vampireBowEffect;

    public VampireBowManager(Plugin plugin) {
        this.plugin = plugin;
        this.vampireBowEffect = new VampireBowEffect();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                ItemStack bow = shooter.getInventory().getItemInMainHand();
                if (bow != null && bow.getType() == Material.BOW && hasVampireBowEffect(bow)) {
                    if (event.getEntity() instanceof LivingEntity) {
                        LivingEntity damagedEntity = (LivingEntity) event.getEntity();
                        vampireBowEffect.onArrowDamage(arrow, damagedEntity);
                    }
                }
            }
        }
    }

    private boolean hasVampireBowEffect(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("Arc VampireBow")) {
            return true;
        }
        return false;
    }
}
