package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtils {

    private final static String SERVERNAME = "localhost";
    private final static String DBNAME = "BookShop";
    private final static String PORTNUMBER = "1433";
    private final static String INSTANCE = ""; 
    private final static String USERID = "sa";
    private final static String PASSWORD = "123";

    public static Connection getConnection() {

        String url;
        if (INSTANCE == null || INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + ";databaseName=" + DBNAME + ";encrypt=false";
        } else {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + "\\" + INSTANCE + ";databaseName=" + DBNAME + ";encrypt=false";
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, USERID, PASSWORD);
            return con;
        } catch (SQLException ex) {
            System.out.println("PRJ301DEMO: Can not connect SQL Server. Reason: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(DBUtils.getConnection());
    }
}
