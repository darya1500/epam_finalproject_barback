package by.epam.learn.daryatarasevich.barback.pool;

import java.util.ResourceBundle;


public class DBManager {
    private final static DBManager instance = new DBManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("dbproperties");

    public static DBManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}