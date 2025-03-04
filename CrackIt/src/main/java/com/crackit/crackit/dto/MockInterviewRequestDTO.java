package com.crackit.crackit.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockInterviewRequestDTO {
    private int inviteeId;
    private LocalDateTime scheduledTime;
}
