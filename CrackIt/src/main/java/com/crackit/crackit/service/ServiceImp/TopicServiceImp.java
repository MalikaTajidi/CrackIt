package com.crackit.crackit.service.ServiceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crackit.crackit.model.Topic;
import com.crackit.crackit.repository.TopicRepository;
import com.crackit.crackit.service.ServiceInterfaces.TopicService;
@Service
public class TopicServiceImp implements TopicService{
    private final TopicRepository topicRepository;

    public TopicServiceImp(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }
    @Override
    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }
    
}
