package org.example1.versionfinalmc.CreeperBow;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class CreeperProjectile implements Listener {

    private Plugin plugin;

    public CreeperProjectile() {
        this.plugin = plugin;
    }

    public void onArrowShot(Arrow arrow) {
        arrow.setMetadata("CreeperProjectile", new FixedMetadataValue(plugin, true));
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.hasMetadata("CreeperProjectile")) {
                explodeArrow(arrow);
            }
        }
    }

    void explodeArrow(Arrow arrow) {
        Location location = arrow.getLocation();
        World world = location.getWorld();
        world.createExplosion(location, 3f, false);
        arrow.remove();
    }
}
