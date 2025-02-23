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
        List<Question> list = new ArrayList<>();
        List<QuestionDTO> list2 = new ArrayList<>();
       Subtopic subtopic = subtopicRepository.findById(subTopicId);
        list = questionRepository.findBySubtopic(subtopic);
        for(Question q : list){
         QuestionDTO a = new QuestionDTO(q.getId(),q.getQuestionText());
         list2.add(a);
        }
        return list2;
    
}

    
}
