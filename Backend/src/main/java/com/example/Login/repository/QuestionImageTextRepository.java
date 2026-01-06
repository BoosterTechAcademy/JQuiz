package com.example.Login.repository;

import com.example.Login.Model.QuestionImageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionImageTextRepository extends JpaRepository<QuestionImageText,Integer> {

    // Random 5 Image-to-Text questions
    @Query(value = "SELECT * FROM question_image_text WHERE question_type = 'imageToText' ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<QuestionImageText> findRandomImageToText();

    // Random 5 Text-to-Image questions
    @Query(value = "SELECT * FROM question_image_text WHERE question_type = 'textToImage' ORDER BY RANDOM() LIMIT 2", nativeQuery = true)
    List<QuestionImageText> findRandomTextToImage();
}
