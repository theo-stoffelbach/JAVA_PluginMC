package fr.utoka.myplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SwordEvent implements Listener {
    int killMonsterLevelUpSword = 1;
    int numberKillMonster = 1;
    @EventHandler
    public void allSword(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Creature && event.getDamager() instanceof Player) {
            Creature creature = (Creature) event.getEntity();
            Player player = (Player) event.getDamager();
            ItemStack weapon = player.getItemInHand();
            double damage = event.getDamage();
            if (creature instanceof Monster) {
                if (weapon.hasItemMeta()) {
                    ItemMeta itemMeta = weapon.getItemMeta();
                    if (itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(ChatColor.DARK_PURPLE + "Vampire Sword")) {
                        player.setHealth(player.getHealth() + damage);
                    } else if (itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(ChatColor.GOLD + "Fire Sword")) {
                        creature.setFireTicks(Integer.MAX_VALUE);
                    } else if (itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals(ChatColor.BLUE + "Fire Sword")) {
                        PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3);
                        creature.addPotionEffect(slowness);
                        creature.setFreezeTicks(Integer.MAX_VALUE);
                    }
                }
            }
        }
    }

    /**
     * the blood sword is upgrade after each kill monster
     * @param event take entity death
     */
    @EventHandler
    public void bloodSword (EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity().getType().isSpawnable() && event.getEntity().getType().isAlive()) {
                Player player = event.getEntity().getKiller();
                ItemStack weapon = player.getItemInHand();
                if (weapon.hasItemMeta()) {
                    if (weapon.getItemMeta().hasDisplayName() && weapon.getItemMeta().getDisplayName().equals(ChatColor.RED + "Blood Sword")) {
                        numberKillMonster++;
                        if (numberKillMonster >= 10) {
                            killMonsterLevelUpSword++;
                            weapon.addEnchantment(Enchantment.DAMAGE_ALL, killMonsterLevelUpSword);
                        }
                    }
                }
            }
        }
    }
}