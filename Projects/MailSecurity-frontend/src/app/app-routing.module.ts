import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MailHomeComponent} from "./Components/mail-home/mail-home.component";
import {LoginComponent} from "./Components/login/login.component";

const routes: Routes = [
  { path: 'home', component: MailHomeComponent },
  { path: 'login', component: LoginComponent },
  {path: '', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
