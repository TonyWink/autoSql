package com.wink.sql.resolver;

import com.wink.sql.utils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultResolver<Entity> {
		
	@SuppressWarnings("unchecked")
	public List<Entity> resultMapper(ResultSet rs,Class clazz) throws Exception{
		List<Entity> list=new ArrayList<Entity>();
		while(rs.next()){
			Entity target=(Entity)clazz.newInstance();
		    PropertyDescriptor[] props;
			props = PropertyUtils.getProperties(clazz);
			for(PropertyDescriptor property:props){
				  Object obj=rs.getObject(property.getName());
				  Method method=property.getWriteMethod();
				  method.invoke(target,obj);
			  }	
			list.add(target);
	   }
		return list;
	}
         
}
