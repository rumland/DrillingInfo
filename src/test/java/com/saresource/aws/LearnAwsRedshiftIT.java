package com.saresource.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClientBuilder;
import com.amazonaws.services.redshift.model.*;
import com.saresource.aws.beans.Identification;
import com.saresource.aws.beans.InformationSchemaTables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.concurrent.TimeUnit;

public class LearnAwsRedshiftIT {
    private AmazonRedshift client;
    private final String dbUrl = "jdbc:redshift://sa-resources-drilling-info.cecvv6wdkflt.us-east-1.redshift.amazonaws.com:5439/drillinginfo";
    private final String masterUserName = "rumland";
    private final String masterUserPassword = "Need to set...";
    private final AwsRedshiftDatabaseIO dbIo = new AwsRedshiftDatabaseIO(dbUrl, masterUserName, masterUserPassword);

    @Before
    public void setUp() throws Exception {
        AmazonRedshiftClientBuilder clientBuilder = AmazonRedshiftClientBuilder.standard().withRegion(Regions.US_WEST_1);
        client = clientBuilder.build();
    }

    @Test
    public void listRedShiftDatabaseTablesTest() {
        dbIo.executeQuery("select * from information_schema.tables;",
                new BeanPropertyRowMapper<>(InformationSchemaTables.class)).forEach(ist ->
            System.out.println(String.format("Catalog: %s; Name: %s", ist.getTable_catalog(), ist.getTable_name()))
        );
    }

    @Test
    public void listIdentificationRecordsTest() {
        dbIo.executeQuery("select * from identification;",
                new BeanPropertyRowMapper<>(Identification.class)).forEach(id ->
                System.out.println(String.format("First: %s; Last: %s; Birthdate: %s", id.getFirst(), id.getLast(), id.getBirthdate()))
        );
    }

    @Test
    public void insertIdentificationRecordTest() {
        int i = dbIo.updateQuery("insert into identification values ('Jennifer', 'Umland', 'February 14, 1984');");
        System.out.println("Returned index? " + i);
        i = dbIo.updateQuery("insert into identification values ('Alyssa', 'Umland', 'October 6, 2014');");
        System.out.println("Returned index? " + i);
    }

    @Test
    public void getDatabaseConnectionThroughClientTest() {
        String clusterIdentifier = "sa-resources-drilling-info";
        DescribeClustersResult result = client.describeClusters(new DescribeClustersRequest()
                .withClusterIdentifier(clusterIdentifier));
        Assert.assertEquals(1, result.getClusters().size());
        String status = (result.getClusters()).get(0).getClusterStatus();
        System.out.println("Status: " + status);
    }

    @Test
    public void createRedshiftClusterTest() {
        String clusterIdentifier = "exampleclusterusingjava";
        if (clusterExists(clusterIdentifier)) {
            deleteCluster(clusterIdentifier);
        }

        CreateClusterRequest request = new CreateClusterRequest()
                .withClusterIdentifier(clusterIdentifier)
                .withMasterUsername("masteruser")
                .withMasterUserPassword("12345678Aa")
                .withNodeType("dc1.large")
                .withNumberOfNodes(2);

        Cluster createResponse = client.createCluster(request);
        waitForClusterReady(clusterIdentifier);
        System.out.println("Created cluster " + createResponse.getClusterIdentifier());

        if (clusterExists(clusterIdentifier)) {
            deleteCluster(clusterIdentifier);
        }
    }

    private void deleteCluster(String clusterIdentifier) {
        DeleteClusterRequest deleteClusterRequest = new DeleteClusterRequest()
                .withClusterIdentifier(clusterIdentifier)
                .withSkipFinalClusterSnapshot(true);
        Cluster deleteCluster = client.deleteCluster(deleteClusterRequest);
        waitForClusterDeleted(deleteCluster.getClusterIdentifier());
    }

    private boolean clusterExists(String clusterIdentifier) {
        try {
            client.describeClusters(new DescribeClustersRequest().withClusterIdentifier(clusterIdentifier)).getClusters().size();
        } catch (ClusterNotFoundException doNotCare) {
            return false;
        }

        return true;
    }

    private void waitForClusterDeleted(String clusterIdentifier) {
        System.out.println("Waiting for cluster to be deleted.");
        while (clusterExists(clusterIdentifier)) {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void waitForClusterReady(String clusterIdentifier) {
        Boolean clusterReady = false;
        System.out.println("Waiting for cluster to become available.");
        while (!clusterReady) {
            DescribeClustersResult result = client.describeClusters(new DescribeClustersRequest()
                    .withClusterIdentifier(clusterIdentifier));
            String status = (result.getClusters()).get(0).getClusterStatus();
            if (status.equalsIgnoreCase("available")) {
                clusterReady = true;
            }
            else {
                System.out.print(".");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
