package fr.utoka.myplugin;

import fr.utoka.myplugin.Entity.EventPnj;
import fr.utoka.myplugin.Listener.KillMobs;
import fr.utoka.myplugin.commands.CommandMoney;
import fr.utoka.myplugin.commands.allCommands;
import fr.utoka.myplugin.events.PlayerJoin;
import fr.utoka.myplugin.itemEvent.ArtefactItems;
import fr.utoka.myplugin.itemEvent.EventMenu;
import fr.utoka.myplugin.itemEvent.PowerSword;
import fr.utoka.myplugin.itemEvent.SwordEvent;
import org.bukkit.MusicInstrument;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public class Manager {


    /**
     * This class is used to manage all the commands of the server
     * @param myPlugin is for add at this plugin the commands
     */
    public void managementCommand(MyPlugin myPlugin) {
        myPlugin.getCommand("g-ping").setExecutor(new allCommands());
        myPlugin.getCommand("g-alert").setExecutor(new allCommands());
        myPlugin.getCommand("g-help").setExecutor(new allCommands());
        myPlugin.getCommand("g-givesword").setExecutor(new allCommands());
        myPlugin.getCommand("g-fly").setExecutor(new allCommands());

        myPlugin.getCommand("g-spwanmob").setExecutor(new allCommands());

        myPlugin.getCommand("getMoney").setExecutor(new CommandMoney(myPlugin));
        myPlugin.getCommand("giveMoney").setExecutor(new CommandMoney(myPlugin));
        myPlugin.getCommand("removeMoney").setExecutor(new CommandMoney(myPlugin));
        myPlugin.getCommand("setMoney").setExecutor(new CommandMoney(myPlugin));
    }

    /**
     * This class is used to manage all the events of the server
     * @param myPlugin is for add at this plugin the events
     */
    public void managementEvents(MyPlugin myPlugin) {
        myPlugin.getServer().getPluginManager().registerEvents(new PlayerJoin(myPlugin),myPlugin);

        myPlugin.getServer().getPluginManager().registerEvents(new EventMenu(), myPlugin);
        myPlugin.getServer().getPluginManager().registerEvents(new SwordEvent(), myPlugin);
        myPlugin.getServer().getPluginManager().registerEvents(new PowerSword(), myPlugin);
        myPlugin.getServer().getPluginManager().registerEvents(new EventPnj(), myPlugin);

        myPlugin.getServer().getPluginManager().registerEvents(new ArtefactItems(), myPlugin);
        myPlugin.getServer().getPluginManager().registerEvents(new EventMenu(), myPlugin);
        myPlugin.getServer().getPluginManager().registerEvents(new KillMobs(), myPlugin);
    }
}
