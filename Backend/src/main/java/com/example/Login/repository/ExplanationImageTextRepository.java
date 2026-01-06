package com.example.Login.repository;

import com.example.Login.Model.ExplanationImageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplanationImageTextRepository extends JpaRepository<ExplanationImageText,Integer> {
}
