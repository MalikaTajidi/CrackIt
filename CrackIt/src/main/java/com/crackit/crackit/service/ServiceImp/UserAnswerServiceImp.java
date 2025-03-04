package com.crackit.crackit.service.ServiceImp;

import org.springframework.stereotype.Service;

import com.crackit.crackit.dto.AnswerResponse;
import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.QuestionChoice;
import com.crackit.crackit.model.User;
import com.crackit.crackit.model.UserAnswer;
import com.crackit.crackit.repository.QuestionChoiceRepository;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.repository.UserAnswerRepository;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceInterfaces.UserAnswerService;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class UserAnswerServiceImp implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionChoiceRepository questionChoiceRepository;
    private final UserRepository userRepository;

    public UserAnswerServiceImp(UserAnswerRepository userAnswerRepository, 
                                QuestionRepository questionRepository, 
                                QuestionChoiceRepository questionChoiceRepository, 
                                UserRepository userRepository) {
        this.userAnswerRepository = userAnswerRepository;
        this.questionRepository = questionRepository;
        this.questionChoiceRepository = questionChoiceRepository;
        this.userRepository = userRepository;
    }

        @Override
        public AnswerResponse submitAnswer(int userId, int questionId, int selectedChoiceId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
    
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));
    
            QuestionChoice selectedChoice = questionChoiceRepository.findById(selectedChoiceId)
                    .orElseThrow(() -> new RuntimeException("Choice not found"));
    
            boolean isCorrect = selectedChoice.isCorrect();
    
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setUser(user);
            userAnswer.setQuestion(question);
            userAnswer.setSelectedChoice(selectedChoice);
            userAnswer.setCorrect(isCorrect);
            userAnswerRepository.save(userAnswer);
    
            return new AnswerResponse(
                    isCorrect,
                    question.getCorrectAnswer(),
                    question.getExplanation()
            );
        }
       
    }

