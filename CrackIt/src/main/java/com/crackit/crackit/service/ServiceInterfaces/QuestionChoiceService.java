package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.dto.ChoiceValidationDTO;
import com.crackit.crackit.dto.QuestionChoiceDTO;

public interface QuestionChoiceService {
    List<QuestionChoiceDTO> getChoicesByQuestionId(int questionId);
   // ChoiceValidationDTO validateChoice(int questionId, int choiceId);
}
