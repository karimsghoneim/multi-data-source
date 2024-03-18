package com.example.multidatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoutingDataSourceConfig {

    @Autowired
    @Qualifier("db1DataSource")
    private DataSource db1DataSource;

    @Autowired
    @Qualifier("db2DataSource")
    private DataSource db2DataSource;

    @Bean
    public DataSource dataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("db1", db1DataSource);
        targetDataSources.put("db2", db2DataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(db1DataSource); // Default DS
        return routingDataSource;
    }
}
