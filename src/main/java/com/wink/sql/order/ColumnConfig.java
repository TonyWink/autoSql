package com.wink.sql.order;

import com.wink.sql.enums.Constraint;

public class ColumnConfig {
    private Integer index;
    private Integer size;
    private Constraint[] cons;

    public ColumnConfig(Integer index, Integer size, Constraint[] cons) {
        this.index = index;
        this.size = size;
        this.cons = cons;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Constraint[] getCons() {
        return cons;
    }

    public void setCons(Constraint[] cons) {
        this.cons = cons;
    }
}
