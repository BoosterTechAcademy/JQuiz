package com.example.Login.security;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryUtil {

    private final Cloudinary cloudinary;

    public CloudinaryUtil(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * Upload Image to Cloudinary
     */
    public String uploadImage(MultipartFile file) throws IOException {
        validateFile(file);

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "explanations/images",
                        "resource_type", "image"
                )
        );
        return result.get("secure_url").toString();
    }

    /**
     * Upload Video to Cloudinary
     */
    public String uploadVideo(MultipartFile file) throws IOException {
        validateFile(file);

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "explanations/videos",
                        "resource_type", "video"
                )
        );
        return result.get("secure_url").toString();
    }

    /**
     * Delete media from Cloudinary
     */
    public void delete(String publicId, String resourceType) throws IOException {
        cloudinary.uploader().destroy(
                publicId,
                ObjectUtils.asMap("resource_type", resourceType)
        );
    }

    /**
     * Basic file validation
     */
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or missing");
        }

//        if (file.getSize() > 200 * 1024 * 1024) {
//            throw new IllegalArgumentException("File size exceeds 200MB limit");
//        }
    }
}
