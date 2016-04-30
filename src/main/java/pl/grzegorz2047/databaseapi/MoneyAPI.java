package pl.grzegorz2047.databaseapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by grzegorz2047 on 23.04.2016
 */
public class MoneyAPI {
    private DatabaseAPI sql;
    private String moneyTable;

    private MoneyAPI() {
    }

    public MoneyAPI(String host, int port, String db, String user, String password) {
        sql = new DatabaseAPI(host, port, db, user, password);
    }

    public void insertPlayer(String player) {
        try {
            String query = "INSERT IGNORE INTO "+getMoneyTable()+ "  (userid, money, id) "+ " VALUES " + " ((SELECT userid FROM Players WHERE Players.username='" + player + "'), 0, 0)";
            Connection connection = sql.getConnection();
            Statement st = connection.createStatement();
            st.execute(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPlayer(String player){
        return 0;
    }

    public void changePlayerMoney(String player, int money) {
        try {                                                                                       //UPDATE TheWallsMoney SET money=money + 5 WHERE userid=(SELECT userid FROM Player WHERE Player.username='grzegorz2047')
            String query = "UPDATE " + getMoneyTable() + " SET money=money+'" + money + "' WHERE userid=" + "(SELECT userid FROM Players WHERE Players.username='" + player + "')";
            Connection connection = sql.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMoneyTable() {
        return moneyTable;
    }

    public void setMoneyTable(String moneyTable) {
        this.moneyTable = moneyTable;
    }

}
