package com.example.Login.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionImageText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qImageId;
    private String imageText;
    //image
    private String questionImage;
    private String questionImageTextOptionOne;
    private String questionImageTextOptionTwo;
    private String questionImageTextOptionThree;
    private String questionImageTextOptionFour;
    //image
    private String questionImageOptionOneUrl;
    //image
    private String questionImageOptionTwoUrl;
    //image
    private String questionImageOptionThreeUrl;
    //image
    private String questionImageOptionFourUrl;
    private String questionType;
    private String author;
    //image
    private String questionImageRightAnswer;
    private String rightAnswerImageText;

    @Column(updatable = false)
    private LocalDateTime createdTime;

    @Column(updatable = false)
    private String createdDay;

    @PrePersist
    public void onCreate() {
        LocalDateTime istNow =
                LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        this.createdTime = istNow;
        this.createdDay = istNow.getDayOfWeek().name(); // MONDAY, TUESDAY
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "explanation_imageId")
    private ExplanationImageText explanationImageText;
}
