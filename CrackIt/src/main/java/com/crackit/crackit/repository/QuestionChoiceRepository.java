package com.crackit.crackit.repository;

import com.crackit.crackit.model.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Integer> {
    List<QuestionChoice> findByQuestionId(int questionId);
    QuestionChoice findByIdAndQuestionId(int choiceId, int questionId);
}
