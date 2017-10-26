package com.wink.sql.test.beans;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wink.sql.core.ConfigManager;

import javax.sql.DataSource;

public class INit {
    static {
        DataSource dataSource= new ComboPooledDataSource();
        ConfigManager.setDataSource(dataSource);
    }
}
