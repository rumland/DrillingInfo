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
import java.util.ArrayList;
import java.util.List;

class AwsS3IO {
    final private AmazonS3 s3Client;

    AwsS3IO(Regions region) {
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

    void upload(String existingBucketName, String newFileName, File file) {

        // Create a list of UploadPartResponse objects. You get one of these
        // for each part upload.
        List<PartETag> partETags = new ArrayList<>();

        // Step 1: Initialize.
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(existingBucketName, newFileName);
        InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);

        long contentLength = file.length();
        long partSize = 5242880; // Set part size to 5 MB.

        try {
            // Step 2: Upload parts.
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                // Last part can be less than 5 MB. Adjust part size.
                partSize = Math.min(partSize, (contentLength - filePosition));

                // Create request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(existingBucketName).withKey(newFileName)
                        .withUploadId(initResponse.getUploadId()).withPartNumber(i)
                        .withFileOffset(filePosition)
                        .withFile(file)
                        .withPartSize(partSize);

                // Upload part and add response to our list.
                partETags.add(
                        s3Client.uploadPart(uploadRequest).getPartETag());

                filePosition += partSize;
            }

            // Step 3: Complete.
            CompleteMultipartUploadRequest compRequest = new
                    CompleteMultipartUploadRequest(
                    existingBucketName,
                    newFileName,
                    initResponse.getUploadId(),
                    partETags);

            s3Client.completeMultipartUpload(compRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(
                    existingBucketName, newFileName, initResponse.getUploadId()));
        }
    }
}
