package pl.grzegorz2047.databaseapi;

/**
 * Created by grzeg on 18.05.2016.
 */
public class StatsUser {
    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getWins() {
        return wins;
    }

    public int getLose() {
        return lose;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    private int kills;
    private int deaths;
    private int wins;
    private int lose;
    private int id;
    private int userid;

    public StatsUser(int id, int userid, int kills, int deaths, int wins, int lose) {
        this.id = id;
        this.userid = userid;
        this.kills = kills;
        this.deaths = deaths;
        this.wins = wins;
        this.lose = lose;

    }

}
