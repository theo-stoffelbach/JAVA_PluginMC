package fr.utoka.myplugin.Entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EventPnj implements Listener {
    @EventHandler
    public void isPnjEvent (EntitySpawnEvent event) {
        Entity entityEvent = event.getEntity();
        if (entityEvent.getName().equals("Maxime")) {
            
        }
    }
}
