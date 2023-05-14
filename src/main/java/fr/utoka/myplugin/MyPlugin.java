package fr.utoka.myplugin;

import fr.utoka.myplugin.commands.EventMenu;
import fr.utoka.myplugin.commands.SwordEvent;
import fr.utoka.myplugin.commands.allCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin lancé");
        getCommand("g-ping").setExecutor(new allCommands());
        getCommand("g-alert").setExecutor(new allCommands());
        getCommand("g-help").setExecutor(new allCommands());
        getCommand("g-givesword").setExecutor(new allCommands());
        getServer().getPluginManager().registerEvents(new EventMenu(), this);
        getServer().getPluginManager().registerEvents(new SwordEvent(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin eteint");
    }
}
