package com.crackit.crackit.repository;

import com.crackit.crackit.model.MockInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MockInterviewRepository extends JpaRepository<MockInterview, Integer> {
    
    List<MockInterview> findByRequesterId(int requesterId);
    List<MockInterview> findByInviteeId(int inviteeId);
}
