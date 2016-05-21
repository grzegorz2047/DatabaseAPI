package pl.grzegorz2047.databaseapi;

/**
 * Created by grzegorz2047 on 23.04.2016
 */
public class SQLUser {
    private String rank;

    public void setRankto(long rankto) {
        this.rankto = rankto;
    }

    private long rankto;
    private int exp;
    private int userid;
    private String username;
    private String language;
    private String lastip;
    private boolean pets;
    private boolean effects;

    public SQLUser(int userid, String username, String language, String lastip, int exp, boolean pets, boolean effects, String rank, long rankto) {
        this.userid = userid;
        this.username = username;
        this.language = language;
        this.lastip = lastip;
        this.exp = exp;
        this.pets = pets;
        this.effects = effects;
        this.rank = rank;
        this.rankto = rankto;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }


    public void setEffects(boolean effects) {
        this.effects = effects;
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

    public int getExp() {
        return exp;
    }

    public boolean hasPets() {
        return pets;
    }

    public boolean hasEffects() {
        return effects;
    }

    public String getRank() {
        return rank;
    }

    public long getRankto() {
        return rankto;
    }
}
