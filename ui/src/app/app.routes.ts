import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { ChatsectionComponent } from './components/main/chatsection/chatsection.component';

export const routes: Routes = [

    {path:'',redirectTo:'/login',pathMatch:'full'},
    {path:"login",component:LoginComponent,title:"Hashtag"},

    { path: 'm', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: MainComponent, children: [
      { path: 'chat', component: ChatsectionComponent, data: { title: 'Hashtag' } }
    ]},
];
