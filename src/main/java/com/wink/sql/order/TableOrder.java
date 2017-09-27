package com.wink.sql.order;

import com.wink.sql.annonations.Table;

public class TableOrder extends BaseOrder {


    public static String getTableName(Class clazz){
        Table annotation= (Table) clazz.getDeclaredAnnotation(Table.class);
        if(annotation==null)
            return clazz.getSimpleName();
        return   annotation.value();
    }

    public static  void createTable(Class clazz,OrderSet order){
        order.getOrder().append("CREATE TABLE "+getTableName(clazz)+" (");
    }
}
