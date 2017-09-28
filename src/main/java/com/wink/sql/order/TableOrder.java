package com.wink.sql.order;

import com.wink.sql.annonations.Table;

public class TableOrder extends BaseOrder {


    public static String getTableName(Class clazz){
        Table annotation= (Table) clazz.getDeclaredAnnotation(Table.class);
        if(annotation==null)
            return clazz.getSimpleName();
        return   annotation.value();
    }

    public static  OrderSet createTable(Class clazz,OrderSet orders){
        orders.getOrder().append("CREATE TABLE "+getTableName(clazz)+" (");
        return orders;
    }
}
