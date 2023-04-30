package fr.team.Gedonna;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Management
{

    public static void load() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE  + "[MonPlugin]" + ChatColor.GREEN + "Le plus a démarré correctement ^^.");
    }



    public static void unload() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE  + "[MonPlugin]" + ChatColor.RED + "Le plus s'est arrêté correctement");
    }
}
