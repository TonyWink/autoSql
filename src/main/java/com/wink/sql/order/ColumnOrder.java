package com.wink.sql.order;


import com.wink.sql.annonations.Column;
import com.wink.sql.enums.Constraint;
import com.wink.sql.mapper.TypeMapper;

import java.lang.reflect.Field;

public final class ColumnOrder extends BaseOrder{

    public static String getColumnName(Field field){
        Column column=field.getAnnotation(Column.class);
        if(column==null){
            return field.getName();
        }
        return column.value();
    }

    public static OrderSet addConstraint(OrderSet orders, Constraint cons){
        orders.getOrder().append(cons.Value());
        return orders;
    }


    public static OrderSet setColumn(Class<?> clazz, Field field, OrderSet orders, ColumnConfig config){
        StringBuffer order=orders.getOrder();
        order.append(getColumnName(field))
                .append(TypeMapper.sqlType(clazz,config.getSize()));
        for(Constraint con : config.getCons())
            order.append(addConstraint(orders,con));
        return orders;
    }

}
