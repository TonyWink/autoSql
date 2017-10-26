package com.wink.sql.mapper;

import com.wink.sql.enums.DbType;

import java.util.HashMap;

public class TypeMapper {
    private static HashMap<Class<?>,SqlType> typeMap=new HashMap<Class<?>,SqlType>();

    static{
        typeMap.put(java.lang.String.class,   new SqlType(DbType.MySql," VARCHAR", 100, true));
        typeMap.put(java.lang.Integer.class,  new SqlType(DbType.MySql," INTEGER", 40,  true));
        typeMap.put(java.lang.Double.class,   new SqlType(DbType.MySql," DOUBLE",  null,false));
        typeMap.put(java.lang.Float.class,    new SqlType(DbType.MySql," FLOAT",   5,   true));
        typeMap.put(java.util.Date.class,     new SqlType(DbType.MySql," DATETIME",null,false));
        typeMap.put(java.lang.Long.class,     new SqlType(DbType.MySql," BIGINT",    null,false));
    }

    private static Class<?> typeTransfer(Class<?> clazz){
        if(clazz.toString().equals("int")){
            return java.lang.Integer.class;
        }else if(clazz.toString().equals("float")){
            return java.lang.Float.class;
        }else if(clazz.toString().equals("double")){
            return java.lang.Double.class;
        }else if(clazz.toString().equals("long")){
            return java.lang.Long.class;
        }
        return clazz;
    }

    public static String sqlType(Class<?> clazz,Integer size){
        clazz=typeTransfer(clazz);
        SqlType type=typeMap.get(clazz);
        if(type==null){
            throw new RuntimeException("the class type"+clazz+"is not supported");
        }else if(type.HasSize()&&size!=null){
            type.setSize(size);
        }else if(!type.HasSize()){
            return type.getSqlType();
        }
        return type.getSqlType() + "(" + type.getSize() + ")";
    }

}
