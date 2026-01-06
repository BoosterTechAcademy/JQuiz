package com.example.Login.Controller;


import com.example.Login.Model.Quiz;
import com.example.Login.dto.QuizQuestionWrapper;
import com.example.Login.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam("title") String title){

        return quizService.createQuiz(title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizQuestionWrapper>> getQuizQuestions(@PathVariable Integer id){

         return quizService.getQuizQuestions(id);
    }
}
