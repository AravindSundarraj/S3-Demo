package com.sample.s3.s3demo;


import static com.sample.s3.s3demo.Constants.BUCKET_NAME;
import static com.sample.s3.s3demo.Constants.FILE_NAME;


public class S3DemoApplication {





	public static void main(String[] args) {
		TestStandardS3 testStandardS3 = new TestStandardS3(S3Config.getInstance().getAmazonS3(),
				BUCKET_NAME,FILE_NAME,"test-data/loc1");
		testStandardS3.createBucket();
		testStandardS3.uploadData();
		//testStandardS3.deleteObject("bucket-key");
		//testStandardS3.deleteBucket();
	}

}
