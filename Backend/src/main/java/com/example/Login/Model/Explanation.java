package com.example.Login.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Explanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int explanationId;
    private String explanationText;
    //image
    private String explanationImageUrl;
    //video
    private String explanationVideoUrl;

    private String rightAnswer;

    private String questionType;
}
