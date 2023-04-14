import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MailHomeComponent} from "./Components/mail-home/mail-home.component";

const routes: Routes = [
  { path: 'home', component: MailHomeComponent },
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
