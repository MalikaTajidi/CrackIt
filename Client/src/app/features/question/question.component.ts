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
  question: string = "What is the capital of France?";
  options: string[] = ["Berlin", "Madrid", "Paris", "Rome"];
  selectedOption: string | null = null;

  selectOption(option: string) {
    this.selectedOption = option;
  }
}
