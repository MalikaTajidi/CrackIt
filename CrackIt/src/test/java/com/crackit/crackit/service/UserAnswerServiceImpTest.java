package com.crackit.crackit.service;

import com.crackit.crackit.dto.AnswerResponse;
import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.QuestionChoice;
import com.crackit.crackit.model.User;
import com.crackit.crackit.model.UserAnswer;
import com.crackit.crackit.repository.QuestionChoiceRepository;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.repository.UserAnswerRepository;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceImp.UserAnswerServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAnswerServiceImpTest {

    @Mock
    private UserAnswerRepository userAnswerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionChoiceRepository questionChoiceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAnswerServiceImp userAnswerService;

    private User user;
    private Question question;
    private QuestionChoice correctChoice;
    private QuestionChoice wrongChoice;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");

        question = new Question();
        question.setId(1);
        question.setCorrectAnswer("Correct Answer");
        question.setExplanation("Explanation for the correct answer");

        correctChoice = new QuestionChoice();
        correctChoice.setId(100);
        correctChoice.setCorrect(true);

        wrongChoice = new QuestionChoice();
        wrongChoice.setId(200);
        wrongChoice.setCorrect(false);
    }

    @Test
    void testSubmitAnswer_CorrectChoice() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));
        when(questionChoiceRepository.findById(100)).thenReturn(Optional.of(correctChoice));

        // Act
        AnswerResponse response = userAnswerService.submitAnswer(1, 1, 100);

        // Assert
        assertTrue(response.isCorrect());
        assertEquals("Correct Answer", response.getCorrectAnswer());
        assertEquals("Explanation for the correct answer", response.getExplanation());

        verify(userAnswerRepository, times(1)).save(any(UserAnswer.class));
    }

    @Test
    void testSubmitAnswer_WrongChoice() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));
        when(questionChoiceRepository.findById(200)).thenReturn(Optional.of(wrongChoice));

        // Act
        AnswerResponse response = userAnswerService.submitAnswer(1, 1, 200);

        // Assert
        assertFalse(response.isCorrect());
        assertEquals("Correct Answer", response.getCorrectAnswer());
        assertEquals("Explanation for the correct answer", response.getExplanation());

        verify(userAnswerRepository, times(1)).save(any(UserAnswer.class));
    }

    @Test
    void testSubmitAnswer_UserNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userAnswerService.submitAnswer(1, 1, 100));
        assertEquals("User not found", exception.getMessage());

        verify(userAnswerRepository, never()).save(any(UserAnswer.class));
    }

    @Test
    void testSubmitAnswer_QuestionNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userAnswerService.submitAnswer(1, 1, 100));
        assertEquals("Question not found", exception.getMessage());

        verify(userAnswerRepository, never()).save(any(UserAnswer.class));
    }

    @Test
    void testSubmitAnswer_ChoiceNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));
        when(questionChoiceRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userAnswerService.submitAnswer(1, 1, 999));
        assertEquals("Choice not found", exception.getMessage());

        verify(userAnswerRepository, never()).save(any(UserAnswer.class));
    }
}
