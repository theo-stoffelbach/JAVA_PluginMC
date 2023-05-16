package fr.utoka.myplugin.Service;

import fr.utoka.myplugin.DB.DbConnection;
import fr.utoka.myplugin.MyPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class CommandMoneyService {
    private static MyPlugin monPlugin ;
    private static CommandMoneyService m_instance;
    private final DbConnection getMoneyConnection;


    // --------------------------------------------------------
    // Public Methods


    public void addMoneyToDB(UUID uuid, int moneyAdd) throws SQLException {
        int newMoney = monPlugin.playerMoney.get(uuid) + moneyAdd;

        final Connection connection = getMoneyConnection.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE money SET money=? WHERE uuid=?");
        preparedStatement.setInt(1,  newMoney);
        preparedStatement.setString(2, uuid.toString());
        preparedStatement.executeUpdate();
    }
    public void addMoneyToHash(UUID uuid, int moneyAdd) {
        int oldmoneyValue = monPlugin.playerMoney.get(uuid);
        monPlugin.playerMoney.replace(uuid,oldmoneyValue, oldmoneyValue + moneyAdd);
    }

    public void removeMoneyToDB(UUID uuid, int moneyRemove) throws SQLException {
        int oldmoneyValue = monPlugin.playerMoney.get(uuid);

        final Connection connection = getMoneyConnection.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE money SET money=? WHERE uuid=?");
        preparedStatement.setInt(1, oldmoneyValue - moneyRemove);
        preparedStatement.setString(2, uuid.toString());
        preparedStatement.executeUpdate();
    }
    public void removeMoneyToHash(UUID uuid, int moneyRemove) {
        int oldmoneyValue = monPlugin.playerMoney.get(uuid);
        monPlugin.playerMoney.replace(uuid,oldmoneyValue, oldmoneyValue - moneyRemove);
    }

    public void setMoneyToDB(UUID uuid, int moneySet) throws SQLException {
        final Connection connection = getMoneyConnection.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE money SET money=? WHERE uuid=?");
        preparedStatement.setInt(1, moneySet);
        preparedStatement.setString(2, uuid.toString());
        preparedStatement.executeUpdate();
    }

    public void setMoneyToHash(UUID uuid, int moneySet) {
        int oldmoneyValue = monPlugin.playerMoney.get(uuid);
        monPlugin.playerMoney.replace(uuid,oldmoneyValue, moneySet);
    }

    // --------------------------------------------
    // Singleton !


    private CommandMoneyService(MyPlugin monPlugin1, MyPlugin monPlugin)  {
        this.monPlugin = monPlugin1;
        getMoneyConnection = monPlugin.getDBManager().getPlayersData();
    }

    public static CommandMoneyService getInstance(MyPlugin monPlugin1) {
        if (monPlugin == null) {
            m_instance = new CommandMoneyService(monPlugin1, monPlugin1);
            return m_instance;
        }else {
            return m_instance;
        }
    }

    public static CommandMoneyService getInstance() {
        if (m_instance == null) {
            return null;
        }else {
            return m_instance;
        }
    }

}
