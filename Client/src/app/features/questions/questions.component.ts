import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SubTopicFilterComponent } from "../sub-topic-filter/sub-topic-filter.component";
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { FooterComponent } from "../../shared/footer/footer.component";

@Component({
  selector: 'app-questions',
  standalone: true,
  imports: [CommonModule, SubTopicFilterComponent, NavbarComponent, FooterComponent],
  templateUrl: './questions.component.html',
  styleUrl: './questions.component.css'
})
export class QuestionsComponent {
  topicId!: number;
  questions: any[] = [];
  subtopicId: number | null = null;

  ngOnInit() {
    this.topicId = Number(this.route.snapshot.paramMap.get('topicId'));
    this.loadQuestions(); 
  }

  constructor(private route: ActivatedRoute) {}

  loadQuestions() {
   
    this.questions = [
      { id: 1, content: 'question1', subtopicId: 1 },
      { id: 2, content: 'question2', subtopicId: 2 },
      { id: 3, content: 'question3', subtopicId: 1 },
      { id: 4, content: 'question4', subtopicId: 2 },
      { id: 5, content: 'question5', subtopicId: 1 }
    ].filter(q => !this.subtopicId || q.subtopicId === this.subtopicId);
  }

  onSubtopicSelected(subId: number | null) {
    this.subtopicId = subId;
    this.loadQuestions();
  }
}
