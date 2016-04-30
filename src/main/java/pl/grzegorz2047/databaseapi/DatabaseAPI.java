package pl.grzegorz2047.databaseapi;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by grzegorz2047 on 22.04.2016
 */
public class DatabaseAPI {
    private String host, user, password, db, table;
    private int port;
    private HikariDataSource hikari;
    //Uzywaj hikari

    private DatabaseAPI() {
    }

    public DatabaseAPI(String host, int port, String db, String user, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.user = user;
        this.password = password;
        connectDB();
    }

    private void connectDB() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", host);
        hikari.addDataSourceProperty("port", port);
        hikari.addDataSourceProperty("databaseName", db);
        hikari.addDataSourceProperty("user", user);
        hikari.addDataSourceProperty("password", password);
        hikari.addDataSourceProperty("cachePrepStmts", true);
        hikari.addDataSourceProperty("prepStmtCacheSize", 250);
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    }

    public Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }

    public HikariDataSource getHikari() {
        return hikari;
    }

    public boolean insertPlayer(Player p) {
        String query = "INSERT IGNORE INTO Players (userid, language, username, lastip, experience) VALUES (0, 'EN', '" + p.getName() + "', '" + p.getAddress().toString().split(":")[0].substring(1) + "', 0)";
        Connection c = null;
        Statement st = null;
        try {
            c = this.getConnection();
            st = c.createStatement();
            boolean answer = st.execute(query);
            st.close();
            c.close();
            return answer;
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
        return false;
    }

    public SQLUser getPlayer(Player p) {
        String query = "SELECT * FROM Players WHERE username='" + p.getName() + "' LIMIT 1";
        Connection c = null;
        Statement st = null;
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String username = result.getString("username");
                String lastip = result.getString("lastip");
                String language = result.getString("language");
                int userid = result.getInt("userid");
                int exp = result.getInt("experience");
                return new SQLUser(userid, username, language, lastip, exp);
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

    //Allows to change language!
    public int updateColumn(String player, String column, String value) {
        String query = "UPDATE Players SET " + column + "='" + value + "' WHERE username='" + player + "'";
        Connection c = null;
        Statement st = null;
        try {
            c = this.getConnection();
            st = c.createStatement();
            int answer = st.executeUpdate(query);
            st.close();
            c.close();
            return answer;
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
        return -1;
    }
}
