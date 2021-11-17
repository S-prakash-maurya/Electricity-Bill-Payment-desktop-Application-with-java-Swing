
import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

    Connection c;

    public dbConnection() {

        try {

            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_data", "root", "Satya@12345");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;

    }

}
