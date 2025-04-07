import { TestBed } from '@angular/core/testing';

import { QuestionChoiceService } from './question-choice.service';

describe('QuestionChoiceService', () => {
  let service: QuestionChoiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionChoiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
