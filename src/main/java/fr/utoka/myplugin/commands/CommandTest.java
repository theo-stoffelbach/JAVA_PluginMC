package fr.utoka.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("test")) {
                player.sendMessage(ChatColor.BLUE + "Bravo tu as reussie le test");
            }
            if (cmd.getName().equalsIgnoreCase("alert")) {
                Bukkit.broadcastMessage(ChatColor.WHITE + "[" + player.getName() + "]" + ChatColor.BLUE + "Ceci est une alerte");
            }
        }
        return false;
    }
}
