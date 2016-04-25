package pl.grzegorz2047.databaseapi;

/**
 * Created by grzegorz2047 on 23.04.2016
 */
public class SQLUser {
    private int userid;
    private String username;
    private String language;
    private String lastip;

    public SQLUser(int userid, String username, String language, String lastip){
        this.userid = userid;
        this.username = username;
        this.language = language;
        this.lastip = lastip;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }
}
