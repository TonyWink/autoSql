package com.wink.sql.mapper;

import com.wink.sql.enums.DbType;

public class SqlType {
    private DbType dbType;
    private String sqlType;
    private Integer size;
    private Boolean hasSize;

    public SqlType(DbType dbType, String sqlType, Integer size, Boolean hasSize) {
        this.dbType = dbType;
        this.sqlType = sqlType;
        this.size = size;
        this.hasSize = hasSize;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public DbType getDbType() {
        return dbType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public Integer getSize() {
        return size;
    }

    public Boolean HasSize() {
        return hasSize;
    }

    public void setHasSize(Boolean hasSize) {
        this.hasSize = hasSize;
    }

    @Override
    public String toString() {
        return "SqlType{" +
                "dbType=" + dbType +
                ", sqlType='" + sqlType + '\'' +
                ", size=" + size +
                ", hasSize=" + hasSize +
                '}';
    }
}
