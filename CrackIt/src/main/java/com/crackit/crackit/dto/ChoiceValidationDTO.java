package com.crackit.crackit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceValidationDTO {
    private int questionId;
    private boolean isCorrect;
    private String explanation;
}
