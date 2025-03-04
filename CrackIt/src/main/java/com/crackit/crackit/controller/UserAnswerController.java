package com.crackit.crackit.controller;

import com.crackit.crackit.dto.AnswerResponse;
import com.crackit.crackit.service.ServiceInterfaces.UserAnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-answers")
public class UserAnswerController {
    
    @Autowired
    private UserAnswerService userAnswerService;

    @PostMapping("/submit")
    public ResponseEntity<AnswerResponse> submitAnswer(
            @RequestParam int userId,
            @RequestParam int questionId,
            @RequestParam int selectedChoiceId) {
        
        AnswerResponse response = userAnswerService.submitAnswer(userId, questionId, selectedChoiceId);
        return ResponseEntity.ok(response);
    }
}
