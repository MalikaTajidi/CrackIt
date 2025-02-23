package com.crackit.crackit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionText;
    private String correctAnswer;
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "subtopic_id", nullable = false)
    private Subtopic subtopic;

    @OneToMany(mappedBy = "question" , fetch = FetchType.EAGER//, cascade = CascadeType.ALL
    )
   // @JsonManagedReference
    private Set<QuestionChoice> choices = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<UserAnswer> userAnswers = new HashSet<>();
}
