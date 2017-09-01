package com.saresource.aws;

import com.amazonaws.regions.Regions;
import com.saresource.aws.beans.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AwsRedshiftDatabaseIOIT {
    private final AwsRedshiftDatabaseIO dbIo = AwsRedshiftDatabaseIO.build();
    private final AwsS3IO awsS3IO = new AwsS3IO(Regions.US_EAST_1);
    private final String bucketName = "sa-resources-s3-bucket/temp";
    private final String iamRole = "arn:aws:iam::673800128558:role/s3-to-redshift";
    private final String categoryTableName = "category";

    @Before
    public void beforeEachTest() {
        createCategoryTable();
    }

    @After
    public void afterEachTest() {
        deleteCategoryTable();
    }

    @Test
    public void copyS3JsonFileToRedshiftTableTest() throws Exception {
        final String inputFileName = "category_object_auto.json";
        final File inputFile = new File(this.getClass().getResource(inputFileName).getPath());

        try {
            copyTestFileToS3Bucket(inputFile);
            String query = String.format("copy %s from 's3://%s/%s' iam_role '%s' json 'auto'",
                    categoryTableName, bucketName, inputFileName, iamRole);
            int updateResult = dbIo.updateQuery(query);
            assertEquals(0, updateResult);
        } finally {
            deleteTestFileInS3Bucket(inputFileName);
        }
    }

    @Test
    public void copyS3CsvFileToRedshiftTableTest() {
        final String inputFileName = "category_object_auto.csv";
        final File inputFile = new File(this.getClass().getResource(inputFileName).getPath());

        try {
            copyTestFileToS3Bucket(inputFile);
            String query = String.format("copy %s from 's3://%s/%s' iam_role '%s' csv IGNOREHEADER AS 1",
                    categoryTableName, bucketName, inputFileName, iamRole);
            int updateResult = dbIo.updateQuery(query);
            assertEquals(0, updateResult);

            Map<Integer, List<String>> categoryData = new HashMap<>();
            dbIo.runQuery(String.format("select * from %s;", categoryTableName),
                    new BeanPropertyRowMapper<>(Category.class)).forEach(category ->
                    categoryData.put(Integer.parseInt(category.getCatid()),
                            Arrays.asList(category.getCatgroup(),
                                    category.getCatname(),
                                    category.getCatdesc())
                    ));
            Map<Integer, List<String>> expectedCategoryData = new HashMap<>();
            expectedCategoryData.put(1, Arrays.asList("Sports", "MLB", "Major League Baseball"));
            expectedCategoryData.put(2, Arrays.asList("Sports", "NHL", "National Hockey League"));
            expectedCategoryData.put(3, Arrays.asList("Sports", "NFL", "National Football League"));
            expectedCategoryData.put(4, Arrays.asList("Sports", "NBA", "National Basketball Association"));
            expectedCategoryData.put(5, Arrays.asList("Shows", "Musicals", "All symphony concerto and choir concerts"));

            assertEquals(expectedCategoryData, categoryData);
        } finally {
            deleteTestFileInS3Bucket(inputFileName);
        }
    }

    private void createCategoryTable() {
        dbIo.executeQuery(String.format("create table if not exists %s (\n" +
                "  catid integer primary key,\n" +
                "  catdesc\tvarchar(255),\n" +
                "  catname\tvarchar(255),\n" +
                "  catgroup\tvarchar(255))", categoryTableName));
    }

    private void deleteCategoryTable() {
        dbIo.updateQuery(String.format("drop table if exists %s", categoryTableName));
    }

    private void copyTestFileToS3Bucket(File inputFile) {
        awsS3IO.copyFileToAwsS3(inputFile, bucketName);
    }

    private void deleteTestFileInS3Bucket(String inputFileName) {
        awsS3IO.delete(bucketName, inputFileName);
    }
}
