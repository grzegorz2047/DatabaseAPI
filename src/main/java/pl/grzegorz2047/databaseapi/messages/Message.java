package pl.grzegorz2047.databaseapi.messages;

import java.util.HashMap;

/**
 * Created by grzegorz2047 on 23.04.2016
 */
public class Message {
    public Message(String path) {
        this.path = path;
    }

    //lang, msg
    private HashMap<String, String> lang = new HashMap<String, String>();
    private String path;

    public String getMessage(String lang) {
        String message = this.lang.get(lang);
        if (message == null) {
            return "§cNo message in §c§l" + lang + "§c language for path §c§l" + path + "§c!";
        } else {
            return message;
        }
    }

    public void addMessage(String language, String message) {
        lang.put(language, message);
    }
}
