package com.saresource.aws;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class AwsS3IO {
    final private AmazonS3 s3Client;

    public AwsS3IO(Regions region) {
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(region).build();
    }

    String download(String bucketName, String fileName) {
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, fileName));
        InputStream objectData = object.getObjectContent();

        String data;
        try {
            data = StreamUtils.copyToString(objectData, Charset.defaultCharset());
            objectData.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public void upload(String bucketName, String newFileName, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, newFileName, file));
    }

    /**
     * Delete file from S3 bucket.
     * @param bucketName Bucket to delete files from
     * @param fileToDelete Name of file to delete
     */
    void delete(String bucketName, String fileToDelete) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileToDelete));
    }
}
