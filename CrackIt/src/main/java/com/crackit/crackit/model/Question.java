package com.crackit.crackit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "id", nullable = false)
    private Subtopic subtopic;

    @OneToMany(mappedBy = "question")
    private Set<QuestionChoice> choices = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<UserAnswer> userAnswers = new HashSet<>();
}
