package com.wink.sql.order;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wink.sql.enums.Constraint;
import com.wink.sql.mapper.TypeMapper;
import com.wink.sql.utils.PropertyUtils;

/**
 * @author wink
 * ָ order set operater,create sql.
 */
public class OrderSet {
	 private static final String SELECT_HEADER="SELECT * FROM ";
	 private static final String DELETE_HEADER="DELETE FROM ";
	 private static final String INSERT_HEADER="INSERT INTO ";
	 private static final String UPDATE_HEADER="UPDATE ";
	 
	 private static OrderSet orders=new OrderSet();
     private static StringBuffer order;
     
     public OrderSet(){
    	 this.order=new StringBuffer();
     }

    public StringBuffer getOrder() {
        return order;
    }

    public OrderSet select(Class<?> clazz){
    	 order.append(SELECT_HEADER)
    	      .append(TableOrder.getTableName(clazz));
    	 return orders;
     }

     public OrderSet delete(Class<?> clazz){
    	 order.append(DELETE_HEADER)
    	      .append(TableOrder.getTableName(clazz));
    	 return orders; 
     }
     
     public OrderSet insert(Class<?> clazz){
    	 order.append(INSERT_HEADER)
    	      .append(TableOrder.getTableName(clazz));
    	 return orders;
     }
     
     public OrderSet update(Class<?> clazz){
    	 order.append(UPDATE_HEADER)
    	      .append(TableOrder.getTableName(clazz));
    	 return orders;
     }
     
     public <T> OrderSet where(T t){
    	 try{
	    	 order.append(" WHERE ");
	    	 PropertyDescriptor[] props=PropertyUtils.getProperties(t.getClass());
	    	 for(PropertyDescriptor prop:props){
	    		 Method method=prop.getReadMethod();
	    		 Object obj=method.invoke(t);
	    		 if(obj!=null){
	    			 order.append(prop.getName()+"=? AND ");
	    		 }
	    	 }
	    	 order.replace(order.length()-4,order.length(),"");
	    	 return orders;
    	 }catch(Exception e){
    		 throw new RuntimeException(e);
    	 }
     }
     
     public <T> OrderSet values(T t){
    	 int len=PropertyUtils.getProperties(t.getClass()).length;
    	 order.append(" VALUES (");
    	 for(int i=0;i<len;i++){
    		 order.append("?,");
    	 }
    	 order.replace(order.length()-1,order.length(),"")
    	      .append(");");
    	 return orders;
     }
     
     public <T> OrderSet set(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
         order.append(" SET ");
    	 PropertyDescriptor[] props=PropertyUtils.getProperties(t.getClass());
    	 for(PropertyDescriptor prop:props){
    		 Method method=prop.getReadMethod();
    		 Object obj=method.invoke(t);
    		 if(obj!=null){
    			 order.append(prop.getName()+"=? ,");
    		 }
    	 }
    	 order.replace(order.length()-1,order.length(),"");
    	 return orders;
     }
      
     public <T> OrderSet byId(T t){
    	 Field[] fields =t.getClass().getFields();
    	 System.out.println(fields);
    		 for(Field field:fields){
    			 Annotation[] annotations=field.getAnnotations();
    			 System.out.println(annotations);
    			 if(annotations!=null){
    				 System.out.println(annotations);
    				 for(Annotation annotation:annotations){
    					 if(annotation.toString().equals("ID")){
    						 System.out.println(field.getName());
    					 }
    				 }
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
