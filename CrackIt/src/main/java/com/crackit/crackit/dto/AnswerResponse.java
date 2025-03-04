package com.crackit.crackit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private boolean isCorrect;
    private String correctAnswer;
    private String explanation;
}
