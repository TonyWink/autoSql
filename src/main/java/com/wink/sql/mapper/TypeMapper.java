package com.wink.sql.mapper;

import com.wink.sql.enums.DbType;

import java.util.HashMap;

public class TypeMapper {
    private static HashMap<Class<?>,SqlType> typeMap=new HashMap<Class<?>,SqlType>();

    static{
        typeMap.put(java.lang.String.class, new SqlType(DbType.MySql,"VACHAR",  100, true));
        typeMap.put(java.lang.Integer.class,new SqlType(DbType.MySql,"INTEGER", 40,  true));
        typeMap.put(java.util.Date.class,   new SqlType(DbType.MySql,"DATETIME",null,false));
        typeMap.put(java.lang.Long.class,   new SqlType(DbType.MySql,"LONG",    100, true));
    }

    public static String sqlType(Class<?> clazz,Integer size){
        SqlType type=typeMap.get(clazz);
        if(type.HasSize()&&size!=null) type.setSize(size);
        if(!type.HasSize()){
            return type.getSqlType();
        }
        return type.getSqlType()+"("+type.getSize()+")";
    }

}
