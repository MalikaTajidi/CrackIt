import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionchoiceComponent } from './questionchoice.component';

describe('QuestionchoiceComponent', () => {
  let component: QuestionchoiceComponent;
  let fixture: ComponentFixture<QuestionchoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QuestionchoiceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuestionchoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
