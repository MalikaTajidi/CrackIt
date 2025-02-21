package com.crackit.crackit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crackit.crackit.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    
}
