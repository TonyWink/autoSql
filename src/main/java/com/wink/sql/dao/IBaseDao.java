package com.wink.sql.dao;

import java.util.List;

public interface IBaseDao<Entity> {


	void insert(Entity entity);

	List<Entity> selectAll();

	List<Entity> selectItems(Entity entity);

	Entity selectById(Object obj);

	Entity selectOne(Entity entity);

	void deleteItems(Entity entity);

    void deleteAll();
}
