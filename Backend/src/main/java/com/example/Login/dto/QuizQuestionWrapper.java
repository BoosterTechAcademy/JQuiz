package com.example.Login.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionWrapper {

    private int qId;
    private String qText;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String Author;

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

}
