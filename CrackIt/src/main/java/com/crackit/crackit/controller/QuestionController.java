package com.crackit.crackit.controller;

import com.crackit.crackit.dto.ChoiceValidationDTO;
import com.crackit.crackit.dto.QuestionChoiceDTO;
import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.service.ServiceInterfaces.QuestionChoiceService;
//import com.crackit.crackit.service.ServiceImp.*;
import com.crackit.crackit.service.ServiceInterfaces.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    
    private QuestionService questionService;
    private QuestionChoiceService questionChoiceService;
    public QuestionController(QuestionService questionService, QuestionChoiceService questionChoiceService){
        this.questionService = questionService;
        this.questionChoiceService = questionChoiceService;

    }

    @GetMapping("/each-subtopic")
    public ResponseEntity<List<QuestionDTO>> getQuestionsBySubtopic(@RequestParam int subTopicId) {
        List<QuestionDTO> questions = questionService.getQuestionsBySubtopic(subTopicId);
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/Choices")
    public ResponseEntity<List<QuestionChoiceDTO>> getChoicesByQuestionId(@RequestParam int questionId) {
        List<QuestionChoiceDTO> choices = questionChoiceService.getChoicesByQuestionId(questionId);
        return ResponseEntity.ok(choices);
    }
    // @GetMapping("/validate")
    // public ResponseEntity<ChoiceValidationDTO> validateChoice(
    //     @RequestParam int questionId,
    //     @RequestParam int choiceId
    // ) {
    //     ChoiceValidationDTO result = questionChoiceService.validateChoice(questionId, choiceId);
    //     return ResponseEntity.ok(result);
    // }
}
