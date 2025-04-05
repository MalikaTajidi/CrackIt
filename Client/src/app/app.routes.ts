import { Routes } from '@angular/router';
import { LandingPageComponent } from './features/landing-page/landing-page.component';
import { LoginComponent } from './features/login/login.component';
import { RegisterComponent } from './features/register/register.component';
import { TopicComponent } from './features/topic/topic.component';
import { QuestionsComponent } from './features/questions/questions.component';
import { QuestionComponent } from './features/question/question.component';
import { MockInterviewComponent } from './features/mock-interview/mock-interview.component';
import { ProfileComponent } from './features/profile/profile.component';


export const routes: Routes = [
    { path: '', component: LandingPageComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component:RegisterComponent},
    {path: 'topics',component:TopicComponent},
    {path: 'questions',component:QuestionsComponent},
    {path: 'question',component:QuestionComponent},
    {path: 'mock-interview',component:MockInterviewComponent},
    {path: 'profile',component: ProfileComponent},
    
];
