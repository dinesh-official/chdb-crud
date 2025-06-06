package com.devng.chdb_crud.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    // MySQL properties
    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    // ClickHouse properties
    @Value("${spring.datasource.clickhouse.url}")
    private String clickhouseUrl;

    @Value("${spring.datasource.clickhouse.username}")
    private String clickhouseUsername;

    @Value("${spring.datasource.clickhouse.password}")
    private String clickhousePassword;

    // MySQL getters
    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public String getMysqlUsername() {
        return mysqlUsername;
    }

    public String getMysqlPassword() {
        return mysqlPassword;
    }

    // ClickHouse getters
    public String getClickhouseUrl() {
        return clickhouseUrl;
    }

    public String getClickhouseUsername() {
        return clickhouseUsername;
    }

    public String getClickhousePassword() {
        return clickhousePassword;
    }
}
