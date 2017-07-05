package com.saresource.aws;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClientBuilder;
import com.amazonaws.services.redshift.model.Cluster;
import com.amazonaws.services.redshift.model.DescribeClustersRequest;
import com.amazonaws.services.redshift.model.Endpoint;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AwsRedshiftDatabaseIO {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Create AwsRedshiftDatabaseIO instance based on AWS_ACCESS_KEY_ID, AWS_DRILLINGINFO_PASSWORD and
     * AWS_SECRET_ACCESS_KEY environment variables.
     *
     * @return new AwsRedshiftDatabaseIO instance
     */
    public static AwsRedshiftDatabaseIO build() {
        EnvironmentVariableCredentialsProvider evcp = new EnvironmentVariableCredentialsProvider();
        AmazonRedshift redshift = AmazonRedshiftClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(evcp).build();
        DescribeClustersRequest dcr = (new DescribeClustersRequest()).withClusterIdentifier("sa-resources-drilling-info");
        Cluster cluster = redshift.describeClusters(dcr).getClusters().get(0);
        Endpoint endpoint = cluster.getEndpoint();
        final String dbUrl = String.format("jdbc:redshift://%s:%s/%s", endpoint.getAddress(), endpoint.getPort(), cluster.getDBName());
        final String masterUserName = cluster.getMasterUsername();
        //TODO ru: Is there a better way to get the master password?
        final String masterUserPassword = System.getenv("AWS_DRILLINGINFO_PASSWORD");

        return new AwsRedshiftDatabaseIO(dbUrl, masterUserName, masterUserPassword);
    }

    private AwsRedshiftDatabaseIO(String dbUrl, String masterUserName, String masterUserPassword) {
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
