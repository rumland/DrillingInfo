package com.saresource.aws;

import com.amazonaws.regions.Regions;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AwsRedshiftDatabaseIOIT {
    private final AwsRedshiftDatabaseIO dbIo = AwsRedshiftDatabaseIO.build();
    private final AwsS3IO awsS3IO = new AwsS3IO(Regions.US_EAST_1);
    private final String bucketName = "sa-resources-s3-bucket/temp";
    private final String iamRole = "arn:aws:iam::673800128558:role/s3-to-redshift";
    private final String categoryTableName = "category";
    private final String inputFileName = "category_object_auto.json";

    //TODO ru: Figure out how to get input json file to have array of objects.
    @Test
    public void copyS3JsonFileToRedshiftTableTest() throws Exception {
        createCategoryTable();
        copyTestFileToS3Bucket();

        String query = String.format("copy %s from 's3://%s/%s' iam_role '%s' json 'auto'",
                categoryTableName, bucketName, inputFileName, iamRole);
        int updateResult = dbIo.updateQuery(query);
        Assert.assertEquals(0, updateResult);
    }

    private void createCategoryTable() {
        dbIo.executeQuery(String.format("create table if not exists %s (\n" +
                "  catid integer primary key,\n" +
                "  catdesc\tvarchar(255),\n" +
                "  catname\tvarchar(255),\n" +
                "  catgroup\tvarchar(255))", categoryTableName));
    }

    private void copyTestFileToS3Bucket() {
        final File file = new File(this.getClass().getResource(inputFileName).getPath());
        awsS3IO.copyFileToAwsS3(file, bucketName);
    }
}