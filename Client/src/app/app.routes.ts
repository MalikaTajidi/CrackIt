import { Routes } from '@angular/router';
import { LandingPageComponent } from './features/landing-page/landing-page.component';
import { LoginComponent } from './features/login/login.component';
import { RegisterComponent } from './features/register/register.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';

export const routes: Routes = [
    { path: '', component: LandingPageComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component:RegisterComponent},
    { path: 'navbar', component:NavbarComponent},
    { path: 'footer', component:FooterComponent},
];
