package com.example.Login.Controller;


import com.example.Login.Model.QuestionImageText;
import com.example.Login.service.QuestionImageTextService;
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
@RequestMapping("/api/questionimage")
public class QuestionImageTextController {


    @Autowired
    public QuestionImageTextService questionImageTextService;

    @PostMapping(value = "/type/imagetotext", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuestionImageText> addQuestionImageToText(

            @RequestParam("imageText") String imageText,
            @RequestParam("questionImage") MultipartFile questionImage,
            @RequestParam("questionImageTextOptionOne") String questionImageTextOptionOne,
            @RequestParam("questionImageTextOptionTwo") String questionImageTextOptionTwo,
            @RequestParam("questionImageTextOptionThree") String questionImageTextOptionThree,
            @RequestParam("questionImageTextOptionFour") String questionImageTextOptionFour,
            @RequestParam("questionType") String questionType,
            @RequestParam("author") String author,
            @RequestParam("explanationText") String explanationText,
            @RequestParam("rightAnswerImageText") String rightAnswerImageText,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video

    ) throws IOException {

        return questionImageTextService.addQuestionImageToText(imageText, questionImage, questionImageTextOptionOne,
                questionImageTextOptionTwo, questionImageTextOptionThree, questionImageTextOptionFour,
                questionType, author, explanationText, rightAnswerImageText, image, video);


    }

    @PostMapping(value = "/type/texttoimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuestionImageText> addQuestionTextToImage(

            @RequestParam("imageText") String imageText,
            @RequestParam("questionImageOptionOne") MultipartFile questionImageOptionOne,
            @RequestParam("questionImageOptionTwo") MultipartFile questionImageOptionTwo,
            @RequestParam("questionImageOptionThree") MultipartFile questionImageOptionThree,
            @RequestParam("questionImageOptionFour") MultipartFile questionImageOptionFour,
            @RequestParam("questionType") String questionType,
            @RequestParam("author") String author,
            @RequestParam("explanationText") String explanationText,
            @RequestParam("questionImageRightAnswer") MultipartFile questionImageRightAnswer,
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video

    ) throws IOException {

        return questionImageTextService.addQuestionTextToImage(imageText, questionImageOptionOne,
                questionImageOptionTwo, questionImageOptionThree, questionImageOptionFour,
                questionType, author, explanationText, questionImageRightAnswer, image, video);


    }
}