package com.wink.sql.resolver;

import com.wink.sql.order.ColumnOrder;
import com.wink.sql.utils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class ResultResolver<Entity> {

	public List<Entity> resultsMapper(ResultSet rs, Class clazz) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException {
		List<Entity> list=new ArrayList<Entity>();
		while(rs.next()){
			Entity target=resultMapper(rs,clazz);
			list.add(target);
	   }
		return list;
	}

	public Entity resultMapper(ResultSet rs, Class clazz) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException {
        Entity target=(Entity)clazz.newInstance();
        PropertyDescriptor[] props;
        props = PropertyUtils.getSortProperties(clazz);
        for(PropertyDescriptor property:props){
            Object obj=rs.getObject(ColumnOrder.getColumnName(property,clazz));
            Method method=property.getWriteMethod();
            method.invoke(target,obj);
        }
        return target;
    }

}
