package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/quantity_measurement_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}