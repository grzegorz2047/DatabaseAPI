package pl.grzegorz2047.databaseapi.shop;

public class Transaction {
    private final int userid;
    private final int itemid;
    private final int purchasetime;

    public Transaction(int userid, int itemid, int purchasetime) {
        this.userid = userid;
        this.itemid = itemid;
        this.purchasetime = purchasetime;
    }

    public int getUserid() {
        return this.userid;
    }

    public int getItemid() {
        return this.itemid;
    }

    public int getPurchasetime() {
        return this.purchasetime;
    }
}
