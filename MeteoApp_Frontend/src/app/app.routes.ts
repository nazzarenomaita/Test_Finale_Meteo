import { Routes } from '@angular/router';
import { NavbarComponent } from './nav/nav.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './signup/signup.component';

export const routes: Routes = 
[
    { path: "", component: NavbarComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  //{ path: "corsi", component: CorsiPageComponent },
  //{ path: "profilo", component: ProfiloComponent, canActivate: [AuthGuard] },
  //{ path: "modificaProfilo", component: UpdateProfileComponent, canActivate: [AuthGuard] }
];
