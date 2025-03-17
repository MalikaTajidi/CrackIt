import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubTopicFilterComponent } from './sub-topic-filter.component';

describe('SubTopicFilterComponent', () => {
  let component: SubTopicFilterComponent;
  let fixture: ComponentFixture<SubTopicFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubTopicFilterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubTopicFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
