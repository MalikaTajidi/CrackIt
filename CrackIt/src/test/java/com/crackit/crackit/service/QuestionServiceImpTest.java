package com.crackit.crackit.service;

import com.crackit.crackit.dto.QuestionDTO;
import com.crackit.crackit.repository.QuestionRepository;
import com.crackit.crackit.service.ServiceImp.QuestionServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImpTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImp questionService;

    private QuestionDTO question1;
    private QuestionDTO question2;

    @BeforeEach
    void setUp() {
        question1 = new QuestionDTO(1, "What is Java?");
        question2 = new QuestionDTO(2, "What is Spring Boot?");
    }

    @Test
    void testGetQuestionsBySubtopic_ReturnsListOfQuestions() {
        // Arrange
        int subTopicId = 10;
        List<QuestionDTO> expectedQuestions = Arrays.asList(question1, question2);
        when(questionRepository.findQuestionsBySubtopicId(subTopicId)).thenReturn(expectedQuestions);

        // Act
        List<QuestionDTO> actualQuestions = questionService.getQuestionsBySubtopic(subTopicId);

        // Assert
        assertEquals(expectedQuestions.size(), actualQuestions.size());
        assertEquals(expectedQuestions, actualQuestions);

        verify(questionRepository, times(1)).findQuestionsBySubtopicId(subTopicId);
    }

    @Test
    void testGetQuestionsBySubtopic_EmptyList() {
        // Arrange
        int subTopicId = 20;
        when(questionRepository.findQuestionsBySubtopicId(subTopicId)).thenReturn(List.of());

        // Act
        List<QuestionDTO> actualQuestions = questionService.getQuestionsBySubtopic(subTopicId);

        // Assert
        assertEquals(0, actualQuestions.size());
        verify(questionRepository, times(1)).findQuestionsBySubtopicId(subTopicId);
    }
}
