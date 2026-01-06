package com.example.Login.repository;

import com.example.Login.Model.QuestionText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QuestionTextRepository extends JpaRepository<QuestionText,Integer> {

    // Random 5 Fill-in-the-Blank
    @Query(value = "SELECT * FROM question_text WHERE question_type = 'FILL_BLANK' ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<QuestionText> findRandomFillBlank();

    // Random 5 MCQ
    @Query(value = "SELECT * FROM question_text WHERE question_type = 'MCQ' ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
    List<QuestionText> findRandomMCQ();
}
