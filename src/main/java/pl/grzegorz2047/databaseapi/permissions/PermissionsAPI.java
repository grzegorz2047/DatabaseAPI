package pl.grzegorz2047.databaseapi.permissions;

import org.bukkit.Bukkit;
import pl.grzegorz2047.databaseapi.DatabaseAPI;
import pl.grzegorz2047.databaseapi.messages.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by grzeg on 15.05.2016.
 */
public class PermissionsAPI {
    private DatabaseAPI sql;
    private HashMap<String, Permission> permissions = new HashMap<String, Permission>();

    public PermissionsAPI(String host, int port, String db, String user, String password) {
        sql = new DatabaseAPI(host, port, db, user, password);
        loadMessages();
    }
    private void injectPermissionsToBukkit(){
        for (Map.Entry<String, Permission> perm : permissions.entrySet()) {
            //Bukkit.getPluginManager().
        }
    }
    private void loadMessages() {
        String query = "SELECT * FROM Permissions";
        try {
            Connection connection = sql.getConnection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String language = result.getString("language");
                String path = result.getString("path");
                String message = result.getString("message");
                boolean found = false;
                for (Map.Entry<String, Permission> msg : permissions.entrySet()) {

                }
            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
