package fr.utoka.myplugin;

import fr.utoka.myplugin.DB.DbManager;
import fr.utoka.myplugin.commands.EventMenu;
import fr.utoka.myplugin.commands.MobsEvent;
import fr.utoka.myplugin.commands.allCommands;
import fr.utoka.myplugin.commands.commandMoney;
import fr.utoka.myplugin.events.PlayerJoin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class MyPlugin extends JavaPlugin {
    private DbManager DBManager;
    public HashMap<UUID, Integer> playerMoney = new HashMap<>();


    @Override
    public void onEnable() {
        System.out.println("Plugin lancé");

        this.playerMoney = new HashMap<>();
        this.DBManager = new DbManager();

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);


        getCommand("test").setExecutor(new allCommands());
        getCommand("alert").setExecutor(new allCommands());
        getCommand("aide").setExecutor(new allCommands());

            getCommand("G-getMoney").setExecutor(new commandMoney(this));

        getServer().getPluginManager().registerEvents(new EventMenu(), this);
        getServer().getPluginManager().registerEvents(new MobsEvent(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin eteint");
    }


    private void createCommandsMoney() throws SQLException {


    }

    public DbManager getDBManager() {
        return DBManager;
    }

    public HashMap<UUID, Integer> getPlayerMoney() {
        return playerMoney;
    }

    public MyPlugin getPlugin() {
        return  this;
    }

}
