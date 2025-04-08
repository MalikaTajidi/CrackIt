import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm!: FormGroup;
  showPassword = false;

  constructor(private authService: AuthService, private router : Router) {
  
  }

  ngOnInit() {
    this.registerForm = new FormGroup(
      {
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required),
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', Validators.required),
  
      }
    );
    
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      console.log('Register data:', this.registerForm.value);

      const userData = {
        ...this.registerForm.value,
      };
      this.authService.registerUser(userData).subscribe(
        (response:any) => {
          console.log('Signup successful:', response);

          this.router.navigate(['/login']); 
        },
        (error) => {
          alert('error');
          console.error(error);
        }
      );
    }
    }
  }

