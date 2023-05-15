package fr.utoka.myplugin;

import fr.utoka.myplugin.Entity.EventPnj;
import fr.utoka.myplugin.itemEvent.EventMenu;
import fr.utoka.myplugin.commands.allCommands;
import fr.utoka.myplugin.itemEvent.PowerSword;
import fr.utoka.myplugin.itemEvent.SwordEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin lancé");
        getCommand("g-ping").setExecutor(new allCommands());
        getCommand("g-alert").setExecutor(new allCommands());
        getCommand("g-help").setExecutor(new allCommands());
        getCommand("g-givesword").setExecutor(new allCommands());
        getCommand("g-fly").setExecutor(new allCommands());
        getServer().getPluginManager().registerEvents(new EventMenu(), this);
        getServer().getPluginManager().registerEvents(new SwordEvent(), this);
        getServer().getPluginManager().registerEvents(new PowerSword(), this);
        getServer().getPluginManager().registerEvents(new EventPnj(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin eteint");
    }
}
