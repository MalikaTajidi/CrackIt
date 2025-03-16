import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  query: string = '';  // for search query
  filteredUsers: any[] = [];  // filtered users based on search query
  isMenu: boolean = false;  // to toggle user menu visibility
  isInvit: boolean = false;  // to toggle notifications visibility
  isMessagerie: boolean = false;  // to toggle messaging visibility
  userAuth = { id: 1, firstname: 'John', lastname: 'Doe', image: 'profile.png' };  // mock authenticated user
  selectedUser: any = null;  // selected user for messaging

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Initialize the lists (e.g., fetch users, messages, notifications from backend)
    this.fetchUsers();
    
  }

  // Search users based on input query
  search(): void {
    // Mock search logic: In a real scenario, you would call an API to get matching users
    if (this.query) {
      this.filteredUsers = this.filteredUsers.filter(user => 
        user.firstname.toLowerCase().includes(this.query.toLowerCase()) ||
        user.lastname.toLowerCase().includes(this.query.toLowerCase())
      );
    } else {
      this.filteredUsers = [];
    }
  }

  // Handle user profile navigation
  navigateToProfile(user: any): void {
    // Navigate to the profile of the selected user
    this.router.navigate(['/profile', user.id]);
  }

  // Toggle notifications dropdown visibility
  toggleInvit(): void {
    this.isInvit = !this.isInvit;
  }

 
  // Toggle user menu visibility
  toggleMenu(): void {
    this.isMenu = !this.isMenu;
  }

  // Navigate to authenticated user's profile
  NavigateToMyprofile(): void {
    this.router.navigate(['/profile', this.userAuth.id]);
  }

  

  // Select user for messaging
  onSelectUser(user: any): void {
    this.selectedUser = user;
    // Implement logic to open the messaging window with the selected user
  }

  // Mock methods to fetch users, messages, and notifications (replace with real API calls)
  fetchUsers(): void {
    this.filteredUsers = [
      { id: 1, firstname: 'John', lastname: 'Doe', image: 'profile1.jpg' },
      { id: 2, firstname: 'Jane', lastname: 'Smith', image: 'profile2.jpg' },
      { id: 3, firstname: 'Bob', lastname: 'Johnson', image: 'profile3.jpg' }
    ];
  }

 
}
