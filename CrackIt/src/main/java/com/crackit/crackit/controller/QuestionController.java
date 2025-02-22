package com.crackit.crackit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.model.Question;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;

    }
    @GetMapping("/each-subtopic")
    public List<QuestionDTO> getQuestionForEachSubTopic(@RequestParam int subTopicId) {
        return questionService.getQuestionsForEachSubTopic(subTopicId);
    }
    
}
