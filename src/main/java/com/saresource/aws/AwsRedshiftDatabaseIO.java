package com.saresource.aws;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AwsRedshiftDatabaseIO {
    private final JdbcTemplate jdbcTemplate;

    public AwsRedshiftDatabaseIO(String dbUrl, String masterUserName, String masterUserPassword) {
        try {
            Class.forName("com.amazon.redshift.jdbc42.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl, masterUserName, masterUserPassword);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    <T> List<T> executeQuery(String query, BeanPropertyRowMapper<T> beanPropertyRowMapper) {
        return jdbcTemplate.query(query, beanPropertyRowMapper);
    }

    int updateQuery(String query) {
        return jdbcTemplate.update(query);
    }

    public int[] batchUpdateQuery(String query, BatchPreparedStatementSetter setter) {
        return jdbcTemplate.batchUpdate(query, setter);
    }
}
