package com.wink.sql.order;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.wink.sql.annonations.ID;
import com.wink.sql.utils.PropertyUtils;

/**
 * @author wink
 * ָ order set operater,create sql.
 */
public class OrderSet {
    private static final String SELECT_HEADER = "SELECT * FROM ";
    private static final String DELETE_HEADER = "DELETE FROM ";
    private static final String INSERT_HEADER = "INSERT INTO ";
    private static final String UPDATE_HEADER = "UPDATE ";

    private static OrderSet orders = new OrderSet();
    private static StringBuffer order;

    public OrderSet() {
        this.order = new StringBuffer();
    }

    public StringBuffer getOrder() {
        return order;
    }

    public OrderSet select(Class<?> clazz) {
        order.append(SELECT_HEADER)
                .append(TableOrder.getTableName(clazz));
        return orders;
    }

    public OrderSet delete(Class<?> clazz) {
        order.append(DELETE_HEADER)
                .append(TableOrder.getTableName(clazz));
        return orders;
    }

    public OrderSet insert(Class<?> clazz) {
        order.append(INSERT_HEADER)
                .append(TableOrder.getTableName(clazz));
        return orders;
    }

    public OrderSet update(Class<?> clazz) {
        order.append(UPDATE_HEADER)
                .append(TableOrder.getTableName(clazz));
        return orders;
    }

    public <T> OrderSet where(T t) {
        try {
            order.append(" WHERE ");
            PropertyDescriptor[] props = PropertyUtils.getProperties(t.getClass());
            for (PropertyDescriptor prop : props) {
                Method method = prop.getReadMethod();
                Object obj = method.invoke(t);
                if (obj != null) {
                    order.append(ColumnOrder.getColumnName(prop, t.getClass()) + "=? AND ");
                }
            }
            order.replace(order.length() - 4, order.length(), "");
            return orders;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> OrderSet values(T t) {
        int len = PropertyUtils.getProperties(t.getClass()).length;
        order.append(" VALUES (");
        for (int i = 0; i < len; i++) {
            order.append("?,");
        }
        order.replace(order.length() - 1, order.length(), "").append(");");
        return orders;
    }

    public <T> OrderSet set(T t) {
        try {
            order.append(" SET ");
            PropertyDescriptor[] props = PropertyUtils.getProperties(t.getClass());
            for (PropertyDescriptor prop : props) {
                Method method = prop.getReadMethod();
                Object obj = method.invoke(t);
                if (obj != null) {
                    order.append(ColumnOrder.getColumnName(prop, t.getClass()) + "=? ,");
                }
            }
            order.replace(order.length() - 1, order.length(), "");
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }

      
     public <T> OrderSet byId(Class clazz){
         Field[] fields =clazz.getDeclaredFields();
    	for(Field field:fields){
               ID id=field.getDeclaredAnnotation(ID.class);
               if(id!=null){
               	order.append(" WHERE "+ColumnOrder.getColumnName(field)+"=?");
			   }
    	}
    	 return orders;
     }

     public <T> OrderSet createTable(Class clazz,ColumnConfig...configs){
     	Field[] fields=clazz.getDeclaredFields();
     	TableOrder.createTable(clazz,orders);
         System.out.println(configs);
         if(configs.length!=0) {
            for(int i = 0; i < fields.length; i++) {
                for (int j=0; j < configs.length;j++) {
                    if (configs[j].getIndex() == i) {
                        orders = ColumnOrder.setColumn(fields[i].getType(),fields[i], orders, configs[j]);
                    } else {
                        orders = ColumnOrder.setColumn(fields[i].getType(),fields[i], orders, null);
                    }
                }
            }
        }else{
            for(int i = 0; i < fields.length; i++) {
                orders = ColumnOrder.setColumn(fields[i].getType(),fields[i], orders, null);
            }
		}
		order.replace(order.length()-1,order.length(),"").append(")");
     	return orders;
	 }


	@Override
	public String toString() {
		return order.charAt(order.length()-1)==';'? order.toString():order.append(";").toString();
	}
	
	
}
