package fr.utoka.myplugin;


import fr.utoka.myplugin.DB.DbManager;
import fr.utoka.myplugin.Listener.KillMobs;
import fr.utoka.myplugin.Service.CommandMoneyService;
import fr.utoka.myplugin.commands.CommandMoney;
import fr.utoka.myplugin.events.PlayerJoin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;


import fr.utoka.myplugin.Entity.EventPnj;
import fr.utoka.myplugin.commands.allCommands;
import fr.utoka.myplugin.itemEvent.ArtefactItems;
import fr.utoka.myplugin.itemEvent.EventMenu;
import fr.utoka.myplugin.itemEvent.PowerSword;
import fr.utoka.myplugin.itemEvent.SwordEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * main class for plugin
 */
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
        event();
    }

    public void event () {
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);

        Manager manager = new Manager();

        manager.managementCommand(this);
        manager.managementEvents(this);
    }
    @Override
    public void onDisable() {
//        this.DBManager.close();
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
