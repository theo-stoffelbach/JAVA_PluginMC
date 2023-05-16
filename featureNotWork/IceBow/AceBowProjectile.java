package org.example1.versionfinalmc.IceBow;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class AceBowProjectile {

    public void onArrowShot(Arrow arrow) {
        Plugin plugin = null;
        arrow.setMetadata("AceBowProjectile", new FixedMetadataValue(plugin, true));
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.hasMetadata("AceBowProjectile")) {
                explodeArrow(arrow);
            }
        }
    }

    private void explodeArrow(Arrow arrow) {
        Location location = arrow.getLocation();
        World world = location.getWorld();
        world.strikeLightning(location);
        arrow.remove();
    }
}
