package com.example.Login.service;

import com.example.Login.Model.ExplanationImageText;
import com.example.Login.repository.ExplanationImageTextRepository;
import com.example.Login.security.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExplanationImageTextService {

    @Autowired
    private final CloudinaryUtil cloudinaryUtil;

    @Autowired
    private final ExplanationImageTextRepository explanationImageTextRepository;

    public ExplanationImageTextService(
            CloudinaryUtil cloudinaryUtil,
            ExplanationImageTextRepository explanationImageTextRepository
    ) {
        this.cloudinaryUtil = cloudinaryUtil;
        this.explanationImageTextRepository = explanationImageTextRepository;
    }


    public String uploadImageToText(
            MultipartFile image
    ) throws IOException {

        String imageUrl = cloudinaryUtil.uploadImage(image);


        return imageUrl;
    }

    public ExplanationImageText uploadImageToTextExplanation(
            MultipartFile image,
            MultipartFile video,
            String text,
            String rightAnswer,
            String questionType
    ) throws IOException {

        String imageUrl = cloudinaryUtil.uploadImage(image);
        String videoUrl = cloudinaryUtil.uploadVideo(video);

        ExplanationImageText exp = new ExplanationImageText();
        exp.setExplanationText(text);
        exp.setExplanationImageUrl(imageUrl);
        exp.setExplanationVideoUrl(videoUrl);
        exp.setRightAnswerImageText(rightAnswer);
        exp.setQuestionType(questionType);

        return  explanationImageTextRepository.save(exp);
    }


    public ExplanationImageText uploadTextToImageExplanation(
            MultipartFile image,
            MultipartFile video,
            String text,
            String rightAnswerImage,
            String questionType
    ) throws IOException {

        String imageUrl = cloudinaryUtil.uploadImage(image);
        String videoUrl = cloudinaryUtil.uploadVideo(video);

        ExplanationImageText exp = new ExplanationImageText();
        exp.setExplanationText(text);
        exp.setExplanationImageUrl(imageUrl);
        exp.setExplanationVideoUrl(videoUrl);
        exp.setExplanationImageRightAnswer(rightAnswerImage);
        exp.setQuestionType(questionType);

        return  explanationImageTextRepository.save(exp);
    }
}
