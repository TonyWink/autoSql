package com.wink.sql.dao;

import java.util.List;

public interface IBaseDao<Entity> {

	List<Entity> selectAll(Entity entity) throws Exception;
}
