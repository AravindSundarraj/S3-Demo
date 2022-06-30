package com.sample.s3.s3demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


public class S3Config {

    private static final S3Config s3Config = new S3Config();

    private AmazonS3 amazonS3;

    public AmazonS3 getAmazonS3(){
        return this.amazonS3;
    }

    private S3Config(){
        if(Objects.nonNull(s3Config) && s3Config instanceof S3Config){
            throw new RuntimeException("S3 Object is already created !!! ");
        }
        AWSCredentials credentials = new BasicAWSCredentials(
                "add your access key",
                "add your secret key"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        this.amazonS3 = s3client;
    }

    public static S3Config getInstance(){
        return s3Config;
    }


}
