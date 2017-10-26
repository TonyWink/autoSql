package com.wink.sql.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConfigManager {
    private static DataSource dataSource;
    private static ConfigManager manager;
    private static final Object lock=new Object();
    private static ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
    private static final Logger LOGGER= LogManager.getLogger(ConfigManager.class);

    private ConfigManager(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public static DataSource getDataSource() {
        if(dataSource==null) {
            LOGGER.error("You should configure the datasource first. ");
            throw new RuntimeException("You should configure the datasource first. ");
        }
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        if(manager==null){
            manager=new ConfigManager(dataSource);
        }else{
            LOGGER.info("WARNING:You should not initialize ConfigManager again.");
        }
    }

    public static Connection getConnection() {
        try {
            Connection con = thread.get();
            if (con != null) return con;
            return getDataSource().getConnection();
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
