package com.example.Login.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qId;
    private String qText;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String rightAnswer;
    private String Author;
    private String questionType;

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
    @JoinColumn(name = "explanation_id")
    private Explanation explanation;



}
