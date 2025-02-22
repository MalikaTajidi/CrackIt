package com.crackit.crackit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crackit.crackit.model.Subtopic;
import java.util.List;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic,Integer> {
    Subtopic findById(int id);
}
