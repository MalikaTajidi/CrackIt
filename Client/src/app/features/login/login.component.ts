import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,
    ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
    http: any;
    loginForm!: FormGroup;
    showPassword = false;
  
    constructor(private authService: AuthService, private router: Router) {
     
    }
    ngOnInit(): void {
      this.loginForm = new FormGroup({
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', Validators.required),
        rememberMe: new FormControl(false) 
  
      });
    }
    email: string = '';
    password: string = '';
    errorMessage: string = '';
 
  
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    }
  
    onSubmit() {
      if (this.loginForm.valid) {
        const { email, password} = this.loginForm.value;
    
        this.authService.login(email, password).subscribe(
          (response: any) => {
            console.log('Connexion réussie', response);
    
            const token = response.accessToken; 
            const user = response.user; 
  
              localStorage.setItem('authToken', token); 
              localStorage.setItem('user', JSON.stringify(user));
              this.router.navigate(['/topics']);
  
          },
          (error: any) => {
            console.error('connexion error : ', error);
          }
        );
      } else {
        console.log('invalid Form');
      }
    }
}
