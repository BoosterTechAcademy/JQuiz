package com.example.Login.Controller;

import com.example.Login.Model.Explanation;
import com.example.Login.service.ExplanationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/explanation")
@RequiredArgsConstructor
public class ExplanationController {

    private final ExplanationService explanationService;

    @PostMapping("/upload")
    public ResponseEntity<Explanation> upload(
            @RequestParam("image") MultipartFile image,
            @RequestParam("video") MultipartFile video,
            @RequestParam("text") String text,
            @RequestParam("rightAnswer") String rightAnswer,
            @RequestParam("questionType") String questionType


    ) throws IOException {

        Explanation explanation = explanationService.uploadExplanation(
                image, video, text, rightAnswer, questionType
        );

        return new ResponseEntity<>(explanation, HttpStatus.CREATED);
    }
}
