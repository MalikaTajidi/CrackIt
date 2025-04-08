import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { CommonModule } from '@angular/common';
import { TopicService } from '../../services/TopicService/topic.service';

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [NavbarComponent, FooterComponent,CommonModule],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.css'
})
export class TopicComponent {
 // Dummy data for topics
//  topics = [
//   { id: 1, title: 'Mathematics', description: 'Explore the world of numbers, shapes, and patterns.' },
//   { id: 2, title: 'Science', description: 'Dive into the mysteries of the natural world and scientific discoveries.' },
//   { id: 3, title: 'History', description: 'Uncover the past events that shaped the modern world.' },
//   { id: 4, title: 'Literature', description: 'Discover the beauty of written works and storytelling from various cultures.' },
//   { id: 5, title: 'Mathematics', description: 'Learn advanced concepts and techniques in the realm of mathematics.' },
//   { id: 6, title: 'Science', description: 'Study the fundamental principles and laws governing the physical world.' },
//   { id: 7, title: 'History', description: 'Explore ancient civilizations and their impact on modern society.' },
//   { id: 8, title: 'Literature', description: 'Analyze classic and contemporary literary works and their societal influences.' }
// ];

topics: any;

constructor(private topicService : TopicService, private router: Router) {}

ngOnInit(): void {
  this.topicService.getAllTopics().subscribe({
    next: (data) => {
      this.topics = data;
      console.log('Topics:', this.topics);
    },
    error: (err) => {
      console.error('Error fetching topics', err);
    }
  });
}

onTopicSelect(topicId: number) {
  this.router.navigate(['/questions', topicId]); 
}
}
