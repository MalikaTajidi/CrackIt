package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.Enum.InterviewStatus;
import com.crackit.crackit.dto.MockInterviewRequestDTO;
import com.crackit.crackit.dto.MockInterviewResponseDTO;

public interface MockInterviewService {
    MockInterviewResponseDTO sendInterviewRequest(int requesterId, MockInterviewRequestDTO requestDTO);
    List<MockInterviewResponseDTO> getUserRequests(int userId);
    List<MockInterviewResponseDTO> getUserInvitations(int userId);
    void updateInterviewStatus(int interviewId, InterviewStatus status);
}
