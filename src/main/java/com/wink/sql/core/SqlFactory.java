package com.wink.sql.core;

import com.wink.sql.enums.SqlType;
import com.wink.sql.order.ColumnConfig;
import com.wink.sql.order.OrderSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class SqlFactory {
    private static final Logger LOGGER= LogManager.getLogger(SqlFactory.class);

    public static <T> String createBaseSql(SqlType sqlType,T t){
        OrderSet order=new OrderSet();
        switch (sqlType){
            case SELECT_WHERE:
                order.select(t.getClass()).where(t);
                break;
            case DELETE_WHERE:
                order.delete(t.getClass()).where(t);
                break;
            case INSERT:
                order.insert(t.getClass()).values(t);
                break;
            case UPDATE_BYID:
                order.update(t.getClass()).set(t).byId(t.getClass());
                break;
            default:
                LOGGER.error("no such sqlType found : "+sqlType);
                break;
        }
        LOGGER.info("SQL-->"+order);
        return order.toString();
    }

    public static String createBaseSql(SqlType sqlType,Class clazz){
        OrderSet order=new OrderSet();
        switch (sqlType){
            case SELECT_ALL:
                order.select(clazz);
                break;
            case DELETE_ALL:
                order.delete(clazz);
                break;
            case SELECT_BYID:
                order.select(clazz).byId(clazz);
                break;
            case DELETE_BYID:
                order.delete(clazz).byId(clazz);
                break;
            default:
                LOGGER.error("no such sqlType found : "+sqlType);
                break;
        }
        LOGGER.info("SQL-->"+order);
        return order.toString();

    }


    public static String createTable(Class<?> clazz, ColumnConfig...configs){
        OrderSet order=new OrderSet();
        order.createTable(clazz,configs);
        LOGGER.info("SQL-->"+order);
        return  order.toString();
    }
}
