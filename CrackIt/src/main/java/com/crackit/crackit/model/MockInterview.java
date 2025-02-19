package com.crackit.crackit.model;

import com.crackit.crackit.Enum.InterviewStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Users requester;

    @ManyToOne
    @JoinColumn(name = "invitee_id")
    private Users invitee;

    private LocalDateTime scheduledTime;

   @Enumerated(EnumType.STRING)
   private InterviewStatus status;


}
