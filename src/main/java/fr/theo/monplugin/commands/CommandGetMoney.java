package fr.theo.monplugin.commands;

import fr.theo.monplugin.MonPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandGetMoney implements CommandExecutor {
    private MonPlugin monPlugin;
    public UUID a = UUID.fromString("8821ab22-aed3-4cfb-9410-bcb05fe57f0c");
    public Integer b = 1000;

    public CommandGetMoney(MonPlugin monPlugin) {
        this.monPlugin = monPlugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
        System.out.println("Debug command money 1");

        if (sender instanceof Player) {
            System.out.println("Debug command money 2");
            Player player = (Player) sender;
            final UUID uuid = player.getUniqueId();

            System.out.println( "Debug command money 3 : WHAT" + uuid);
            System.out.println( monPlugin.getPlayerMoney().get(a));
            System.out.println( "Debug command money 4 :");


            if (monPlugin.getPlayerMoney().containsKey(uuid)) {
                System.out.println("Debug command money 4");
                final Integer money = monPlugin.getPlayerMoney().get(uuid);

                sender.sendMessage("Vous avez : " + money + "$");
            }
        }

        return false;
    }
 }
