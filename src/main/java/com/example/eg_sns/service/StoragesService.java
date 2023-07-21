package com.example.eg_sns.service;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Log4j2
@Service
public class StoragesService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    public StoragesService() {
        AwsCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);
        this.s3Client = S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public String store(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();

        if (StringUtils.isEmpty(fileName)) {
            return null;
        }

        try {
            byte[] bytes = multipartFile.getBytes();

            // S3にアップロードする際のファイルパスを指定
            String s3Key = "profileimg/" + fileName;

            // アップロードリクエストを作成
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(s3Key)
                    .contentType(multipartFile.getContentType())
                    .build();

            // アップロードを実行
            PutObjectResponse response = s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(bytes));

            // アップロードされたファイルのURLを返す
            return "https://" + s3BucketName + ".s3." + awsRegion + ".amazonaws.com/" + s3Key;
        } catch (IOException e) {
            log.error("ファイルアップロード中にエラーが発生しました。", e);
            return null;
        }
    }
}
