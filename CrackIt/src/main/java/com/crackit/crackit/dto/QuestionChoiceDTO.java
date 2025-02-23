package com.crackit.crackit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionChoiceDTO {
    private int id;
    private String choiceText;
}
