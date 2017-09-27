package com.wink.sql.dao;

import com.wink.sql.core.Manager;
import com.wink.sql.order.OrderSet;
import com.wink.sql.resolver.*;
import com.wink.sql.utils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.List;

public abstract class BaseDao<Entity> implements IBaseDao<Entity>{
    private static Class<?> clazz;

	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.clazz=(Class)params[0];
        System.out.println(clazz);
    }

	/**
	 * select all data.
	 * @return data list
	 * @throws SQLException 
	 */
	public List<Entity> selectAll(Entity entity) throws Exception{
		OrderSet order=new OrderSet();
		order.select(entity.getClass().getSimpleName());
		Connection con= Manager.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(order.toString());
		return new ResultResolver<Entity>().resultMapper(rs, clazz);
	}
	
	
	/**
	 * select items .
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<Entity> selectItems(Entity entity) throws Exception{
		OrderSet order=new OrderSet();
		order.select(entity.getClass().getSimpleName()).where(entity);
		Connection con= Manager.getConnection();
		PreparedStatement st=con.prepareStatement(order.toString());
		ResultSet rs=setParms(st, entity).executeQuery();
		return new ResultResolver<Entity>().resultMapper(rs, clazz);
	}
	

//	/**
//	 * ��������ɾ����¼
//	 * @param entity
//	 * @return
//	 * @throws Exception
//	 */
//	public Boolean deleteItems(Entity entity) throws Exception{
//		order order=new order();
//		order.delete(entity.getClass().getSimpleName()).where(entity);
//		Connection con=SqlUtils.getConnection();
//		PreparedStatement st=con.prepareStatement(order.toString());
//		setParms(st, entity).execute();
//		return true;
//	}
//
//
//
//	/*
//	 * ����һ����¼
//	 */
//	public Boolean insert(Entity entity) throws Exception{
//		order order=new order();
//		order.insert(entity.getClass().getSimpleName()).values(entity);
//		Connection con=SqlUtils.getConnection();
//		PreparedStatement st=con.prepareStatement(order.toString());
//		setParms(st, entity).execute();
//		return true;
//	}
//
//	public Boolean update(Entity entity) throws Exception{
//		order order=new order();
//		order.update(entity.getClass().getSimpleName()).set(entity).where(entity);
//		Connection con=SqlUtils.getConnection();
//		System.out.println(order.toString());
//		PreparedStatement st=con.prepareStatement(order.toString());
//		setParms(st, entity).execute();
//		return true;
//	}
	
	private PreparedStatement setParms(PreparedStatement st,Entity entity) throws Exception{
		PropertyDescriptor[] props= PropertyUtils.getProperties(entity.getClass());
		int index=1;
		for(PropertyDescriptor prop:props){
			Method method=prop.getReadMethod();
			Object obj=method.invoke(entity);
			if(obj!=null){
				st.setObject(index, obj);
				index++;
			}
		}
		return st;
	}
}
