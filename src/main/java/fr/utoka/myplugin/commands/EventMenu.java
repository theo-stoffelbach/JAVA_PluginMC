package fr.utoka.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EventMenu implements Listener {
    @EventHandler
    public void Menu (PlayerInteractEvent event) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Téléporte dans le monde RPG");
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = event.getItem();
            if (item != null && item.getType() == Material.COMPASS) {
                Player player = event.getPlayer();
                Inventory inv = Bukkit.createInventory(null, 18, "Menu");
                player.openInventory(inv);
                inv.setItem(0, createMetaItem(Material.WOODEN_SWORD, "RPG", lore));
                inv.setItem(17, createMetaItem(Material.BOOK, "Help", lore));
            }
        }
    }
    public ItemStack createMetaItem (Material material, String name, ArrayList lore) {
        ItemStack rpg = new ItemStack(material);
        ItemMeta rpgM = rpg.getItemMeta();
        rpgM.setDisplayName(name);
        rpgM.setLore(lore);
        rpg.setItemMeta(rpgM);
        return rpg;
    }

    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (event.getView().getTitle().equalsIgnoreCase("Menu")) {
            if (current == null) return;
            if (current.getType() == Material.WOODEN_SWORD) {
                player.teleport(new Location(Bukkit.getWorld("world"), 88, 71, 86));
            }
            if (current.getType() == Material.BOOK) {
                player.chat("/aide");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void giveCompassJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        player.getInventory().addItem(createMetaItem(Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE + "Vampire Sword", null));
    }
}