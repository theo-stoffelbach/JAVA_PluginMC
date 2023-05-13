package fr.utoka.myplugin.commands;

import fr.utoka.myplugin.DB.DbConnection;
import fr.utoka.myplugin.MyPlugin;

import fr.utoka.myplugin.Service.CommandMoneyService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


public class CommandMoney implements CommandExecutor{
    private MyPlugin monPlugin ;
    private final DbConnection getMoneyConnection;
    private CommandMoneyService managementMoney = CommandMoneyService.getInstance();


    public CommandMoney(MyPlugin monPlugin)  {
        this.monPlugin = monPlugin;
        getMoneyConnection = monPlugin.getDBManager().getPlayersData();
    }



    /**
     * @param monPlugin
     * It's a Constructor of commandMoney that manage command link at the money
     */




    /**
     * @param sender
     * Command In game to get money of Player
     */
    private int getMoney(CommandSender sender) {
        Player player = (Player) sender;
        final UUID uuid = player.getUniqueId();

        System.out.println(uuid);
        System.out.println(monPlugin.getPlayerMoney().get(uuid));
        System.out.println("Tien : " + monPlugin.getPlayerMoney().containsKey(uuid));

        if (monPlugin.getPlayerMoney().containsKey(uuid)) {
            return monPlugin.getPlayerMoney().get(uuid);
        }

        return -1;
    }

//    private <T extends UUID & Player> void giveMoney(T player,int moneyGive) throws SQLException{

    /**
     * @param uuid, int
     * Command In game to get money of Player
     */
        public void giveMoney(UUID uuid,int moneyGive) throws SQLException{
//        System.out.println("add money 1");
        managementMoney.addMoneyToDB(uuid,moneyGive);
//        System.out.println("add money 2");
        managementMoney.addMoneyToHash(uuid, moneyGive);
    }
    /**
     * @param uuid, int
     * Command In game to Remove money of Player
     */
        public void removeMoney(UUID uuid,int moneyGive) throws SQLException{
        System.out.println("remove money 1");
        managementMoney.removeMoneyToDB(uuid,moneyGive);
        System.out.println("remove money 2");
        managementMoney.removeMoneyToHash(uuid, moneyGive);
    }
        public void setMoney(UUID uuid,int moneyToSet) throws SQLException{
        System.out.println("set money 1");
        managementMoney.setMoneyToDB(uuid,moneyToSet);
        System.out.println("set money 2");
        managementMoney.setMoneyToHash(uuid, moneyToSet);
    }

    /**
     * @param sender, cmd, msg, arg
     * Command OverRide of bukkit to can mamange all money command.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
        System.out.println(monPlugin.getPlayerMoney());

        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("getMoney")) {
                int money = getMoney(sender);
                if (money != -1) sender.sendMessage("Vous avez : " + money + "$");
                else sender.sendMessage("Merci de vous déconncter et Reconnecter, si l'erreur persiste, merci de contacter le staff");
            }
            else if (cmd.getName().equalsIgnoreCase("giveMoney")) {
                int moneyGive = 0;
                if (arg.length == 1) {
                    try {
                        System.out.println("Debug Give money 1");
                        moneyGive = Integer.parseInt(arg[0]);
                        giveMoney(((Player) sender).getUniqueId(), moneyGive);
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                }else if (arg.length == 2) {
                    try {
                        UUID uuid = Bukkit.getPlayer(arg[0]).getUniqueId();
                        moneyGive = Integer.parseInt(arg[1]);
                        giveMoney(uuid,moneyGive);
                    }catch (Exception err) {
                        System.out.println(err);
                    }
                }
                if (moneyGive != 0) sender.sendMessage("Vous avez gagner : " + moneyGive + (arg.length==2?" de la part de " + sender.getName():"") + ".");
            }
            else if (cmd.getName().equalsIgnoreCase("removeMoney")) {
                int moneyToRemove = 0;
                if (arg.length == 1) {
                    try {
                        moneyToRemove = Integer.parseInt(arg[0]);
                        removeMoney(((Player) sender).getUniqueId(), moneyToRemove);
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                }
                if (arg.length == 2) {
                    try {
                        UUID uuid = Bukkit.getPlayer(arg[0]).getUniqueId();
                        moneyToRemove = Integer.parseInt(arg[1]);
                        removeMoney(uuid,moneyToRemove);
                    }catch (Exception err) {
                        System.out.println(err);
                    }
                }
                if (moneyToRemove != 0) sender.sendMessage("Tu as perdu : " + moneyToRemove + (arg.length==2?" à cause de " + sender.getName():"") + ".");
            }
            else if (cmd.getName().equalsIgnoreCase("setMoney")) {
                System.out.println("SetMoney 0");
                int moneySet = 0;
                if (arg.length == 1) {
                    try {
                        System.out.println("SetMoney 1");
                        moneySet = Integer.parseInt(arg[0]);
                        setMoney(((Player) sender).getUniqueId(), moneySet);
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }
                }
                if (arg.length == 2) {
                    try {
                        UUID uuid = Bukkit.getPlayer(arg[0]).getUniqueId();
                        moneySet = Integer.parseInt(arg[1]);
                        setMoney(uuid,moneySet);
                    }catch (Exception err) {
                        System.out.println(err);
                    }
                }
                if (moneySet != 0) sender.sendMessage("Ton argent est à : " + moneySet + (arg.length==2?" à cause de/grace à " + sender.getName():"") + ".");
            }
        }

        return false;
    }

}
