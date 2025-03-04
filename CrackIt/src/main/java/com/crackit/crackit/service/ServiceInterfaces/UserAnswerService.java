package com.crackit.crackit.service.ServiceInterfaces;



import com.crackit.crackit.dto.AnswerResponse;


public interface UserAnswerService {
    AnswerResponse submitAnswer(int userId, int questionId, int choiceId);
    
}
