package com.example.Login.service;

import com.example.Login.Model.Explanation;
import com.example.Login.repository.ExplanationRepository;
import com.example.Login.security.CloudinaryUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExplanationService {

    private final CloudinaryUtil cloudinaryUtil;
    private final ExplanationRepository explanationRepository;

    public ExplanationService(
            CloudinaryUtil cloudinaryUtil,
            ExplanationRepository explanationRepository
    ) {
        this.cloudinaryUtil = cloudinaryUtil;
        this.explanationRepository = explanationRepository;
    }

    public Explanation uploadExplanation(
            MultipartFile image,
            MultipartFile video,
            String text,
            String rightAnswer,
            String questionType
    ) throws IOException {

        String imageUrl = cloudinaryUtil.uploadImage(image);
        String videoUrl = cloudinaryUtil.uploadVideo(video);

        Explanation exp = new Explanation();
        exp.setExplanationText(text);
        exp.setExplanationImageUrl(imageUrl);
        exp.setExplanationVideoUrl(videoUrl);
        exp.setRightAnswer(rightAnswer);
        exp.setQuestionType(questionType);

        return  explanationRepository.save(exp);
    }
}
