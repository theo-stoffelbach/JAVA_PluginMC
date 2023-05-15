package fr.utoka.myplugin;

import fr.utoka.myplugin.DB.DbManager;
import fr.utoka.myplugin.Listener.KillMobs;
import fr.utoka.myplugin.Service.CommandMoneyService;
import fr.utoka.myplugin.commands.CommandMoney;
import fr.utoka.myplugin.commands.EventMenu;
import fr.utoka.myplugin.commands.MobsEvent;
import fr.utoka.myplugin.commands.allCommands;
import fr.utoka.myplugin.events.PlayerJoin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class MyPlugin extends JavaPlugin {
    private DbManager DBManager;
    public HashMap<UUID, Integer> playerMoney = new HashMap<>();
//    public HashMap<UUID, Number> playerLevel = new HashMap<>();


    @Override
    public void onEnable() {
        System.out.println("Plugin lancé");

        this.playerMoney = new HashMap<>();
//        this.playerLevel = new HashMap<>();
        this.DBManager = new DbManager();
        try {
            createCommandsMoney();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);


        getCommand("test").setExecutor(new allCommands());
        getCommand("alert").setExecutor(new allCommands());
        getCommand("aide").setExecutor(new allCommands());

        getCommand("getMoney").setExecutor(new CommandMoney(this));
        getCommand("giveMoney").setExecutor(new CommandMoney(this));
        getCommand("removeMoney").setExecutor(new CommandMoney(this));
        getCommand("setMoney").setExecutor(new CommandMoney(this));

        getServer().getPluginManager().registerEvents(new EventMenu(), this);
//        getServer().getPluginManager().registerEvents(new MobsEvent(), this);
        getServer().getPluginManager().registerEvents(new KillMobs(), this);
    }

    @Override
    public void onDisable() {
        this.DBManager.close();
        System.out.println("Plugin eteint");
    }


    private void createCommandsMoney() throws SQLException {
        CommandMoneyService.getInstance(this);

    }

    public DbManager getDBManager() {
        return DBManager;
    }

    public HashMap<UUID, Integer> getPlayerMoney() {
        return playerMoney;
    }

}
