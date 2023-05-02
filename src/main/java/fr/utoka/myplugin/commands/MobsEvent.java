package fr.utoka.myplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MobsEvent implements Listener {
    @EventHandler
    public void vampireSword(EntityDamageByEntityEvent event) {
        ItemMeta meta = allSword().getItemMeta();
        if (event.getEntity() instanceof Creature && event.getDamager() instanceof Player) {
            Creature creature = (Creature) event.getEntity();
            Player player = (Player) event.getDamager();
            double damage = event.getDamage();
            if (creature instanceof Monster) {
                if (meta.getDisplayName().equals(ChatColor.DARK_PURPLE + "Vampire Sword")) {
                    player.setHealth(player.getHealth() + damage);
                }
            }
        }
    }
    public ItemStack allSword () {
        EventMenu eventMenu = new EventMenu();
        ItemStack item = eventMenu.createMetaItem(Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE + "Vampire Sword", null);
        return item;
    }
}
