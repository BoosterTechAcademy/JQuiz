package com.example.Login.service;


import com.example.Login.Model.QuestionImageText;
import com.example.Login.Model.QuestionText;
import com.example.Login.Model.Quiz;
import com.example.Login.dto.QuizQuestionWrapper;
import com.example.Login.repository.QuestionImageTextRepository;
import com.example.Login.repository.QuestionTextRepository;
import com.example.Login.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    public QuestionTextRepository questionTextRepository;

    @Autowired
    public QuestionImageTextRepository questionImageTextRepository;

    @Autowired
    public QuizRepository quizRepository;

    public ResponseEntity<Quiz> createQuiz(String title) {


        Quiz quiz=new Quiz();
        quiz.setTitle(title);

        List<QuestionText> questionText = questionTextRepository.findRandomMCQ();

        questionText.addAll(questionTextRepository.findRandomFillBlank());

        List<QuestionImageText> questionImageText = questionImageTextRepository.findRandomImageToText();

        questionImageText.addAll(questionImageTextRepository.findRandomTextToImage());

        quiz.setQuestionTextList(questionText);
        quiz.setQuestionImageTextList(questionImageText);

        return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizQuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizRepository.findById(id);


        List<QuestionText> questionFromDBText = quiz.get().getQuestionTextList();

        List<QuestionImageText> questionFromDBImage = quiz.get().getQuestionImageTextList();

       List<QuizQuestionWrapper> questionsForUsers =  new ArrayList<>();

       for(QuestionText record : questionFromDBText){

           QuizQuestionWrapper quizQuestionWrapper = new QuizQuestionWrapper();

           quizQuestionWrapper.setQId(record.getQId());
           quizQuestionWrapper.setAuthor(record.getAuthor());

           if(record.getQuestionType().equals("MCQ")){

               quizQuestionWrapper.setQText(record.getQText());
               quizQuestionWrapper.setOptionOne(record.getOptionOne());
               quizQuestionWrapper.setOptionTwo(record.getOptionTwo());
               quizQuestionWrapper.setOptionThree(record.getOptionThree());
               quizQuestionWrapper.setOptionFour(record.getOptionFour());

           }

           if(record.getQuestionType().equals("FILL_BLANK")){

               quizQuestionWrapper.setQText(record.getQText());
           }


           questionsForUsers.add(quizQuestionWrapper);
       }

        for(QuestionImageText record : questionFromDBImage){

            QuizQuestionWrapper quizQuestionWrapper = new QuizQuestionWrapper();

            quizQuestionWrapper.setQId(record.getQImageId());
            quizQuestionWrapper.setAuthor(record.getAuthor());

            if(record.getQuestionType().equals("imageToText")){

                quizQuestionWrapper.setQText(record.getImageText());
                quizQuestionWrapper.setQuestionImage(record.getQuestionImage());
                quizQuestionWrapper.setQuestionImageTextOptionOne(record.getQuestionImageTextOptionOne());
                quizQuestionWrapper.setQuestionImageTextOptionTwo(record.getQuestionImageTextOptionTwo());
                quizQuestionWrapper.setQuestionImageTextOptionThree(record.getQuestionImageTextOptionThree());
                quizQuestionWrapper.setQuestionImageTextOptionFour(record.getQuestionImageTextOptionFour());

            }

            if(record.getQuestionType().equals("textToImage")){

                quizQuestionWrapper.setQText(record.getImageText());
                quizQuestionWrapper.setQuestionImageOptionOneUrl(record.getQuestionImageOptionOneUrl());
                quizQuestionWrapper.setQuestionImageOptionTwoUrl(record.getQuestionImageOptionTwoUrl());
                quizQuestionWrapper.setQuestionImageOptionThreeUrl(record.getQuestionImageOptionThreeUrl());
                quizQuestionWrapper.setQuestionImageOptionFourUrl(record.getQuestionImageOptionFourUrl());
            }


            questionsForUsers.add(quizQuestionWrapper);
        }

       return new ResponseEntity<>(questionsForUsers,HttpStatus.OK);
    }
}
