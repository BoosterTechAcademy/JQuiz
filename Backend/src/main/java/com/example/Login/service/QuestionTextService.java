package com.example.Login.service;


import com.example.Login.Model.Explanation;
import com.example.Login.Model.QuestionText;
import com.example.Login.repository.QuestionTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QuestionTextService {

    @Autowired
    private QuestionTextRepository questionTextRepository;
    @Autowired
    private ExplanationService explanationService;

    public ResponseEntity<QuestionText> addQuestionNormal(
            String qText,String optionOne,String optionTwo,String optionThree,
            String optionFour,String rightAnswer,String author,String questionType,
            String explanationText,String explanationRightAnswer,
            MultipartFile image, MultipartFile video) throws IOException {


        Explanation explanation = explanationService.uploadExplanation(image,video,explanationText,explanationRightAnswer,questionType);

        QuestionText questionText = new QuestionText();

        questionText.setQText(qText);
        questionText.setOptionOne(optionOne);
        questionText.setOptionTwo(optionTwo);
        questionText.setOptionThree(optionThree);
        questionText.setOptionFour(optionFour);
        questionText.setRightAnswer(rightAnswer);
        questionText.setAuthor(author);
        questionText.setQuestionType(questionType);
        questionText.setExplanation(explanation);


       return new ResponseEntity<>(questionTextRepository.save(questionText), HttpStatus.CREATED);
    }

    public ResponseEntity<QuestionText> addQuestionFillInTheBlanks(String qText, String rightAnswer, String author, String questionType, String explanationText, String explanationRightAnswer, MultipartFile image, MultipartFile video)

            throws IOException
    {

        Explanation explanation = explanationService.uploadExplanation(image,video,explanationText,explanationRightAnswer,questionType);

        QuestionText questionText=new QuestionText();

        questionText.setQText(qText);
        questionText.setRightAnswer(rightAnswer);
        questionText.setAuthor(author);
        questionText.setQuestionType(questionType);
        questionText.setExplanation(explanation);

        return new ResponseEntity<>(questionTextRepository.save(questionText),HttpStatus.CREATED);
    }

}
