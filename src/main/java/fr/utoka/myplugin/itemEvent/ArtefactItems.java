package fr.utoka.myplugin.itemEvent;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class ArtefactItems implements Listener {

    @SuppressWarnings("unused")

    // Création des objets personnalisés
    // Ce sont des exemples je les remplacerais par des artefact
    private final ItemStack customsword = new ItemStack(Material.NETHERITE_SWORD, 1);
    private final ItemStack customBoots = new ItemStack(Material.NETHERITE_BOOTS, 1);
    private final ItemStack customchestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);

    public ArtefactItems() {
        // Personnalisation de l'épée
        ItemMeta customSwordMeta = customsword.getItemMeta();
        customSwordMeta.setDisplayName("§4Hercule");
        customSwordMeta.setLore(Arrays.asList("Un Artefact "));
        customSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 250, true);
        customsword.setItemMeta(customSwordMeta);

        // Personnalisation des bottes
        ItemMeta customBootsMeta = customBoots.getItemMeta();
        customBootsMeta.setDisplayName("§6Hermès ");
        customBootsMeta.setLore(Arrays.asList("§7Un Artefact"));
        /* customBootsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); */
        customBoots.setItemMeta(customBootsMeta);

        // Personnalisation des chestplates
        ItemMeta customChestplateMeta = customchestplate.getItemMeta();
        customChestplateMeta.setDisplayName("§6Tank ");
        customChestplateMeta.setLore(Arrays.asList("§7Des plastrons très confortables", "§7et ultra résistantes !"));
        customchestplate.setItemMeta(customChestplateMeta);
    }

    /* Permet de vérifier si le joueur bouge et donc d'ajouter la potion de vitesse */
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack boots = player.getInventory().getBoots();

        // Vérifie si le joueur porte les bottes personnalisées
        if (boots != null && boots.hasItemMeta() && boots.getItemMeta().getDisplayName().equals("§6Hermès ")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1));
        }

    }
    @EventHandler
    /* Vérification des objets (épée et bottes) et permet de vérifier les bottes et donc d'ajouter l'effet de la potion */
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();

        // Ajout de l'épée personnalisée à l'inventaire
        player.getInventory().addItem(customsword);
        player.getInventory().addItem(customchestplate);

        // Ajout des bottes personnalisées à l'inventaire si elles ne sont pas déjà présentes
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
}





