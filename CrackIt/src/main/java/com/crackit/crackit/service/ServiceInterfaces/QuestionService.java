package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.dto.ChoiceValidationDTO;
import com.crackit.crackit.dto.QuestionDTO;

public interface QuestionService {
    List<QuestionDTO> getQuestionsBySubtopic(int subTopicId);
    
}
