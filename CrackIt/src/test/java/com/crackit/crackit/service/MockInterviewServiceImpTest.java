package com.crackit.crackit.service;

import com.crackit.crackit.Enum.InterviewStatus;
import com.crackit.crackit.dto.MockInterviewRequestDTO;
import com.crackit.crackit.dto.MockInterviewResponseDTO;
import com.crackit.crackit.model.MockInterview;
import com.crackit.crackit.model.User;
import com.crackit.crackit.repository.MockInterviewRepository;
import com.crackit.crackit.repository.UserRepository;
import com.crackit.crackit.service.ServiceImp.MockInterviewServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockInterviewServiceImpTest {

    @Mock
    private MockInterviewRepository mockInterviewRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MockInterviewServiceImp mockInterviewService;

    private User requester;
    private User invitee;
    private MockInterview mockInterview;
    private MockInterviewRequestDTO requestDTO;
    private LocalDateTime scheduledTime;

    @BeforeEach
    void setUp() {
        requester = new User();
        requester.setId(1);
        requester.setFirstName("John");
        requester.setLastName("Doe");

        invitee = new User();
        invitee.setId(2);
        invitee.setFirstName("Jane");
        invitee.setLastName("Smith");

        scheduledTime = LocalDateTime.of(2025, 3, 10, 14, 0);

        mockInterview = new MockInterview();
        mockInterview.setId(1);
        mockInterview.setRequester(requester);
        mockInterview.setInvitee(invitee);
        mockInterview.setScheduledTime(scheduledTime);
        mockInterview.setStatus(InterviewStatus.PENDING);

        requestDTO = new MockInterviewRequestDTO();
        requestDTO.setInviteeId(2);
        requestDTO.setScheduledTime(scheduledTime);
    }

    @Test
    void testSendInterviewRequest_Success() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(requester));
        when(userRepository.findById(2)).thenReturn(Optional.of(invitee));
        when(mockInterviewRepository.save(any(MockInterview.class))).thenReturn(mockInterview);

        // Act
        MockInterviewResponseDTO response = mockInterviewService.sendInterviewRequest(1, requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("John Doe", response.getRequesterName());
        assertEquals("Jane Smith", response.getInviteeName());
        assertEquals(InterviewStatus.PENDING, response.getStatus());
        assertEquals(scheduledTime, response.getScheduledTime());

        verify(mockInterviewRepository, times(1)).save(any(MockInterview.class));
    }

    @Test
    void testSendInterviewRequest_RequesterNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockInterviewService.sendInterviewRequest(1, requestDTO);
        });

        assertEquals("Requester not found", exception.getMessage());
        verify(mockInterviewRepository, never()).save(any(MockInterview.class));
    }

    @Test
    void testSendInterviewRequest_InviteeNotFound() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(requester));
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockInterviewService.sendInterviewRequest(1, requestDTO);
        });

        assertEquals("Invitee not found", exception.getMessage());
        verify(mockInterviewRepository, never()).save(any(MockInterview.class));
    }

    @Test
    void testGetUserRequests_ReturnsList() {
        // Arrange
        List<MockInterview> interviews = Arrays.asList(mockInterview);
        when(mockInterviewRepository.findByRequesterId(1)).thenReturn(interviews);

        // Act
        List<MockInterviewResponseDTO> result = mockInterviewService.getUserRequests(1);

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getRequesterName());
        assertEquals("Jane Smith", result.get(0).getInviteeName());

        verify(mockInterviewRepository, times(1)).findByRequesterId(1);
    }

    @Test
    void testGetUserInvitations_ReturnsList() {
        // Arrange
        List<MockInterview> invitations = Arrays.asList(mockInterview);
        when(mockInterviewRepository.findByInviteeId(2)).thenReturn(invitations);

        // Act
        List<MockInterviewResponseDTO> result = mockInterviewService.getUserInvitations(2);

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getRequesterName());
        assertEquals("Jane Smith", result.get(0).getInviteeName());

        verify(mockInterviewRepository, times(1)).findByInviteeId(2);
    }

    @Test
    void testUpdateInterviewStatus_Success() {
        // Arrange
        when(mockInterviewRepository.findById(1)).thenReturn(Optional.of(mockInterview));

        // Act
        mockInterviewService.updateInterviewStatus(1, InterviewStatus.ACCEPTED);

        // Assert
        assertEquals(InterviewStatus.ACCEPTED, mockInterview.getStatus());
        verify(mockInterviewRepository, times(1)).save(mockInterview);
    }

    @Test
    void testUpdateInterviewStatus_InterviewNotFound() {
        // Arrange
        when(mockInterviewRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockInterviewService.updateInterviewStatus(1, InterviewStatus.ACCEPTED);
        });

        assertEquals("Interview not found", exception.getMessage());
        verify(mockInterviewRepository, never()).save(any(MockInterview.class));
    }
}
