package com.crackit.crackit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.Subtopic;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{
    List<Question> findBySubtopic(Subtopic subtopic);
}
