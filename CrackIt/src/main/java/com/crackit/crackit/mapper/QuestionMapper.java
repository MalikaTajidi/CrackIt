// package com.crackit.crackit.mapper;

// import java.util.Set;
// import java.util.stream.Collectors;

// import com.crackit.crackit.dto.QuestionChoiceDTO;
// import com.crackit.crackit.dto.QuestionDTO;
// import com.crackit.crackit.model.Question;
// import com.crackit.crackit.model.QuestionChoice;

// public class QuestionMapper {
//         public static QuestionDTO toDTO(Question question) {
//         Set<QuestionChoiceDTO> choiceDTOs = question.getChoices().stream()
//                 .map(QuestionMapper::mapChoiceToDTO)
//                 .collect(Collectors.toSet());

//         return new QuestionDTO(
//                 question.getId(),
//                 question.getQuestionText(),
//                 choiceDTOs
//         );
//     }

//     private static QuestionChoiceDTO mapChoiceToDTO(QuestionChoice choice) {
//         return new QuestionChoiceDTO(
//                 choice.getId(),
//                 choice.getChoiceText()
//         );
//     }
// }
