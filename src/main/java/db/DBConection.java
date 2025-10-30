package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConection {
   private  static DBConection instance;
   private Connection connection;
    private DBConection() throws SQLException {
   this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
    }
public  Connection getConnection(){
        return  connection;
}
public static DBConection getInstance() throws SQLException {
       return null==instance?instance =new DBConection():instance;
}
}
