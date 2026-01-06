package com.example.Login.service;

import com.example.Login.Model.ExplanationImageText;
import com.example.Login.Model.QuestionImageText;
import com.example.Login.repository.QuestionImageTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class QuestionImageTextService {

    @Autowired
    public ExplanationImageTextService explanationImageTextService;

    @Autowired
    public QuestionImageTextRepository questionImageTextRepository;

    public ResponseEntity<QuestionImageText> addQuestionImageToText(String imageText,
                                                               MultipartFile questionImage, String questionImageTextOptionOne,
                                                               String questionImageTextOptionTwo, String questionImageTextOptionThree,
                                                               String questionImageTextOptionFour, String questionType, String author,
                                                               String explanationText,
                                                               String rightAnswerImageText, MultipartFile image, MultipartFile video

    ) throws IOException {


        ExplanationImageText explanationImageText = explanationImageTextService.uploadImageToTextExplanation(image,video,explanationText,rightAnswerImageText,questionType);

        QuestionImageText questionImageText =new QuestionImageText();
        questionImageText.setImageText(imageText);

        String imageUrl = explanationImageTextService.uploadImageToText(questionImage);

        questionImageText.setQuestionImage(imageUrl);
        questionImageText.setQuestionImageTextOptionOne(questionImageTextOptionOne);
        questionImageText.setQuestionImageTextOptionTwo(questionImageTextOptionTwo);
        questionImageText.setQuestionImageTextOptionThree(questionImageTextOptionThree);
        questionImageText.setQuestionImageTextOptionFour(questionImageTextOptionFour);
        questionImageText.setQuestionType(questionType);
        questionImageText.setAuthor(author);
        questionImageText.setRightAnswerImageText(rightAnswerImageText);
        questionImageText.setExplanationImageText(explanationImageText);

        return new ResponseEntity<>( questionImageTextRepository.save(questionImageText), HttpStatus.CREATED);
    }

    public ResponseEntity<QuestionImageText> addQuestionTextToImage(String imageText, MultipartFile questionImageOptionOne, MultipartFile questionImageOptionTwo, MultipartFile questionImageOptionThree, MultipartFile questionImageOptionFour,
                                                                     String questionType, String author, String explanationText, MultipartFile questionImageRightAnswer, MultipartFile image, MultipartFile video) throws IOException {

        String questionImageRightAnswerUrl = explanationImageTextService.uploadImageToText(questionImageRightAnswer);

        ExplanationImageText explanationImageText = explanationImageTextService.uploadTextToImageExplanation(image,video,explanationText,questionImageRightAnswerUrl,questionType);

        QuestionImageText questionImageText =new QuestionImageText();
        questionImageText.setImageText(imageText);
        String questionImageOptionOneUrl = explanationImageTextService.uploadImageToText(questionImageOptionOne);
        questionImageText.setQuestionImageOptionOneUrl(questionImageOptionOneUrl);
        String questionImageOptionTwoUrl = explanationImageTextService.uploadImageToText(questionImageOptionTwo);
        questionImageText.setQuestionImageOptionTwoUrl(questionImageOptionTwoUrl);
        String questionImageOptionThreeUrl = explanationImageTextService.uploadImageToText(questionImageOptionThree);
        questionImageText.setQuestionImageOptionThreeUrl(questionImageOptionThreeUrl);
        String questionImageOptionFourUrl = explanationImageTextService.uploadImageToText(questionImageOptionOne);
        questionImageText.setQuestionImageOptionFourUrl(questionImageOptionFourUrl);
        questionImageText.setQuestionType(questionType);
        questionImageText.setAuthor(author);
        questionImageText.setQuestionImageRightAnswer(questionImageRightAnswerUrl);
        questionImageText.setExplanationImageText(explanationImageText);

        return  new ResponseEntity<>(questionImageTextRepository.save(questionImageText),HttpStatus.CREATED);

    }
}
