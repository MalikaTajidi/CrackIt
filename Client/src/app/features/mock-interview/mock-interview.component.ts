import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MockRequestsComponent } from '../mock-requests/mock-requests.component';
import { MockInvitationsComponent } from '../mock-invitations/mock-invitations.component';
import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-mock-interview',
  standalone: true,
  imports: [CommonModule,MockRequestsComponent,MockInvitationsComponent,NavbarComponent,FooterComponent],
  templateUrl: './mock-interview.component.html',
  styleUrl: './mock-interview.component.css'
})
export class MockInterviewComponent {


  currentTab: string = 'received';  // Default tab is "received"

  // Method to switch between tabs
  switchTab(tab: string) {
    this.currentTab = tab;
  }
}
