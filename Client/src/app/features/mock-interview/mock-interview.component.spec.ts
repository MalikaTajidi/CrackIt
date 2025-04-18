import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockInterviewComponent } from './mock-interview.component';

describe('MockInterviewComponent', () => {
  let component: MockInterviewComponent;
  let fixture: ComponentFixture<MockInterviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MockInterviewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MockInterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
