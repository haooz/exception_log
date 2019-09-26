/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zkhc.exception_log.config.datasource.multi;

import cn.stylefeng.roses.core.config.properties.DruidProperties;
import cn.stylefeng.roses.core.datascope.DataScopeInterceptor;
import cn.stylefeng.roses.core.mutidatasource.DynamicDataSource;
import cn.stylefeng.roses.core.mutidatasource.aop.MultiSourceExAop;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zkhc.exception_log.config.datasource.extra.DataConnectionProperties;
import com.zkhc.exception_log.config.datasource.extra.MultiDataSourceExtraProperties;
import com.zkhc.exception_log.config.properties.MutiDataSourceProperties;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "guns.muti-datasource", name = "open", havingValue = "true")
@EnableTransactionManagement(order = 2, proxyTargetClass = true)
@MapperScan(basePackages = {"com.zkhc.exception_log.modular.*.mapper"})
public class MultiDataSourceConfig {

    /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * 多数据源配置 原配置【只是为兼容MultiSourceExAop】
     */
    @Bean
    public MutiDataSourceProperties mutiDataSourceProperties() {
        MutiDataSourceProperties mutiDataSourceProperties=new MutiDataSourceProperties();
        String arr[] = {DatasourceEnum.DATA_SOURCE_EXCEPTION_ADMIN};
        mutiDataSourceProperties.setDataSourceNames(arr);
        return mutiDataSourceProperties;
    }

    /**
     * 多数据源配置 扩展配置
     */
    @Bean
    @ConfigurationProperties(prefix = "guns.muti-datasource")
    public MultiDataSourceExtraProperties multiDataSourceExtraProperties() {
        return new MultiDataSourceExtraProperties();
    }


    /**
     * 多数据源切换的aop
     */
    @Bean
    public MultiSourceExAop multiSourceExAop() {
        return new MultiSourceExAop();
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }



    /**
     * 多数据源连接池配置
     */
    @Bean
    public DynamicDataSource mutiDataSource(DruidProperties druidProperties, MultiDataSourceExtraProperties multiDataSourceExtraProperties) {

        HashMap<Object, Object> hashMap = new HashMap<>();
        DruidDataSource dataSourceGuns = dataSource(druidProperties);
        try{

            dataSourceGuns.init();
            hashMap.put(DatasourceEnum.DATA_SOURCE_EXCEPTION_ADMIN,dataSourceGuns);

            for(DataConnectionProperties dataConnectionProperties:multiDataSourceExtraProperties.getDataConnectionPropertiesList()){
                DruidDataSource dataSource = new DruidDataSource();
                druidProperties.config(dataSource);
                dataConnectionProperties.config(dataSource);
                dataSource.init();

                hashMap.put(dataConnectionProperties.getDataSourceName(),dataSource);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}