package com.crackit.crackit.repository;

import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT new com.crackit.crackit.dto.QuestionDTO(q.id, q.questionText) FROM Question q WHERE q.subtopic.id = :subTopicId")
    List<QuestionDTO> findQuestionsBySubtopicId(@Param("subTopicId") int subTopicId);
}
