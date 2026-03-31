package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

public class ConnectionPool {

    private static final LinkedList<Connection> pool = new LinkedList<>();
    private static final int POOL_SIZE = 5;

    static {
        try {
            String url = ApplicationConfig.getProperty("db.url");
            String username = ApplicationConfig.getProperty("db.username");
            String password = ApplicationConfig.getProperty("db.password");

            for (int i = 0; i < POOL_SIZE; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error creating connection pool", e);
        }
    }

    public static synchronized Connection getConnection() {
        return pool.removeFirst();
    }

    public static synchronized void releaseConnection(Connection conn) {
        pool.addLast(conn);
    }
}
