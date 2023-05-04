package fr.utoka.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class allCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("test")) {
                player.sendMessage("Bravo tu as réussi le test");
            }
            if (cmd.getName().equalsIgnoreCase("aide")) {
                helpCommand(player);
            }
            if (cmd.getName().equalsIgnoreCase("alert")) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part + " ");
                }
                Bukkit.broadcastMessage(ChatColor.RED + "[" + player.getName() + "] " + ChatColor.WHITE + bc.toString());
            }
        }
        return false;
    }
    void helpCommand (Player player) {
        player.sendMessage("[HELP COMMANDS]");
        player.sendMessage(ChatColor.GOLD + "/alert " + ChatColor.WHITE + "texte");
    }
}
