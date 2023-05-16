package fr.utoka.myplugin.commands;

import fr.utoka.myplugin.MyPlugin;

import fr.utoka.myplugin.Service.CommandMoneyService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;


public class CommandMoney implements CommandExecutor{
    private final MyPlugin monPlugin ;
    private final CommandMoneyService managementMoney = CommandMoneyService.getInstance();


    public CommandMoney(MyPlugin monPlugin)  {
        this.monPlugin = monPlugin;
    }

        /**
         * Give at Player money and add at Hashmap and DB.
         * @param uuid take sender command
         * @param moneyGive take the amount of money to add
         */
        private void giveMoney(UUID uuid,int moneyGive) throws SQLException{
        managementMoney.addMoneyToDB(uuid,moneyGive);
        managementMoney.addMoneyToHash(uuid, moneyGive);
        if (moneyGive != 0) Bukkit.getPlayer(uuid).sendMessage("Vous avez gagner : " + moneyGive + " de la part de " + Bukkit.getPlayer(uuid).getName());
        }

        /**
         * remove at Player money and remove at Hashmap and DB.
         * @param uuid take sender command
         * @param moneyRemove take the amount of money to remove
         */
        public void removeMoney(UUID uuid,int moneyRemove) throws SQLException{
        managementMoney.removeMoneyToDB(uuid,moneyRemove);
        managementMoney.removeMoneyToHash(uuid, moneyRemove);
        }

        /**
         * Set at Player money and set at Hashmap and DB.
         * @param uuid take sender command
         * @param moneyToSet take the amount of money to set
         */
        public void setMoney(UUID uuid,int moneyToSet) throws SQLException{
        managementMoney.setMoneyToDB(uuid,moneyToSet);
        managementMoney.setMoneyToHash(uuid, moneyToSet);
        Bukkit.getPlayer(uuid).sendMessage("Ton argent est à : " + moneyToSet + " à cause de/grace à " + Bukkit.getPlayer(uuid).getName());
        }

    // -------------------------------------------------------------------------------------
    // Private

    /**
     * Set at Player money and set at Hashmap and DB.
     * @param sender it the player
     */
    private void getMoney(CommandSender sender) {
        Player player = (Player) sender;
        final UUID uuid = player.getUniqueId();

        System.out.println(uuid);
        System.out.println(monPlugin.getPlayerMoney().get(uuid));
        System.out.println("Tien : " + monPlugin.getPlayerMoney().containsKey(uuid));

        if (monPlugin.getPlayerMoney().containsKey(uuid)) {
            int money = monPlugin.getPlayerMoney().get(uuid);
            if (money != -1) sender.sendMessage("Vous avez : " + money + "$");
            return;
        }
        sender.sendMessage("Merci de vous déconncter et Reconnecter, si l'erreur persiste, merci de contacter le staff");
        System.out.println("Mince GetMoney Marche pas");
    }

    private void giveMoney(CommandSender sender, String[] arg) {
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
                err.printStackTrace();
            }
        }
    }

    private void removeMoney(CommandSender sender, String[] arg) {
        Player playerAffected = null;
        int moneyToRemove = 0;
        if (arg.length == 1) {
            try {
                moneyToRemove = Integer.parseInt(arg[0]);
                removeMoney(((Player) sender).getUniqueId(), moneyToRemove);
                playerAffected = (Player) sender;
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        else if (arg.length == 2) {
            try {
                UUID uuid = Bukkit.getPlayer(arg[0]).getUniqueId();
                moneyToRemove = Integer.parseInt(arg[1]);
                removeMoney(uuid,moneyToRemove);
                playerAffected = Bukkit.getPlayer(arg[0]);
            }catch (Exception err) {
                err.printStackTrace();
            }
        }
        else sender.sendMessage("Cette commande ne dispose que de 3 commentaire fait un /g-help pour plus d'info");
    }

    private void setMoney(CommandSender sender, String[] arg) {
        System.out.println("SetMoney 0");
        int moneySet = 0;
        Player playerAffected = null;
        if (arg.length == 1) {
            try {
                System.out.println("SetMoney 1");
                moneySet = Integer.parseInt(arg[0]);
                setMoney(((Player) sender).getUniqueId(), moneySet);
                playerAffected = (Player) sender;
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        if (arg.length == 2) {
            try {
                UUID uuid = Bukkit.getPlayer(arg[0]).getUniqueId();
                moneySet = Integer.parseInt(arg[1]);
                setMoney(uuid,moneySet);
                playerAffected = Bukkit.getPlayer(arg[0]);
            }catch (Exception err) {
                err.printStackTrace();
            }
        }else {
            sender.sendMessage("Cette commande ne dispose que de 3 commentaire fait un /g-help pour plus d'info");
        }
    }

    // -------------------------------------------------------------------------------------
    // Command

    /**
     * @param sender, cmd, msg, arg
     * Command OverRide of bukkit to can mamange all money command.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
        System.out.println(monPlugin.getPlayerMoney());

        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("g-getMoney")) getMoney(sender);
            else if (cmd.getName().equalsIgnoreCase("g-giveMoney")) giveMoney(sender,arg);
            else if (cmd.getName().equalsIgnoreCase("g-removeMoney")) removeMoney(sender, arg);
            else if (cmd.getName().equalsIgnoreCase("g-setMoney")) setMoney(sender,arg);
        }

        return false;
    }

}
