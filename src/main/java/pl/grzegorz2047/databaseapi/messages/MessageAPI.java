package pl.grzegorz2047.databaseapi.messages;

import pl.grzegorz2047.databaseapi.DatabaseAPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by grzegorz2047 on 22.04.2016
 */
public class MessageAPI {
    private DatabaseAPI sql;
    //path, msg obj
    private HashMap<String, Message> messages = new HashMap<String, Message>();
    private String minigame;

    public MessageAPI(String host, int port, String db, String user, String password, String minigame) {
        sql = new DatabaseAPI(host, port, db, user, password);
        this.minigame = minigame;
        loadMessages();
    }

    private void loadMessages() {
        String query = "SELECT * FROM Messages WHERE minigame='" + minigame + "'";
        try {
            Statement st = sql.getConnection().createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String language = result.getString("language");
                String path = result.getString("path");
                String message = result.getString("message");
                boolean found = false;
                for (Map.Entry<String, Message> msg : messages.entrySet()) {
                    if (msg.getKey().equals(path)) {
                        found = true;
                        msg.getValue().addMessage(language, message);
                        break;
                    }
                }
                if (!found) {
                    Message msg = new Message(path);
                    msg.addMessage(language, message);
                    this.messages.put(path, msg);
                }
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMessage(String lang, String path) {
        for (Map.Entry<String, Message> msg : messages.entrySet()) {
            if (msg.getKey().equals(path)) {
                return msg.getValue().getMessage(lang);
            }
        }
        return "No message added yet!";
    }
}
