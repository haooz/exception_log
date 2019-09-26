package com.zkhc.exception_log.config.datasource.extra;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.List;

public class MultiDataSourceExtraProperties {
    private List<DataConnectionProperties> dataConnectionPropertiesList;

    public List<DataConnectionProperties> getDataConnectionPropertiesList() {
        return dataConnectionPropertiesList;
    }

    public void setDataConnectionPropertiesList(List<DataConnectionProperties> dataConnectionPropertiesList) {
        this.dataConnectionPropertiesList = dataConnectionPropertiesList;
    }

    public void config(DruidDataSource druidDataSource, DataConnectionProperties dataConnectionProperties) {
        druidDataSource.setUrl(dataConnectionProperties.getUrl());
        druidDataSource.setUsername(dataConnectionProperties.getUsername());
        druidDataSource.setPassword(dataConnectionProperties.getPassword());
        druidDataSource.setDriverClassName(dataConnectionProperties.getDriverClassName());
        druidDataSource.setValidationQuery(dataConnectionProperties.getValidationQuery());
    }
}
