package com.wink.sql.utils;

import com.wink.sql.annonations.ID;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public class PropertyUtils {

	public static String[] getFieldNames(Class clazz){
		try {
            Field[] fields=clazz.getDeclaredFields() ;
            String[] names =new String[fields.length];
            for(int i=0;i<fields.length;i++){
              names[i]=fields[i].getName();
            }
            return names;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//warning: it do not order by class declared fields type !
	public static PropertyDescriptor[] getProperties(Class clazz){
    try {
			return Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e){
			throw new RuntimeException(e);
		}
	}

	public static Boolean isPrimaryKey(Field field){
		ID id=field.getAnnotation(ID.class);
		if(id!=null) return true;
		return false;

	}
}
