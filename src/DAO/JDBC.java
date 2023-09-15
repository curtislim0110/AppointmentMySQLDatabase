package DAO;
import java.sql.Connection;
import java.sql.DriverManager;


public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection dbConn;  // Connection Interface

    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            dbConn = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            dbConn.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            // better to not do anything, because the program is closing when closeConnection() is called
        }
    }

    // getDatabase() is used to get a database after openConnection() is called in main, improves the speed of database access
    public static Connection getDatabase() {
        return dbConn;
    }
}