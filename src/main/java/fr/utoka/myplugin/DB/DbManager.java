package fr.utoka.myplugin.DB;

import java.sql.SQLException;

public class DbManager {
    private final DbConnection PlayersData;
    public DbManager() {
        this.PlayersData = new DbConnection(new DbCredentials("localhost","minecraft_server","root","minecraft_server",3306));
    }

    public DbConnection getPlayersData() {
        return PlayersData;
    }

    public void close() {
        try {
            this.PlayersData.close();
        }catch (SQLException err) {
            err.printStackTrace();
        }
    }

}
