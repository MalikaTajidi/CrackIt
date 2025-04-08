package com.crackit.crackit.service.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crackit.crackit.dto.TopicDTO;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.model.Topic;
import com.crackit.crackit.repository.SubtopicRepository;
import com.crackit.crackit.repository.TopicRepository;
import com.crackit.crackit.service.ServiceInterfaces.TopicService;
@Service
public class TopicServiceImp implements TopicService{
    private final TopicRepository topicRepository;
    private final SubtopicRepository subtopicRepository;

    public TopicServiceImp(TopicRepository topicRepository,SubtopicRepository subtopicRepository){
        this.topicRepository = topicRepository;
        this.subtopicRepository = subtopicRepository;
    }
    @Override
    public List<TopicDTO> getAllTopics(){
        List<Topic> listTopics = topicRepository.findAll();
        List<TopicDTO> list = new ArrayList<>();
        for(Topic t : listTopics){
            TopicDTO topic = new TopicDTO(t.getId(),t.getTitle(),t.getDescription());
            list.add(topic);
          }
          return list;
    }

    @Override
    public List<Subtopic> getAllSubTopics(int topicId){
       //Topic topic = topicRepository.findById(topicId);
       List<Subtopic> list = subtopicRepository.findAll();
       List<Subtopic> subs = new ArrayList<>();
       for(Subtopic l : list){
        if(l.getTopic().getId()==topicId){
          subs.add(l);
        }
       }
       return subs; 

    }
    
}
