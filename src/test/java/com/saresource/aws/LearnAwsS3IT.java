package com.saresource.aws;

import com.amazonaws.regions.Regions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class LearnAwsS3IT {
    @Test
    public void uploadFileTest() throws IOException {

        final String existingBucketName = "sa-resources-s3-bucket/drilling-info/load";
        final File file = new File(this.getClass().getResource("testAwsS3Upload.txt").getPath());
        final String newFileName = file.getName();

        AwsS3IO s3IO = new AwsS3IO(Regions.US_EAST_1);
        s3IO.upload(existingBucketName, newFileName, file);

        String s3content = s3IO.download(existingBucketName, newFileName);

        String expectedContent = StreamUtils.copyToString(new FileInputStream(file), Charset.defaultCharset());

        System.out.println("S3 content: " + s3content);
        System.out.println("Expected content: " + expectedContent);

        Assert.assertEquals("S3 content should match test content", expectedContent, s3content);
    }
}
