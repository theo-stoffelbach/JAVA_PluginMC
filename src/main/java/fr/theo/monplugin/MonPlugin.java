package fr.theo.monplugin;

import fr.theo.monplugin.DB.DbManager;
import fr.theo.monplugin.commands.CommandGetMoney;
import fr.theo.monplugin.commands.CommandTest;
import fr.theo.monplugin.events.PlayerJoin;
import fr.theo.monplugin.recipes.Crafts;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class MonPlugin extends JavaPlugin {
    private DbManager DBManager;
    public UUID a = UUID.fromString("8821ab22-aed3-4cfb-9410-bcb05fe57f0c");
    public Integer b = 1000;
    public HashMap<UUID, Integer> playerMoney = new HashMap<>();

    @Override
    public void onEnable() {
        Management.load();

        this.playerMoney = new HashMap<>();
        this.DBManager = new DbManager();
        this.playerMoney.put(a,b);

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);



        getCommand("test").setExecutor(new CommandTest());
        getCommand("money").setExecutor(new CommandGetMoney(this));
        getServer().addRecipe(Crafts.notchApple());

    }

    @Override
    public void onDisable() {
        Management.unload();

        this.DBManager.close();
    }

    public DbManager getDBManager() {
        return DBManager;
    }

    public HashMap<UUID, Integer> getPlayerMoney() {
        return playerMoney;
    }
}
