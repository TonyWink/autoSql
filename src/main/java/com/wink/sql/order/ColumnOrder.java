package com.wink.sql.order;


import com.wink.sql.annonations.Column;
import com.wink.sql.annonations.ID;
import com.wink.sql.enums.Constraint;
import com.wink.sql.mapper.TypeMapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public final class ColumnOrder extends BaseOrder{
    private static final Constraint [] defCons={Constraint.NOTNULL};
    private static ColumnConfig defConfig=new ColumnConfig(null,null,defCons);

    public static String getColumnName(Field field){
        Column column=field.getAnnotation(Column.class);
        if(column==null){
            return field.getName();
        }
        return column.value();
    }

    public  static String getColumnName(PropertyDescriptor prop,Class clazz){
        Field [] fields=clazz.getDeclaredFields();
        for(Field field : fields){
            if(prop.getName().equals(field.getName())){
                return getColumnName(field);
            }
        }
        System.out.println("no such property "+prop.getName()+" column name found !");
        return null;
    }

    public static void addConstraint(StringBuffer order, Constraint cons){
        order.append(cons.Value());
    }


    public static Boolean isPrimaryKey(Field field){
        ID id=field.getAnnotation(ID.class);
        if(id!=null) return true;
        return false;
    }

    public static OrderSet setColumn(Class<?> clazz, Field field, OrderSet orders, ColumnConfig config){
        StringBuffer order=orders.getOrder();
        order.append(getColumnName(field));
        if(config==null) config=defConfig;
        order.append(TypeMapper.sqlType(clazz, config.getSize()));
        if(isPrimaryKey(field))
            addConstraint(order,Constraint.PRIMARYKEY);
        if(config.getCons()!=null) {
            for (Constraint con : config.getCons())
                addConstraint(order, con);
        }
        order.append(",");
        return orders;
    }

}
