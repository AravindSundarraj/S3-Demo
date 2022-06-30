package com.sample.s3.s3demo;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

import static com.sample.s3.s3demo.Constants.BUCKET_NAME;


public class TestStandardS3 {

    private AmazonS3 amazonS3;
    private String bucketName;

    private String fileLoc;

    private String bucketKey;

    public TestStandardS3(AmazonS3 amazonS3,String bucketName,String fileLoc,String bucketKey){
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.fileLoc = fileLoc;
        this.bucketKey = bucketKey;
    }

    public void createBucket(){
        if(!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
        else{
            System.out.println("Bucket exist please choose different bucket name");
        }
    }

    public void uploadData(){
       // String bucketName, String key, InputStream input, ObjectMetadata metadata
       // InputStream
        try{
            File file = new File(fileLoc);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            FileUtils.openInputStream(file);
            amazonS3.putObject(bucketName , bucketKey,FileUtils.openInputStream(file),objectMetadata);
            System.out.println("Uploaded sample data");
        }
        catch(Exception e){

        }

    }

    public void deleteObject(String object){
        amazonS3.deleteObject(bucketName,object);
    }

    public void deleteBucket(){
        amazonS3.deleteBucket(bucketName);
    }
}
