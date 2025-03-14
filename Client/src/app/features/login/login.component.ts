import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,
    ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
 
    loginForm: FormGroup;
    showPassword = false;
  
    constructor(private fb: FormBuilder) {
      this.loginForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required],
        rememberMe: [false],
      });
    }
  
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    }
  
    onSubmit() {
      if (this.loginForm.valid) {
        const loginData = this.loginForm.value;
        console.log('Login request:', loginData);
        // backend auth service
      }
    }
}
