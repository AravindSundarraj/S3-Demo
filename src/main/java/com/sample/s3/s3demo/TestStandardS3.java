package com.sample.s3.s3demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.sample.s3.s3demo.Constants.BUCKET_NAME;


public class TestStandardS3 {

    private AmazonS3 amazonS3;
    private String bucketName;

    private String fileLoc;

    private String bucketKey;

    public TestStandardS3(AmazonS3 amazonS3, String bucketName, String fileLoc, String bucketKey) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.fileLoc = fileLoc;
        this.bucketKey = bucketKey;
    }

    public void createBucket() {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        } else {
            System.out.println("Bucket exist please choose different bucket name");
        }
    }

    public void uploadData() {
        // String bucketName, String key, InputStream input, ObjectMetadata metadata
        // InputStream
        try {
            File file = new File(fileLoc);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            FileUtils.openInputStream(file);
            amazonS3.putObject(bucketName, bucketKey, FileUtils.openInputStream(file), objectMetadata);
            System.out.println("Uploaded sample data");
        } catch (Exception e) {

        }

    }

    public void deleteObject(String object) {
        amazonS3.deleteObject(bucketName, object);
    }

    public void deleteBucket() {
        amazonS3.deleteBucket(bucketName);
    }


    public void getData() {
/*          S3Object s3Object =
        amazonS3.getObject(bucketName, bucketKey);
       InputStream inputStream = s3Object.getObjectContent();*/
        try (InputStream is = amazonS3.getObject(bucketName, bucketKey).getObjectContent()) {
            String s =  StreamUtils.copyToString(is, StandardCharsets.UTF_8);
            System.out.println("The Object Content == >> "+s);
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}