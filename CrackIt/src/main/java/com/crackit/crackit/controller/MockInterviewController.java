package com.crackit.crackit.controller;

import com.crackit.crackit.Enum.InterviewStatus;
import com.crackit.crackit.dto.MockInterviewRequestDTO;
import com.crackit.crackit.dto.MockInterviewResponseDTO;
import com.crackit.crackit.service.ServiceInterfaces.MockInterviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mock-interviews")
public class MockInterviewController {

    private final MockInterviewService mockInterviewService;

    public MockInterviewController(MockInterviewService mockInterviewService) {
        this.mockInterviewService = mockInterviewService;
    }

    @PostMapping("/{requesterId}/send")
    public ResponseEntity<MockInterviewResponseDTO> sendInterviewRequest(
            @PathVariable int requesterId,
            @RequestBody MockInterviewRequestDTO requestDTO) {
        return ResponseEntity.ok(mockInterviewService.sendInterviewRequest(requesterId, requestDTO));
    }

    @GetMapping("/{userId}/requests")
    public ResponseEntity<List<MockInterviewResponseDTO>> getUserRequests(@PathVariable int userId) {
        return ResponseEntity.ok(mockInterviewService.getUserRequests(userId));
    }

    @GetMapping("/{userId}/invitations")
    public ResponseEntity<List<MockInterviewResponseDTO>> getUserInvitations(@PathVariable int userId) {
        return ResponseEntity.ok(mockInterviewService.getUserInvitations(userId));
    }

    @PatchMapping("/{interviewId}/status")
    public ResponseEntity<String> updateInterviewStatus(
            @PathVariable int interviewId,
            @RequestParam InterviewStatus status) {
        mockInterviewService.updateInterviewStatus(interviewId, status);
        return ResponseEntity.ok("Interview status updated successfully.");
 }
}
