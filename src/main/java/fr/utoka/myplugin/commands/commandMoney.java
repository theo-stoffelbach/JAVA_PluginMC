package fr.utoka.myplugin.commands;

import fr.utoka.myplugin.DB.DbConnection;
import fr.utoka.myplugin.MyPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


public class commandMoney implements CommandExecutor{
    private MyPlugin monPlugin ;
    private final DbConnection getMoneyConnection;

    public commandMoney(MyPlugin monPlugin)  {

        System.out.println("init 0");
        this.monPlugin = monPlugin;
        getMoneyConnection = monPlugin.getDBManager().getPlayersData();
        System.out.println("init 1");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
        System.out.println("Debug command money 1");
        System.out.println(monPlugin.getPlayerMoney());


        if (sender instanceof Player) {
            System.out.println("Debug command money 2");
            Player player = (Player) sender;
            final UUID uuid = player.getUniqueId();

            System.out.println( "Debug command money 3 : WHAT" + uuid);
            System.out.println( monPlugin.getPlayerMoney().get(uuid));
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
