package pl.grzegorz2047.databaseapi;

import java.sql.*;

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
        Connection c = null;
        Statement st = null;
        try {
            String query = "INSERT IGNORE INTO " + getMoneyTable() + "  (userid, money, id) " + " VALUES " + " ((SELECT userid FROM Players WHERE Players.username='" + player + "'), 0, 0)";
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

    public int getPlayer(String player) {
        String query = "SELECT * FROM " + getMoneyTable() + " WHERE userid=(SELECT userid FROM Players WHERE Players.username='" + player + "') LIMIT 1";
        Connection c = null;
        Statement st = null;
        try {
            c = sql.getConnection();
            st = c.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                return result.getInt("money");
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
        return -1;
    }

    public void changePlayerMoney(String player, int money) {
        Connection c = null;
        Statement st = null;
        try {                                                                                       //UPDATE TheWallsMoney SET money=money + 5 WHERE userid=(SELECT userid FROM Player WHERE Player.username='grzegorz2047')
            String query = "UPDATE " + getMoneyTable() + " SET money=money+'" + money + "' WHERE userid=" + "(SELECT userid FROM Players WHERE Players.username='" + player + "')";
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

    public String getMoneyTable() {
        return moneyTable;
    }

    public void setMoneyTable(String moneyTable) {
        this.moneyTable = moneyTable;
    }

}
