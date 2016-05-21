package pl.grzegorz2047.databaseapi.permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzeg on 16.05.2016.
 */
public class Permission {

    private List<String> eligibleRanks = new ArrayList<String>();
    private String permission;

    public Permission(String path){
        this.permission = path;
    }
    public void addEligibleRank(String rank){
        this.eligibleRanks.add(rank);
    }
    public List<String> getEligibleRanks(){
        return this.eligibleRanks;
    }

}
