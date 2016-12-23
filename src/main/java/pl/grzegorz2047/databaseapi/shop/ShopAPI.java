package pl.grzegorz2047.databaseapi.shop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import pl.grzegorz2047.databaseapi.DatabaseAPI;
import pl.grzegorz2047.databaseapi.shop.Item;
import pl.grzegorz2047.databaseapi.shop.Transaction;

public class ShopAPI {
    private String shopItemsTable;
    private DatabaseAPI sql;
    private String purchasedItemsTable;

    private ShopAPI() {
    }

    public ShopAPI(String host, int port, String db, String user, String password, String shopItemsTable, String purchasedItemsTable) {
        this.sql = new DatabaseAPI(host, port, db, user, password);
        this.shopItemsTable = shopItemsTable;
        this.purchasedItemsTable = purchasedItemsTable;
    }

    public List<Transaction> getPlayerItems(String player) {
        ArrayList transactions = new ArrayList();
        String query = "SELECT * FROM " + this.purchasedItemsTable + " WHERE userid=(SELECT userid FROM Players WHERE Players.username=\'" + player + "\')";
        Connection c = null;
        Statement st = null;

        try {
            c = this.sql.getConnection();
            st = c.createStatement();
            ResultSet ex = st.executeQuery(query);

            while(ex.next()) {
                int userid = ex.getInt("userid");
                int itemid = ex.getInt("itemid");
                int purchasetime = ex.getInt("purchasetime");
                transactions.add(new Transaction(userid, itemid, purchasetime));
            }

            st.close();
            c.close();
        } catch (SQLException var22) {
            var22.printStackTrace();
        } finally {
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException var21) {
                var21.printStackTrace();
            }

            try {
                if(st != null) {
                    st.close();
                }
            } catch (SQLException var20) {
                var20.printStackTrace();
            }

        }

        return transactions;
    }

    public HashMap<Integer, Item> getShopItems(String type) {
        HashMap items = new HashMap();
        String query = "SELECT * FROM " + this.shopItemsTable + " WHERE type=\'" + type + "\'";
        Connection c = null;
        Statement st = null;

        try {
            c = this.sql.getConnection();
            st = c.createStatement();
            ResultSet ex = st.executeQuery(query);

            while(ex.next()) {
                int itemid = ex.getInt("itemid");
                String name = ex.getString("name");
                Material material = Material.valueOf(ex.getString("material"));
                int amount = ex.getInt("amount");
                String ench1Str = ex.getString("ench1");
                String ench2Str = ex.getString("ench2");
                String[] ench1 = null;
                String[] ench2 = null;
                if(ench1Str != null) {
                    ench1 = ench1Str.split(":");
                }

                if(ench2Str != null) {
                    ench2 = ench2Str.split(":");
                }

                String t = ex.getString("type");
                int slot = ex.getInt("slot");
                int price = ex.getInt("price");
                short data = ex.getShort("data");
                items.put(Integer.valueOf(itemid), new Item(itemid, name, material, amount, ench1, ench2, t, slot, price, data));
            }

            st.close();
            c.close();
        } catch (SQLException var31) {
            var31.printStackTrace();
        } finally {
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException var30) {
                var30.printStackTrace();
            }

            try {
                if(st != null) {
                    st.close();
                }
            } catch (SQLException var29) {
                var29.printStackTrace();
            }

        }

        return items;
    }

    public void buyItem(String player, String itemid, long time) {
        Connection c = null;
        Statement st = null;

        try {
            String ex = "INSERT IGNORE INTO " + this.purchasedItemsTable + "  ( `userid`, `itemid`, `purchasetime`) " + " VALUES " + " ((SELECT userid FROM Players WHERE Players.username=\'" + player + "\'), " + itemid + ", " + time + ")";
            c = this.sql.getConnection();
            st = c.createStatement();
            st.execute(ex);
            st.close();
            c.close();
        } catch (SQLException var20) {
            var20.printStackTrace();
        } finally {
            try {
                if(c != null) {
                    c.close();
                }
            } catch (SQLException var19) {
                var19.printStackTrace();
            }

            try {
                if(st != null) {
                    st.close();
                }
            } catch (SQLException var18) {
                var18.printStackTrace();
            }

        }

    }
}
