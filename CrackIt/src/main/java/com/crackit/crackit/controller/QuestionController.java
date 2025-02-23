package com.crackit.crackit.controller;

import com.crackit.crackit.dto.QuestionDTO;
//import com.crackit.crackit.service.ServiceImp.*;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/each-subtopic")
    public ResponseEntity<List<QuestionDTO>> getQuestionsBySubtopic(@RequestParam int subTopicId) {
        List<QuestionDTO> questions = questionService.getQuestionsBySubtopic(subTopicId);
        return ResponseEntity.ok(questions);
    }
}
