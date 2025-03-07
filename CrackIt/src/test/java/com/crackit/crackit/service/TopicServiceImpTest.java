package com.crackit.crackit.service;

import com.crackit.crackit.dto.TopicDTO;
import com.crackit.crackit.model.Subtopic;
import com.crackit.crackit.model.Topic;
import com.crackit.crackit.repository.SubtopicRepository;
import com.crackit.crackit.repository.TopicRepository;
import com.crackit.crackit.service.ServiceImp.TopicServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceImpTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private SubtopicRepository subtopicRepository;

    @InjectMocks
    private TopicServiceImp topicService;

    private Topic topic1;
    private Topic topic2;
    private Subtopic subtopic1;
    private Subtopic subtopic2;

    @BeforeEach
    void setUp() {
        topic1 = new Topic();
        topic1.setId(1);
        topic1.setTitle("Java Programming");

        topic2 = new Topic();
        topic2.setId(2);
        topic2.setTitle("Spring Boot");

        subtopic1 = new Subtopic();
        subtopic1.setId(101);
        subtopic1.setSubtopicName("OOP in Java");
        subtopic1.setTopic(topic1);

        subtopic2 = new Subtopic();
        subtopic2.setId(102);
        subtopic2.setSubtopicName("REST APIs with Spring Boot");
        subtopic2.setTopic(topic2);
    }

    @Test
    void testGetAllTopics() {
        // Arrange
        when(topicRepository.findAll()).thenReturn(Arrays.asList(topic1, topic2));

        // Act
        List<TopicDTO> topics = topicService.getAllTopics();

        // Assert
        assertEquals(2, topics.size());
        assertEquals("Java Programming", topics.get(0).getTitle());
        assertEquals("Spring Boot", topics.get(1).getTitle());
        verify(topicRepository, times(1)).findAll();
    }

    @Test
    void testGetAllTopics_EmptyList() {
        // Arrange
        when(topicRepository.findAll()).thenReturn(List.of());

        // Act
        List<TopicDTO> topics = topicService.getAllTopics();

        // Assert
        assertTrue(topics.isEmpty());
        verify(topicRepository, times(1)).findAll();
    }

    @Test
    void testGetAllSubTopics() {
        // Arrange
        when(subtopicRepository.findAll()).thenReturn(Arrays.asList(subtopic1, subtopic2));

        // Act
        List<Subtopic> subtopics = topicService.getAllSubTopics(1);

        // Assert
        assertEquals(1, subtopics.size());
        assertEquals("OOP in Java", subtopics.get(0).getSubtopicName());
        verify(subtopicRepository, times(1)).findAll();
    }

    @Test
    void testGetAllSubTopics_NoMatch() {
        // Arrange
        when(subtopicRepository.findAll()).thenReturn(Arrays.asList(subtopic1, subtopic2));

        // Act
        List<Subtopic> subtopics = topicService.getAllSubTopics(999);

        // Assert
        assertTrue(subtopics.isEmpty());
        verify(subtopicRepository, times(1)).findAll();
    }
}
