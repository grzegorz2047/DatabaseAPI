package pl.grzegorz2047.databaseapi;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

/**
 * Created by grzegorz2047 on 23.04.2016
 */
public class Database extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Mysql system is on!");
    }

    @Override
    public void onDisable() {

        System.out.println("Mysql system is off!");
    }

}
