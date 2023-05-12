package fr.utoka.myplugin.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class ArtefactEvent implements Listener {

    private final ItemStack customsword = new ItemStack(Material.DIAMOND_SWORD, 1);
    private final ItemStack customBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
    private final ItemStack customchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
    private final ItemStack customTalisman = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
    private int talismanUses = 5;

    public ArtefactEvent() {

        ItemMeta customSwordMeta = customsword.getItemMeta();
        customSwordMeta.setDisplayName("§4Hercule");
        customSwordMeta.setLore(Arrays.asList("Un Artefact "));
        customSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        customsword.setItemMeta(customSwordMeta);

        ItemMeta customBootsMeta = customBoots.getItemMeta();
        customBootsMeta.setDisplayName("§6Hermès ");
        customBootsMeta.setLore(Arrays.asList("§7Un Artefact"));
        customBoots.setItemMeta(customBootsMeta);

        ItemMeta customChestplateMeta = customchestplate.getItemMeta();
        customChestplateMeta.setDisplayName("§6Tank ");
        customChestplateMeta.setLore(Arrays.asList("§7Des plastrons très confortables", "§7et ultra résistantes !"));
        customchestplate.setItemMeta(customChestplateMeta);

        ItemMeta customTalismanMeta = customTalisman.getItemMeta();
        customTalismanMeta.setDisplayName("§5Talisman d'entre les morts");
        customTalismanMeta.setLore(Arrays.asList("§7Vous permet de revivre", "§75 fois avant de se briser"));
        customTalisman.setItemMeta(customTalismanMeta);
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onItem(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {
            return;
        }

        if (clickedItem.getType() == Material.DIAMOND_CHESTPLATE && clickedItem.getItemMeta().getDisplayName().equals("§6Tank")) {

            player.setMaxHealth(40.0);
            player.setHealth(40.0);
            player.setHealthScale(40.0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 3));
        } else if (event.getSlotType() == InventoryType.SlotType.ARMOR) {

            ItemStack chestplate = player.getInventory().getChestplate();
            if (chestplate == null || !chestplate.hasItemMeta() || !chestplate.getItemMeta().getDisplayName().equals("§6Tank")) {

                player.setMaxHealth(20.0);
                player.setHealthScale(20.0);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            }
        }
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.DIAMOND_CHESTPLATE && item.hasItemMeta() && item.getItemMeta().getDisplayName().equals("§6Tank ")) {
            Action action = event.getAction();
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                player.setMaxHealth(40.0);
                player.setHealth(40.0);
                player.setHealthScale(40.0);
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 3));
            }
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack boots = player.getInventory().getBoots();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack sword = player.getInventory().getItemInMainHand();

        // Vérifie si le joueur a tous les équipements équipés
        if (boots != null && boots.hasItemMeta() && boots.getItemMeta().getDisplayName().equals("§6Hermès ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1));
        } else {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        if (boots != null && boots.hasItemMeta() && boots.getItemMeta().getDisplayName().equals("§6Hermès ")
                && chestplate != null && chestplate.hasItemMeta() && chestplate.getItemMeta().getDisplayName().equals("§6Tank ")
                && sword != null && sword.hasItemMeta() && sword.getItemMeta().getDisplayName().equals("§4Hercule")) {
            player.getInventory().setItemInOffHand(customTalisman);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 1));
        } else {
            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

        player.getInventory().addItem(customsword);
        player.getInventory().addItem(customchestplate);

        boolean hasCustomBoots = false;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.NETHERITE_BOOTS && item.getItemMeta().getDisplayName().equals("§6Hermès ")) {
                hasCustomBoots = true;
                break;
            }
        }
        if (!hasCustomBoots) {
            player.getInventory().addItem(customBoots);
        }
        player.updateInventory();
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.getHealth() - event.getFinalDamage() <= 0) { // Check if the player's health will be reduced to 0 or less
                if (player.getInventory().contains(customTalisman)) {
                    if (talismanUses > 0) {
                        talismanUses--;
                        event.setCancelled(true);
                        player.setHealth(20);
                        player.setFireTicks(0);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 4));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 4));
                        player.getInventory().getItem(player.getInventory().first(customTalisman)).setAmount(talismanUses);
                        player.sendMessage(ChatColor.GOLD + "Votre talisman vous a sauvé la vie ! " + ChatColor.RED + talismanUses + ChatColor.GOLD + " utilisations restantes.");
                    } else {
                        player.sendMessage(ChatColor.GOLD + "Votre talisman s'est brisé, vous ne pouvez plus l'utiliser.");
                    }
                }
            }
        }
    }

}