package com.ucap.zfw.task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	@Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "formalDataSource")
    @Qualifier("formalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.formal")
    public DataSource formalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="primaryJdbcTemplate")
    public JdbcTemplate testJdbcTemplate (
            @Qualifier("primaryDataSource")  DataSource testDataSource ) {
        return new JdbcTemplate(testDataSource);
    }

    @Bean(name = "formalJdbcTemplate")
    public JdbcTemplate formalJdbcTemplate(
            @Qualifier("formalDataSource") DataSource formalDataSource){
        return new JdbcTemplate(formalDataSource);
    }

}
