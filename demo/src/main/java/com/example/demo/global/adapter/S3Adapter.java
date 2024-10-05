package com.example.demo.global.adapter;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Adapter {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; // 버킷 이름

    public String uploadImage(MultipartFile file, String name) {
        ObjectMetadata metadata = new ObjectMetadata();
        String folder = name + "/";
        String fileName = folder + UUID.randomUUID() + ".png";
        metadata.setContentLength(file.getSize());
        metadata.setContentType("image/png");
        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드에 실패하였습니다.");
        }
    }

    public void deleteImage(String url) {
        try{
            String splitStr = ".com/";
            String fileName = url.substring(url.lastIndexOf(splitStr) + splitStr.length());
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } catch (SdkClientException e){
            throw new RuntimeException("이미지 삭제에 실패하였습니다.");
        }
    }
}
