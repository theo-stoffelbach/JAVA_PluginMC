package fr.utoka.myplugin;

import fr.utoka.myplugin.commands.EventMenu;
import fr.utoka.myplugin.commands.MobsEvent;
import fr.utoka.myplugin.commands.allCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin lancé");
        getCommand("test").setExecutor(new allCommands());
        getCommand("alert").setExecutor(new allCommands());
        getCommand("aide").setExecutor(new allCommands());
        getServer().getPluginManager().registerEvents(new EventMenu(), this);
        getServer().getPluginManager().registerEvents(new MobsEvent(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin eteint");
    }
}
