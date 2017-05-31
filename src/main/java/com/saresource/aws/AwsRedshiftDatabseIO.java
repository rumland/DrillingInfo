package com.saresource.aws;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

class AwsRedshiftDatabseIO {
    private final JdbcTemplate jdbcTemplate;

    AwsRedshiftDatabseIO(String dbUrl, String masterUserName, String masterUserPassword) {
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
}
