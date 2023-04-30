package fr.theo.monplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,String msg, String[] arg) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            int exp = player.getExpToLevel();



            if (exp > 1000) {
                player.setExp(exp - 1000);
            }else {
                player.giveExp(1000);
            }

            System.out.println("exp du joueur : " + exp);


        }

    return false;
    }
}
