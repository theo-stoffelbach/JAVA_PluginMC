package fr.utoka.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class allCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("g-ping")) {
                player.sendMessage("pong");
            }
            if (cmd.getName().equalsIgnoreCase("g-help")) {
                helpCommand(player);
            }
            if (cmd.getName().equalsIgnoreCase("g-givesword")) {
                EventMenu eventMenu = new EventMenu();
                if (args.length == 1) {
                    if (Objects.equals(args[0], "vampire")) {
                        eventMenu.giveSwordMeta(player, "vampire");
                    }
                    if (Objects.equals(args[0], "fire")) {
                        eventMenu.giveSwordMeta(player, "fire");
                    }
                    if (Objects.equals(args[0], "blood")) {
                        eventMenu.giveSwordMeta(player, "blood");
                    }
                    if (Objects.equals(args[0], "ice")) {
                        eventMenu.giveSwordMeta(player, "ice");
                    }
                } else {
                    player.sendMessage("Wrong Command retry with name sword");
                }
            }
            if (cmd.getName().equalsIgnoreCase("g-alert")) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part + " ");
                }
                Bukkit.broadcastMessage(ChatColor.RED + "[" + player.getName() + "] " + ChatColor.WHITE + bc.toString());
            }
            return true;
        }
        return false;
    }
    void helpCommand (Player player) {
        player.sendMessage("[HELP COMMANDS]");
        player.sendMessage(ChatColor.GOLD + "/g-ping " + ChatColor.WHITE + "send pong");
        player.sendMessage(ChatColor.GOLD + "/g-alert [msg]" + ChatColor.WHITE + "broadcast message");
        player.sendMessage(ChatColor.GOLD + "/g-givesword [name sword]" + ChatColor.WHITE + "sword: blood, vampire, fire");
        player.sendMessage(ChatColor.GOLD + "/g-getmoney " + ChatColor.WHITE + "display your money");
        player.sendMessage(ChatColor.GOLD + "/g-removemoney [player] [money] " + ChatColor.WHITE + "remove money of player or your money");
        player.sendMessage(ChatColor.GOLD + "/g-givemoney [player] [money] " + ChatColor.WHITE + "give money to the player or to you");
        player.sendMessage(ChatColor.GOLD + "/g-setmoney [player] [money] " + ChatColor.WHITE + "set money player");
    }
}