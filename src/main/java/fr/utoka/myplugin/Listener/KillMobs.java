package fr.utoka.myplugin.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobs implements Listener {

    @EventHandler
    public void onKillMobs(EntityDeathEvent event) {
    Entity mobdeath = event.getEntity();

    EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) mobdeath.getLastDamageCause();
    Entity killer = damageEvent.getDamager();
    }
}
