package pl.grzegorz2047.databaseapi;

/**
 * Created by grzegorz2047 on 23.04.2016
 */


public class SQLUser {
    private boolean disguise;
    private String rank;
    private long rankto;
    private int exp;
    private int userid;
    private String username;
    private String language;
    private String lastip;
    private boolean pets;
    private boolean effects;

    public void setRankto(long rankto) {
        this.rankto = rankto;
    }

    public SQLUser(int userid, String username, String language, String lastip, int exp, boolean pets, boolean effects, boolean disguise, String rank, long rankto) {
        this.userid = userid;
        this.username = username;
        this.language = language;
        this.lastip = lastip;
        this.exp = exp;
        this.pets = pets;
        this.effects = effects;
        this.disguise = disguise;
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

    public void setDisguise(boolean disguise) {
        this.disguise = disguise;
    }

    public boolean hasDisguise() {
        return this.disguise;
    }

    public void setEffects(boolean effects) {
        this.effects = effects;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLastip() {
        return this.lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public int getExp() {
        return this.exp;
    }

    public boolean hasPets() {
        return this.pets;
    }

    public boolean hasEffects() {
        return this.effects;
    }

    public String getRank() {
        return this.rank;
    }

    public long getRankto() {
        return this.rankto;
    }

    public boolean isDisguise() {
        return this.disguise;
    }
}
