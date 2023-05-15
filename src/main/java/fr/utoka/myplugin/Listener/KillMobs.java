package fr.utoka.myplugin.Listener;

import fr.utoka.myplugin.Service.CommandMoneyService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.sql.SQLException;

public class KillMobs implements Listener {

    @EventHandler
    public void onKillMobs(EntityDeathEvent event) {
    Entity mobdeath = event.getEntity();

    System.out.println("OH SNAP");
    Player player;


    if (event.getEntity().getKiller() instanceof Player) {
        CommandMoneyService managementMoney = CommandMoneyService.getInstance();
        player = event.getEntity().getKiller();
        for (EntityInfo mobs : EntityInfo.values()) {
            if (mobs.name().equals(mobdeath.getType().toString())) {
                try {
                    managementMoney.addMoneyToDB(player.getUniqueId(), mobs.priceKilled());
                    managementMoney.addMoneyToHash(player.getUniqueId(), mobs.priceKilled());
                }catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        }
    }else {
        return;
    }

    System.out.println("Mob tué : " + mobdeath.getName() + " Type tué : " + mobdeath.getType());
    System.out.println("tueur " + player.getName() + " Type tué : " + player.getType());
    }
}
