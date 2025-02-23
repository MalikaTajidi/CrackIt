package com.crackit.crackit.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private int id;
    private String questionText;
   // private Set<QuestionChoiceDTO> choices;
    
}
