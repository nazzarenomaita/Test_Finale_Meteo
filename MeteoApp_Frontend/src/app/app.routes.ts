import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './signup/signup.component';
import { ProfileComponent } from './profilo/profilo.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';

export const routes: Routes = 
[
    { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "profilo", component: ProfileComponent, canActivate: [AuthGuard] }
];
