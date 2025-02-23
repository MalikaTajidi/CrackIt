package com.crackit.crackit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crackit.crackit.model.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{
    
}
