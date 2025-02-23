package com.crackit.crackit.service.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;

@Service
public class QuestionServiceImp implements QuestionService{

    private QuestionRepository questionRepository;
    public QuestionServiceImp(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;

    }

    public List<QuestionDTO> getQuestionsBySubtopic(int subTopicId) {
        return questionRepository.findQuestionsBySubtopicId(subTopicId);
    }
}
