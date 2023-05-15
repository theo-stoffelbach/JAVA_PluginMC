package fr.utoka.myplugin.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Crafts {
    public static ShapedRecipe notchApple() {
        ShapedRecipe notchApple = new ShapedRecipe(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        notchApple.shape( new String[] {"GGG","GAG","GGG"});
        notchApple.setIngredient('G',Material.GOLD_BLOCK,1);
        notchApple.setIngredient('A',Material.APPLE,1);

        return notchApple;
    }

}
