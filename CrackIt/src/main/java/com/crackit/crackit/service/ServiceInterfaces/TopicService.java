package com.crackit.crackit.service.ServiceInterfaces;

import java.util.List;

import com.crackit.crackit.dto.TopicDTO;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.model.Topic;

public interface TopicService {
    List<TopicDTO> getAllTopics();
    List<Subtopic> getAllSubTopics(int topicId);
    
}
