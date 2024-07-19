import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../service/auth-service.service';
import { LoginDto } from '../model/LoginDto';
import { StorageService } from '../service/storage.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  logError:boolean=false

  constructor(private fb: FormBuilder, private authService: AuthService, private storageService: StorageService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$")]]
    });
  }

  onLogin() {
    if (this.loginForm.valid) {
      this.authService.signin(new LoginDto(this.loginForm.value.email, this.loginForm.value.password)).subscribe((res: any) => {
        this.storageService.setLocalToken(res.token)
        this.storageService.setProperty('user_email', (this.loginForm.value.email));
        this.router.navigate(["/profilo"]);
      }, (error: HttpErrorResponse) => {
        this.logError = true;
      })
    }
  }
}