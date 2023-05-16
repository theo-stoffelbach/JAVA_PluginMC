package org.example1.versionfinalmc;

import org.bukkit.plugin.java.JavaPlugin;
import org.example1.versionfinalmc.CreeperBow.CreeperBowManager;
import org.example1.versionfinalmc.FireBow.FireBowManager;
import org.example1.versionfinalmc.IceBow.AceBowManager;
import org.example1.versionfinalmc.VampireBow.VampireBowManager;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CreeperBowManager(this), this);
        getServer().getPluginManager().registerEvents(new FireBowManager(this), this);
        getServer().getPluginManager().registerEvents(new AceBowManager(this), this);
        getServer().getPluginManager().registerEvents(new VampireBowManager(this), this);
    }

    @Override
    public void onDisable() {
    }
}
