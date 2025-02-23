package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.crackit.crackit.model.Question;

public interface QuestionService {
    List<Question> getQuestionsForEachSubTopic(int subTopicId);
    
}
