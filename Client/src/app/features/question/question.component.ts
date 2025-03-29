import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { FooterComponent } from "../../shared/footer/footer.component";

@Component({
  selector: 'app-question',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './question.component.html',
  styleUrl: './question.component.css'
})
export class QuestionComponent {
  currentIndex: number = 0;
  questions: { question: string, options: string[] }[] = [
    { question: "What is the capital of France?", options: ["Berlin", "Madrid", "Paris", "Rome"] },
    { question: "What is 2 + 2?", options: ["3", "4", "5", "6"] },
    { question: "What is the largest planet?", options: ["Earth", "Mars", "Jupiter", "Saturn"] }
  ];

  selectedOption: string | null = null;

  get question() {
    return this.questions[this.currentIndex].question;
  }

  get options() {
    return this.questions[this.currentIndex].options;
  }

  selectOption(option: string) {
    this.selectedOption = option;
  }

  goToPrevious() {
    if (this.currentIndex > 0) {
      this.currentIndex--;
      this.selectedOption = null; // Reset selected option when going back
    }
  }

  goToNext() {
    if (this.currentIndex < this.questions.length - 1) {
      this.currentIndex++;
      this.selectedOption = null; // Reset selected option when moving forward
    }
  }
}
