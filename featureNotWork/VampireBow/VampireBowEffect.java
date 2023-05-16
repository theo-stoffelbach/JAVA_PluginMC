package org.example1.versionfinalmc.VampireBow;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VampireBowEffect {

    private static final int MAX_HEALTH_DRAIN = 4;

    public void onArrowDamage(Arrow arrow, LivingEntity target) {
        if (target instanceof Player) {
            Player player = (Player) target;
            double damage = arrow.getDamage();

            double healthToDrain = damage / 2;
            if (healthToDrain > MAX_HEALTH_DRAIN) {
                healthToDrain = MAX_HEALTH_DRAIN;
            }
            player.setHealth(player.getHealth() - healthToDrain);

            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
        }
    }
}
