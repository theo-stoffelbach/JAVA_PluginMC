package fr.utoka.myplugin.itemEvent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SwordEvent implements Listener {
    /**
     * Create sword with meta item
     * @param sender take sender command
     * @param input take value for create meta item
     */
    public void giveSwordMeta(CommandSender sender, String input) {
        Player player = (Player) sender;
        EventMenu eventMenu = new EventMenu();
        switch (input) {
            case "vampire":
                player.getInventory().addItem(eventMenu.createMetaItem(Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE + "Vampire Sword", null, null));
                break;
            case "fire":
                player.getInventory().addItem(eventMenu.createMetaItem(Material.NETHERITE_SWORD, ChatColor.GOLD + "Fire Sword", null, null));
                break;
            case "blood":
                player.getInventory().addItem(eventMenu.createMetaItem(Material.NETHERITE_SWORD, ChatColor.RED + "Blood Sword", null, Enchantment.DAMAGE_ALL));
                break;
            case "ice":
                player.getInventory().addItem(eventMenu.createMetaItem(Material.NETHERITE_SWORD, ChatColor.BLUE + "Ice Sword", null, null));
                break;
        }
    }
    @EventHandler
    public void giveCompassJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
    }

    /**
     * this function forbids enchantment for special meta item
     * @param event event when enchanting object
     */
    @EventHandler
    public void offEnchant (EnchantItemEvent event) {
        ArrayList<String> list = new ArrayList<>();
        list.add(ChatColor.GOLD + "Fire Sword");
        list.add(ChatColor.DARK_PURPLE + "Vampire Sword");
        ItemStack item = event.getItem();
        if (item.hasItemMeta()) {
            boolean found = false;
            ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName()) {
                for (String str : list) {
                    if (str.equals(meta.getDisplayName())) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    event.setCancelled(true);
                    event.getEnchanter().sendMessage("Vous ne pouvez pas enchanter cet objet.");
                }
            }
        }
    }
}
