package com.wink.sql.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SqlOperater {
    private static final Logger LOGGER= LogManager.getLogger(SqlOperater.class);

    public static Boolean execute(String sql){
        try{
            Connection con = ConfigManager.getConnection();
            PreparedStatement st=con.prepareStatement(sql);
            return st.execute();
        }catch (Exception e){
            LOGGER.error("execute error:"+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean execute(String sql, HashMap<Integer,Object> parms){
       try{
           Connection con = ConfigManager.getConnection();
           PreparedStatement st=con.prepareStatement(sql);
           setParms(st,parms);
           return st.execute();
       }catch (Exception e){
           LOGGER.error("execute error:"+e.getMessage());
           e.printStackTrace();
       }
       return false;
    }

        public static ResultSet executeQuery(String sql){
        try {
            Connection con = ConfigManager.getConnection();
            PreparedStatement st=con.prepareStatement(sql);
            return st.executeQuery();
        }catch (Exception e){
            LOGGER.error("execute error:"+e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("executeQuery failed.");
    }

    public static ResultSet executeQuery(String sql, HashMap<Integer,Object> parms){
        try{
            Connection con = ConfigManager.getConnection();
            PreparedStatement st=con.prepareStatement(sql);
            setParms(st,parms);
            return st.executeQuery();
        }catch (Exception e){
            LOGGER.error("execute error:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQuery(String sql,Object id){
        try{
            Connection con = ConfigManager.getConnection();
            PreparedStatement st=con.prepareStatement(sql);
            LOGGER.info("parm key-->"+id);
            st.setObject(1,id);
            return st.executeQuery();
        }catch (Exception e){
            LOGGER.error("execute error:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static PreparedStatement setParms(PreparedStatement statement,HashMap<Integer,Object> parms) throws SQLException {
        Iterator iter = parms.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            Integer index = (Integer) entry.getKey();
            Object value = entry.getValue();
            statement.setObject(index+1,value);
        }
        return statement;
    }
}
