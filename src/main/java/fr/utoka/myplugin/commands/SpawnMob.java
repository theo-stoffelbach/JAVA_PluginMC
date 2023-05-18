package fr.utoka.myplugin.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import static org.bukkit.Bukkit.getServer;

public class SpawnMob implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {

        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("g-spwanmob")) {
                World world = getServer().getWorld("world"); // Récupère le monde où faire spawn le mob
                Location location = new Location(world, 0, 64, 0); // Définissez les coordonnées de l'emplacement du spawn

                Monster customMob = (Monster) world.spawnEntity(location, EntityType.ZOMBIE); // Faites spawn le CustomMob


                // func ;
            }
        }

        return false;
    }
}
