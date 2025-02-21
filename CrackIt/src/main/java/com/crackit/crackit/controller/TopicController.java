package com.crackit.crackit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crackit.crackit.dto.TopicDTO;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.model.Topic;
import com.crackit.crackit.service.ServiceInterfaces.TopicService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/topic")
public class TopicController {
    private final TopicService topicService;
    public TopicController(TopicService topicService){
        this.topicService = topicService;

    }
    @GetMapping
    public List<TopicDTO> getAllTopics(){
        return topicService.getAllTopics();
    }
    @GetMapping("/subtopics")
    public List<Subtopic> getSubTopicsByTopicId(@RequestParam int topicId) {
        return topicService.getAllSubTopics(topicId);
    }
    
    
}
