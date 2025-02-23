package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.dto.QuestionDTO;

//import org.springframework.stereotype.Service;

import com.crackit.crackit.model.Question;

public interface QuestionService {
    List<QuestionDTO> getQuestionsForEachSubTopic(int subTopicId);
    
}
