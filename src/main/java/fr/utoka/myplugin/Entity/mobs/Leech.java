package fr.utoka.myplugin.Entity.mobs;

import fr.utoka.myplugin.MyPlugin;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class Leech implements Listener {
    private static MyPlugin myPlugin;
    private double healtMob;
    private double healtMobMax;

    public Leech (MyPlugin myPlugin) {
        this.myPlugin = myPlugin;
    }

    public static void createLeech(Location location) {
        Zombie leech = location.getWorld().spawn(location, Zombie.class);

        leech.setMetadata("CustomMob", new FixedMetadataValue(myPlugin,"Leech"));

        leech.setCustomName(ChatColor.BLUE + "LEECH - " + leech.getHealth() + " / 40");
        leech.setCustomNameVisible(true);

        Attributable zombieAt = leech;
        AttributeInstance attribute = zombieAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(40);
        leech.setHealth(40);

        new BukkitRunnable() {

            public void run() {
                if (!leech.isDead()) {
                    if (leech.getTarget() == null) {
                        for (Entity entity : leech.getNearbyEntities(10,10,10)) {
                            if (entity instanceof Player) {
                                Player player = (Player) entity;
                                leech.setTarget(player);
                                player.sendMessage(ChatColor.BLUE + "[LEECH] : Je TE vois, ah ah ah");
                            }
                        }
                    }else {
                        LivingEntity target = leech.getTarget();
                        if (target.getLocation().distanceSquared(leech.getLocation()) > 25) {
                            leech.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, leech.getLocation(), 20);
                            leech.setVelocity(target.getLocation().add(0,2,0).subtract(leech.getLocation()).toVector().multiply(0.275));
                        }
                    }
                }else {
                    cancel();
                }
            }
        }.runTaskTimer(myPlugin,0L,20L);
    }

    @EventHandler
    public void onDommange(EntityDamageByEntityEvent event) {


        if (event.getDamager() instanceof Zombie) {
            MetadataValue customMeta = (MetadataValue) event.getEntity().getMetadata("CustomMobs");

            if (customMeta == "Leech") {

            }
        }
    }
}
