package com.wink.sql.utils;

import com.wink.sql.core.SqlFactory;
import javafx.beans.property.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collections;

public class PropertyUtils {
	private static final Logger LOGGER= LogManager.getLogger(PropertyUtils.class);

	public static Object getPropertyValue(String name,Class clazz){
		try {
			PropertyDescriptor[] properties = getProperties(clazz);
			PropertyDescriptor property = getPropertyByName(name, properties);
			Object obj=property.getReadMethod().invoke(clazz);
			return obj;
		}catch (Exception e){

		}
		return null;
	}

	private static PropertyDescriptor getPropertyByName(String name,PropertyDescriptor[] properties){
		for(PropertyDescriptor property:properties){
			if(name.equals(property.getName()))
				return property;
		}
		LOGGER.error("can't find property by name-->"+name);
		return null;
	}

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
	//需要一个按正常顺序排列的Property
	public static PropertyDescriptor[] getProperties(Class clazz){
    try {
			return Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e){
			throw new RuntimeException(e);
		}
	}

	public static PropertyDescriptor[] getSortProperties(Class clazz){
		Field[] fields=clazz.getDeclaredFields();
		PropertyDescriptor[] properties=getProperties(clazz);
		PropertyDescriptor[] result=new PropertyDescriptor[properties.length];
		for(int i=0;i<fields.length;i++){
			result[i]=getPropertyByName(fields[i].getName(),properties);
		}
        return result;
	}

}
