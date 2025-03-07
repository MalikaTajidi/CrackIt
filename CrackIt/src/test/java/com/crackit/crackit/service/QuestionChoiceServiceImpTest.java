package com.crackit.crackit.service;

import com.crackit.crackit.dto.ChoiceValidationDTO;
import com.crackit.crackit.dto.QuestionChoiceDTO;
import com.crackit.crackit.model.Question;
import com.crackit.crackit.model.QuestionChoice;
import com.crackit.crackit.repository.QuestionChoiceRepository;
import com.crackit.crackit.service.ServiceImp.QuestionChoiceServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionChoiceServiceImpTest {

    @Mock
    private QuestionChoiceRepository questionChoiceRepository;

    @InjectMocks
    private QuestionChoiceServiceImp questionChoiceService;

    private QuestionChoice choice1;
    private QuestionChoice choice2;
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(1);
        question.setExplanation("Explanation about the correct choice.");

        choice1 = new QuestionChoice(1, "Choice A", false, question);
        choice2 = new QuestionChoice(2, "Choice B", true, question);
    }

    @Test
    void testGetChoicesByQuestionId_ReturnsListOfChoices() {
        // Arrange
        int questionId = 1;
        List<QuestionChoice> choices = Arrays.asList(choice1, choice2);
        when(questionChoiceRepository.findByQuestionId(questionId)).thenReturn(choices);

        // Act
        List<QuestionChoiceDTO> result = questionChoiceService.getChoicesByQuestionId(questionId);

        // Assert
        assertEquals(2, result.size());
        assertEquals(choice1.getChoiceText(), result.get(0).getChoiceText());
        assertEquals(choice2.getChoiceText(), result.get(1).getChoiceText());

        verify(questionChoiceRepository, times(1)).findByQuestionId(questionId);
    }

    


}
