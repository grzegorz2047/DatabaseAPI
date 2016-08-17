package pl.grzegorz2047.databaseapi;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.HashMap;
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

    public boolean insertPlayer(String p, String ipAddress) {
       // String ipaddress = "";
       // if (p.getAddress() != null) {
       //     ipaddress = p.getAddress().toString().split(":")[0].substring(1);
       // }
        String query = "INSERT IGNORE INTO Players (userid, language, username, lastip, experience,rank, pets, effects) VALUES (0, 'PL', '" +
                p + "', '" +
                ipAddress + "', 0, 'Gracz', 'false', 'false')";
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

    public SQLUser getPlayer(String p) {
        String query = "SELECT * FROM Players WHERE username='" + p + "' LIMIT 1";
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
                boolean pets = result.getBoolean("pets");
                boolean effects = result.getBoolean("effects");
                String rank = result.getString("rank");
                long rankto = result.getLong("rankto");
                return new SQLUser(userid, username, language, lastip, exp, pets, effects, rank, rankto);
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

    public void changePlayerExp(String player, int exp) {
        Connection c = null;
        Statement st = null;
        try {                                                                                       //UPDATE TheWallsMoney SET money=money + 5 WHERE userid=(SELECT userid FROM Player WHERE Player.username='grzegorz2047')
            String query = "UPDATE Players SET  experience=experience +" + exp + " WHERE username='" + player + "'";
            c = this.getConnection();
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

    public void increaseValueBy(String player, String column, int value) {
        Connection c = null;
        Statement st = null;
        try {                                                                                       //UPDATE TheWallsMoney SET money=money + 5 WHERE userid=(SELECT userid FROM Player WHERE Player.username='grzegorz2047')
            String query = "UPDATE Players SET " + column + "= " + column + "+" + value + " WHERE username='" + player + "'";
            c = this.getConnection();
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

    public HashMap<String, String> getSettings() {
        HashMap<String, String> settings = new HashMap<String, String>();
        String query = "SELECT * FROM Settings";
        Connection c = null;
        Statement st = null;
        try {
            c = this.getConnection();
            st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String path = result.getString("path");
                String value = result.getString("value");
                settings.put(path, value);
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
        return settings;
    }

    public void refreshRank(Player p) {
        String query = "SELECT * FROM Players WHERE username='" + p.getName() + "' LIMIT 1";
        String query2 = "UPDATE Players SET rank='" + "Gracz" + "' WHERE username='" + p.getName() + "' LIMIT 1";
        Connection c = null;
        Statement st = null;
        Statement st2 = null;
        try {
            c = this.getConnection();
            st = c.createStatement();
            st2 = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String rank = result.getString("rank");
                long rankto = result.getLong("rankto");
                Instant instant = Instant.EPOCH;
                System.out.println(instant.getEpochSecond());
                if (rankto >= instant.getEpochSecond() && rank.equals("Vip")) {
                    st2.executeUpdate(query2);
                }
            }

            st.close();
            st2.close();
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
                if (st2 != null) {
                    st2.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
