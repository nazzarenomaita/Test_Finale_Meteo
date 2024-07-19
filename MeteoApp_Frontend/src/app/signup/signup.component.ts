import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { catchError, map, of } from 'rxjs';
import { AuthService } from '../service/auth-service.service';
import { LoginDto } from '../model/LoginDto';
import { UtenteRegistrationDto } from '../model/UtenteRegistrationDto';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    RouterLink
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class RegisterComponent implements OnInit{
  signupForm: FormGroup;
  alreadyRegistered: boolean = false;
  alreadyLogged: boolean = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {

    this.signupForm = this.fb.group({
      name: ['', [Validators.required]],
      cognome: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$")]]
    });
  }
  ngOnInit(): void {

    this.authService.isAuthenticated().subscribe({
      error: (error: HttpErrorResponse) => { this.alreadyLogged = false; },
      next: (loggedIn: boolean) => { this.alreadyLogged = loggedIn; },
    });
  }
  onSignup() {
    if (this.signupForm.valid) {
      this.authService.signup(new UtenteRegistrationDto(this.signupForm.value.name, this.signupForm.value.cognome, this.signupForm.value.email, this.signupForm.value.password)).subscribe(() => {
        this.authService.signin(new LoginDto(this.signupForm.value.email, this.signupForm.value.password)).subscribe((res) => {
          this.router.navigate(["/profilo"]);
        });
      }, (err: HttpErrorResponse) => {
          this.alreadyRegistered = true;
      })
    }
  }

}