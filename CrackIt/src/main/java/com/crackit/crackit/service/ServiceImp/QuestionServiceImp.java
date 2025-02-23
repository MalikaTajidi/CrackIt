package com.crackit.crackit.service.ServiceImp;



import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.repository.SubtopicRepository;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImp implements QuestionService {
    private final QuestionRepository questionRepository;
    private final SubtopicRepository subtopicRepository;
    public QuestionServiceImp(QuestionRepository questionRepository,SubtopicRepository subtopicRepository){
        this.questionRepository = questionRepository;
        this.subtopicRepository = subtopicRepository;
     }
     @Override

     public List<Question> getQuestionsForEachSubTopic(int subTopicId){
       // List<Subtopic> listSubtopics = new ArrayList<>();
        List<Question> listQuestions = new ArrayList<>();
        //listQuestions = questionRepository.findAll().get(subTopicId);
        return listQuestions;

     }

    
}
