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
