package com.crackit.crackit.service.ServiceImp;

import java.util.stream.Collectors;
import java.util.*;
import org.springframework.stereotype.Service;

import com.crackit.crackit.dto.ChoiceValidationDTO;
import com.crackit.crackit.dto.QuestionChoiceDTO;
import com.crackit.crackit.model.QuestionChoice;
import com.crackit.crackit.repository.QuestionChoiceRepository;
import com.crackit.crackit.service.ServiceInterfaces.QuestionChoiceService;
@Service
public class QuestionChoiceServiceImp implements QuestionChoiceService {
   
    private QuestionChoiceRepository questionChoiceRepository;
    public QuestionChoiceServiceImp(QuestionChoiceRepository questionChoiceRepository){
        this.questionChoiceRepository = questionChoiceRepository;

    }
   @Override
    public List<QuestionChoiceDTO> getChoicesByQuestionId(int questionId) {
        List<QuestionChoice> choices = questionChoiceRepository.findByQuestionId(questionId);
        return choices.stream()
                .map(choice -> new QuestionChoiceDTO(choice.getId(), choice.getChoiceText()))
                .collect(Collectors.toList());
    }
    @Override
    public ChoiceValidationDTO validateChoice(int questionId, int choiceId) {
        QuestionChoice choice = questionChoiceRepository.findByIdAndQuestionId(choiceId, questionId);
         return new ChoiceValidationDTO(
                questionId,
                choice.isCorrect(),
                choice.getQuestion().getExplanation()
            );
       
    }

    
}
