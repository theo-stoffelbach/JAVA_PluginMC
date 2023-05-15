package fr.utoka.myplugin.Listener;

import fr.utoka.myplugin.Service.CommandMoneyService;
import fr.utoka.myplugin.commands.CommandMoney;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.checkerframework.checker.units.qual.C;

import java.sql.SQLException;
import java.util.UUID;

public class KillMobs implements Listener {

    @EventHandler
    public void onKillMobs(EntityDeathEvent event)  {
    Entity mobdeath = event.getEntity();

    System.out.println("MErde");
    Player player;


    if (event.getEntity().getKiller() instanceof Player) {
        CommandMoneyService managementMoney = CommandMoneyService.getInstance();
        player = event.getEntity().getKiller();
        for (EntityInfo mobs : EntityInfo.values()) {
            if (mobs.name().equals(mobdeath.getType().toString())) {
                managementMoney.addMoneyToHash(player.getUniqueId(), mobs.priceKilled());
            }
        }
    }else {
        return;
    }

    System.out.println("Mob tué : " + mobdeath.getName() + " Type tué : " + mobdeath.getType());
    System.out.println("tueur " + player.getName() + " Type tué : " + player.getType());
    }
}
