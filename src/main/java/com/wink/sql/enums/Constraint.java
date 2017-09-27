package com.wink.sql.enums;

public enum Constraint{
    PRIMARYKEY(" PRIMARY KEY "),
    NOTNULL(" NOT NULL "),
    UNIQUE(" QNIQUE KEY");

    private String value;

    Constraint(String value) {
        this.value=value;
    }

    public String Value() {
        return value;
    }
}
