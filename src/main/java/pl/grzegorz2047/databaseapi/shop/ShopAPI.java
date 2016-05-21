package pl.grzegorz2047.databaseapi.shop;

import pl.grzegorz2047.databaseapi.DatabaseAPI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by grzeg on 21.05.2016.
 */
public class ShopAPI {

    private String table;
    private DatabaseAPI sql;
    private String moneyTable;

    private ShopAPI() {
    }

    public ShopAPI(String host, int port, String db, String user, String password, String table) {
        sql = new DatabaseAPI(host, port, db, user, password);
        this.table = table;
    }


    public int getPlayerItems(String player) {
        String query = "SELECT * FROM " + table + " WHERE userid=(SELECT userid FROM Players WHERE Players.username='" + player + "') LIMIT 1";
        Connection c = null;
        Statement st = null;
        try {
            c = sql.getConnection();
            st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                int money = result.getInt("money");
                return money;
            }
            st.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }


}
