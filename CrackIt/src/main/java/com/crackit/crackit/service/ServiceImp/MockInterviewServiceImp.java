package com.crackit.crackit.service.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crackit.crackit.Enum.InterviewStatus;
import com.crackit.crackit.dto.MockInterviewRequestDTO;
import com.crackit.crackit.dto.MockInterviewResponseDTO;
import com.crackit.crackit.model.MockInterview;
import com.crackit.crackit.model.User;
import com.crackit.crackit.repository.MockInterviewRepository;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceInterfaces.MockInterviewService;
@Service
public class MockInterviewServiceImp implements MockInterviewService{

    private final MockInterviewRepository mockInterviewRepository;
    private final UserRepository userRepository;

    public MockInterviewServiceImp(MockInterviewRepository mockInterviewRepository, UserRepository userRepository) {
        this.mockInterviewRepository = mockInterviewRepository;
        this.userRepository = userRepository;
    }
        @Override
        public MockInterviewResponseDTO sendInterviewRequest(int requesterId, MockInterviewRequestDTO requestDTO) {
        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Requester not found"));
        User invitee = userRepository.findById(requestDTO.getInviteeId())
                .orElseThrow(() -> new RuntimeException("Invitee not found"));

        MockInterview interview = new MockInterview();
        interview.setRequester(requester);
        interview.setInvitee(invitee);
        interview.setScheduledTime(requestDTO.getScheduledTime());
        interview.setStatus(InterviewStatus.PENDING);

        MockInterview savedInterview = mockInterviewRepository.save(interview);

        return new MockInterviewResponseDTO(
                savedInterview.getId(),
                savedInterview.getRequester().getFirstName() + " " + savedInterview.getRequester().getLastName(),
                savedInterview.getInvitee().getFirstName() + " " + savedInterview.getInvitee().getLastName(),
                savedInterview.getScheduledTime(),
                savedInterview.getStatus()
        );
    }
    @Override
    public List<MockInterviewResponseDTO> getUserRequests(int userId) {
        List<MockInterview> interviews = mockInterviewRepository.findByRequesterId(userId);

        return interviews.stream()
                .map(interview -> new MockInterviewResponseDTO(
                        interview.getId(),
                        interview.getRequester().getFirstName() + " " + interview.getRequester().getLastName(),
                        interview.getInvitee().getFirstName() + " " + interview.getInvitee().getLastName(),
                        interview.getScheduledTime(),
                        interview.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<MockInterviewResponseDTO> getUserInvitations(int userId) {
        List<MockInterview> invitations = mockInterviewRepository.findByInviteeId(userId);

        return invitations.stream()
                .map(interview -> new MockInterviewResponseDTO(
                        interview.getId(),
                        interview.getRequester().getFirstName() + " " + interview.getRequester().getLastName(),
                        interview.getInvitee().getFirstName() + " " + interview.getInvitee().getLastName(),
                        interview.getScheduledTime(),
                        interview.getStatus()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public void updateInterviewStatus(int interviewId, InterviewStatus status) {
        MockInterview interview = mockInterviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        interview.setStatus(status);
        mockInterviewRepository.save(interview);
    }
    
}
