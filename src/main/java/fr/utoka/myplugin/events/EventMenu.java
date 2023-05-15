package fr.utoka.myplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
public class EventMenu implements Listener {
    @EventHandler
    public void Menu(PlayerInteractEvent event) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Téléporte dans le monde RPG");
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            if (item != null && item.getType() == Material.COMPASS) {
                Player player = event.getPlayer();
                Inventory inv = Bukkit.createInventory(null, 18, "Menu");
                player.openInventory(inv);
                inv.setItem(0, createMetaItem(Material.WOODEN_SWORD, "RPG", lore, Enchantment.DAMAGE_ALL));
                inv.setItem(17, createMetaItem(Material.BOOK, "Help", lore, Enchantment.DAMAGE_ALL));
            }
        }
    }

    /**
     * This function allow to create meta items
     * @param material    Material of meta item
     * @param name        Name of meta item
     * @param lore        lore of meta item
     * @param enchantment enchantment for meta item
     * @return return meta item
     */
    public ItemStack createMetaItem(Material material, String name, ArrayList lore, Enchantment enchantment) {
        ItemStack rpg = new ItemStack(material);
        ItemMeta rpgM = rpg.getItemMeta();
        rpgM.setDisplayName(name);
        rpgM.setLore(lore);
        rpgM.setUnbreakable(true);
        if (enchantment != null) {
            rpgM.addEnchant(enchantment, 1, true);
        }
        rpg.setItemMeta(rpgM);
        return rpg;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (event.getView().getTitle().equalsIgnoreCase("Menu")) {
            if (current == null) return;
            if (current.getType() == Material.WOODEN_SWORD) {
                player.teleport(new Location(Bukkit.getWorld("world"), 88, 71, 86));
            }
            if (current.getType() == Material.BOOK) {
                player.chat("/g-help");
                player.closeInventory();
            }
        }
    }
}