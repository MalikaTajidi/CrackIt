import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { FooterComponent } from "../../shared/footer/footer.component";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profile = {
    firstName: 'Malika',
    lastName: 'Tajidi',
    email: 'tajidimalika@gmail.com',
    bio: `Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
          Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.`,
    avatar: 'default-profile.png',
    headerImage: 'https://images.unsplash.com/photo-1605379399642-870262d3d051?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2000&q=80'
  };
}
