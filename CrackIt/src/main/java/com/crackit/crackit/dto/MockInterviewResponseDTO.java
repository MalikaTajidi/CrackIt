package com.crackit.crackit.dto;

import java.time.LocalDateTime;

import com.crackit.crackit.Enum.InterviewStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockInterviewResponseDTO {
    private int id;
    private String requesterName;
    private String inviteeName;
    private LocalDateTime scheduledTime;
    private InterviewStatus status;
}
