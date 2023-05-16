package org.example1.versionfinalmc.FireBow;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireBowEffect {

    private static final int FIRE_DURATION = 60;

    public void onArrowDamage(Arrow arrow, LivingEntity target) {
        if (target instanceof Player) {
            Player player = (Player) target;

            player.setFireTicks(FIRE_DURATION);

            player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, FIRE_DURATION, 0));
        }
    }
}
