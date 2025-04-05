import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockRequestsComponent } from './mock-requests.component';

describe('MockRequestsComponent', () => {
  let component: MockRequestsComponent;
  let fixture: ComponentFixture<MockRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MockRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MockRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
