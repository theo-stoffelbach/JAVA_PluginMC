package fr.utoka.myplugin;

import fr.utoka.myplugin.commands.CommandTest;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("Le plugin est lancé");
        getCommand("test").setExecutor(new CommandTest());
        getCommand("alert").setExecutor(new CommandTest());
    }

    @Override
    public void onDisable() {
        System.out.println("Le plugin est mort");
    }
}
