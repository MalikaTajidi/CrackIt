import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-sub-topic-filter',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sub-topic-filter.component.html',
  styleUrl: './sub-topic-filter.component.css'
})
export class SubTopicFilterComponent implements OnInit {
  @Input() topicId!: number;
  @Output() subtopicSelected = new EventEmitter<number | null>();

  subtopics: any[] = [];

  ngOnInit() {
    // Replace with actual service
    this.subtopics = [
      { id: 1, name: 'Algebra' },
      { id: 2, name: 'Geometry' },
      { id: 3, name: 'Algebra' },
      { id: 4, name: 'Geometry' },
      { id: 5, name: 'Algebra' },
      { id: 6, name: 'Geometry' },
      { id: 7, name: 'Algebra' },
      { id: 8, name: 'Geometry' }
    ];
  }

  selectSubtopic(id: number | null) {
    this.subtopicSelected.emit(id);
  }
}
