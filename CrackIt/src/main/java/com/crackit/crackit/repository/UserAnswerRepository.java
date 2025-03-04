package com.crackit.crackit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crackit.crackit.model.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer>{
    
}
