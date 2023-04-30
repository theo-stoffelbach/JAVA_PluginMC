package fr.team.Gedonna.events;

import fr.team.Gedonna.DB.DbConnection;
import fr.team.Gedonna.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private Main monPlugin;

    public PlayerJoin(Main monPlugin) {
        this.monPlugin = monPlugin;
    };

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
                System.out.println("Debug Player Join 1 - " + uuid + " : " + money + " $ ");
                monPlugin.getPlayerMoney().put(uuid,money);
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
