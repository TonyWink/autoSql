package com.wink.sql.enums;

public enum Constraint{
    PRIMARYKEY(" PRIMARY KEY"),
    NOTNULL(" NOT NULL"),
    UNIQUE(" UNIQUE KEY"),
    DEFAULT(" DEFAULT ");

    private String value;
    private Boolean hasParm;

    Constraint(String value) {
        this.value=value;
        this.hasParm=false;
    }

    public String Value() {
        return value;
    }

    public Boolean hasParm(){
        return hasParm;
    }

    public Constraint setParm(Object obj){
        if(this.equals(Constraint.DEFAULT)) {
            this.hasParm = true;
            this.value = this.value + obj;
        }else{
            
        }
        return this;
    }
}
