import { Routes } from '@angular/router';
import { LandingPageComponent } from './features/landing-page/landing-page.component';
import { LoginComponent } from './features/login/login.component';
import { RegisterComponent } from './features/register/register.component';
import { TopicComponent } from './features/topic/topic.component';

export const routes: Routes = [
    { path: '', component: LandingPageComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component:RegisterComponent},
    {path: 'topics',component:TopicComponent},
];
