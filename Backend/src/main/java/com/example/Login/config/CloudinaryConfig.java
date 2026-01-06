package com.example.Login.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dbslo17eh");
        config.put("api_key", "318476818952832");
        config.put("api_secret", "xStLJe8Q00_7j_a4o5WXkw7PijM");

        return new Cloudinary(config);
    }

}
