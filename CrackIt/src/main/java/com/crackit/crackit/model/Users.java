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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String FirstName;
    private String LastName;
    @Column(nullable = false,unique=true)
    private String Email;
    @Column(nullable = false)
    private String Password;
    private String profilePicture;

    @OneToMany(mappedBy = "user")
    private Set<UserAnswer> userAnswers = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Progress> progress = new HashSet<>();
}
