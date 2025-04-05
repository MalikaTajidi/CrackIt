import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockInvitationsComponent } from './mock-invitations.component';

describe('MockInvitationsComponent', () => {
  let component: MockInvitationsComponent;
  let fixture: ComponentFixture<MockInvitationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MockInvitationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MockInvitationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
