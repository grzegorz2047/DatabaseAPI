package pl.grzegorz2047.databaseapi;

import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by grzeg on 17.05.2016.
 */
public class StatsAPI {

    private DatabaseAPI sql;
    private String table;

    public StatsAPI(String host, int port, String db, String user, String password, String table) {
        sql = new DatabaseAPI(host, port, db, user, password);
        this.table = table;
    }


    public void increaseValueBy(String player, String column, int value) {
        Connection c = null;
        Statement st = null;
        try {                                                                                       //UPDATE TheWallsMoney SET money=money + 5 WHERE userid=(SELECT userid FROM Player WHERE Player.username='grzegorz2047')
            String query = "UPDATE " + table + " SET " + column + "= " + column + "+" + value + " WHERE userid=(SELECT userid FROM Players WHERE Players.username='" + player + "')";
            c = sql.getConnection();
            st = c.createStatement();
            st.executeUpdate(query);
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
    }

    public void insertPlayer(String player) {
        Connection c = null;
        Statement st = null;
        try {
            String query = "INSERT IGNORE INTO " + table + "  (userid, kills, deaths, wins, lose, id) " + " VALUES " + " ((SELECT userid FROM Players WHERE Players.username='" + player + "'), 0, 0, 0, 0, 0)";
            c = sql.getConnection();
            st = c.createStatement();
            st.execute(query);
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
    }

    public StatsUser getPlayer(Player p) {
        String query = "SELECT * FROM " + table + " WHERE userid=(SELECT userid FROM Players WHERE Players.username='" + p.getName() + "') LIMIT 1";
        Connection c = null;
        Statement st = null;
        try {
            c = sql.getConnection();
            st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                int userid = result.getInt("userid");
                int kills = result.getInt("kills");
                int deaths = result.getInt("deaths");
                int wins = result.getInt("wins");
                int lose = result.getInt("lose");
                return new StatsUser(id, userid, kills, deaths, wins, lose);
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
        return null;
    }


    public int getPlayer(String player) {
        return 0;
    }

}
