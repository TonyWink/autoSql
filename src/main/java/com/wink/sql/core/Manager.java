package com.wink.sql.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Manager {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> thread = new ThreadLocal<Connection>();

    public Manager(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        Manager.dataSource = dataSource;
    }

    public static Connection getConnection() {
        try {
            Connection con = thread.get();
            if (con != null) return con;
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw null;
    }

    public static void closeConnection(Connection connection){
        try {
            Connection con = thread.get();
            if (connection != con) {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
