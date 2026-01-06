package com.example.Login.Controller;


import com.example.Login.Model.QuestionText;
import com.example.Login.service.QuestionTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/question")
public class QuestionTextController {

    @Autowired
    private QuestionTextService questionTextService;

    @PostMapping(value="/type/normal",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuestionText> addQuestionNormal(

            @RequestParam("qText") String qText,
            @RequestParam("optionOne") String optionOne,
            @RequestParam("optionTwo") String optionTwo,
            @RequestParam("optionThree") String optionThree,
            @RequestParam("optionFour") String optionFour,
            @RequestParam("rightAnswer") String rightAnswer,
            @RequestParam("author") String author,
            @RequestParam("questionType") String questionType,
            @RequestParam("explanationText") String explanationText,
            @RequestParam("explanationRightAnswer") String explanationRightAnswer,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video

            ) throws IOException

    {

        return questionTextService.addQuestionNormal(qText,optionOne,
                optionTwo,optionThree,optionFour,rightAnswer,author,questionType,
                explanationText,explanationRightAnswer,image,video);
    }


    @PostMapping(value="/type/blanks",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuestionText> addQuestionFillInTheBlanks(

            @RequestParam("qText") String qText,
            @RequestParam("rightAnswer") String rightAnswer,
            @RequestParam("author") String author,
            @RequestParam("questionType") String questionType,
            @RequestParam("explanationText") String explanationText,
            @RequestParam("explanationRightAnswer") String explanationRightAnswer,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video

    ) throws IOException

    {

        return questionTextService.addQuestionFillInTheBlanks(qText,rightAnswer,author,questionType,
                explanationText,explanationRightAnswer,image,video);
    }


}
