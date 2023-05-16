package fr.utoka.myplugin.events;

import fr.utoka.myplugin.DB.DbConnection;
import fr.utoka.myplugin.MyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private final MyPlugin monPlugin;
    public PlayerJoin(MyPlugin monPlugin) {
        this.monPlugin = monPlugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)  {
        final UUID uuid = event.getPlayer().getUniqueId();
        final DbConnection getMoneyConnection = monPlugin.getDBManager().getPlayersData();

        try {
        final Connection connection = getMoneyConnection.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, money FROM money WHERE uuid = ?");
            preparedStatement.setString(1,uuid.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Integer money = resultSet.getInt("money");
                monPlugin.getPlayerMoney().put(uuid,money);
                System.out.println(monPlugin.getPlayerMoney());

            }else {
                createUser(connection, uuid);
            }
        }catch (SQLException err) {
            err.printStackTrace();
        }
    }

    private void createUser(Connection connection, UUID uuid) {
    try {
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO money VALUES (?,?,?,?)");
        final long time = System.currentTimeMillis();
        final Timestamp timeNow = new Timestamp(time);

        preparedStatement.setString(1,uuid.toString());
        preparedStatement.setInt(2, 1000);
        preparedStatement.setTimestamp(3, timeNow);
        preparedStatement.setTimestamp(4, timeNow);
        preparedStatement.executeUpdate();

        monPlugin.getPlayerMoney().put(uuid, 1000);

    }catch (SQLException err) {
        err.printStackTrace();
    }

    }

}
