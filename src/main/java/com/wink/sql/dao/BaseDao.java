package com.wink.sql.dao;

import com.wink.sql.core.SqlFactory;
import com.wink.sql.core.SqlOperater;
import com.wink.sql.enums.SqlType;
import com.wink.sql.mapper.ParmMapper;
import com.wink.sql.resolver.ResultResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.List;

public class BaseDao<Entity> implements IBaseDao<Entity>{
    private static Class<?> clazz;

	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.clazz=(Class)params[0];
    }


	public void insert(Entity entity){
		String sql= SqlFactory.createBaseSql(SqlType.INSERT,entity);
		SqlOperater.execute(sql, ParmMapper.fieldMapping(entity));
	}

    @Override
    public List<Entity> selectAll() {
        String sql = SqlFactory.createBaseSql(SqlType.SELECT_ALL, clazz);
        ResultSet rs = SqlOperater.executeQuery(sql);
        try {
            return new ResultResolver<Entity>().resultsMapper(rs, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Entity> selectItems (Entity entity){
        String sql = SqlFactory.createBaseSql(SqlType.SELECT_WHERE, entity);
        ResultSet rs = SqlOperater.executeQuery(sql, ParmMapper.fieldMapping(entity));
        try {
            return new ResultResolver<Entity>().resultsMapper(rs, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entity selectById(Object obj) {
        String sql=SqlFactory.createBaseSql(SqlType.SELECT_BYID,clazz);
        ResultSet rs = SqlOperater.executeQuery(sql,obj);
        try {
            if(rs.next()){
                return new ResultResolver<Entity>().resultMapper(rs,clazz);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entity selectOne(Entity entity) {
        String sql=SqlFactory.createBaseSql(SqlType.SELECT_WHERE,entity);

        return null;
    }

    @Override
    public void deleteItems(Entity entity) {
        String sql=SqlFactory.createBaseSql(SqlType.DELETE_WHERE,entity);
        SqlOperater.execute(sql, ParmMapper.fieldMapping(entity));
    }

    @Override
    public void deleteAll() {
        String sql=SqlFactory.createBaseSql(SqlType.DELETE_ALL,clazz);
        SqlOperater.execute(sql);
    }
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
    }
