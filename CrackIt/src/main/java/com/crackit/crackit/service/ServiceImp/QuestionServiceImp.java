package com.crackit.crackit.service.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crackit.crackit.dto.QuestionChoiceDTO;
import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.repository.SubtopicRepository;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;
import java.util.Set;

@Service
public class QuestionServiceImp implements QuestionService {
    private final QuestionRepository questionRepository;
    private final SubtopicRepository subtopicRepository;
    public QuestionServiceImp(QuestionRepository questionRepository,SubtopicRepository subtopicRepository){
        this.questionRepository = questionRepository;
        this.subtopicRepository = subtopicRepository;
     }
     @Override
     public List<QuestionDTO> getQuestionsForEachSubTopic(int subTopicId) {
    Subtopic subtopic = subtopicRepository.findById(subTopicId);
            //.orElseThrow(() -> new RuntimeException("Subtopic not found"));

    List<Question> questions = questionRepository.findBySubtopic(subtopic);

    return questions.stream().map(question -> {
        // Map choices to DTOs
        Set<QuestionChoiceDTO> choiceDTOs = question.getChoices().stream()
                .map(choice -> new QuestionChoiceDTO(
                        choice.getId(),
                        choice.getChoiceText()
                ))
                .collect(Collectors.toSet());

        // Return QuestionDTO with choices
        return new QuestionDTO(
                question.getId(),
                question.getQuestionText(),
                choiceDTOs
        );
    }).collect(Collectors.toList());
}

    
}
