import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MailHomeComponent } from './Components/mail-home/mail-home.component';
import { AppRoutingModule } from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import { ReplaceLineBreaksPipe } from './Pipes/replace-line-breaks.pipe';
import { OrderByPipe } from './Pipes/order-by.pipe';
import { LoginComponent } from './Components/login/login.component';
import {FormsModule} from "@angular/forms";
import {AuthInterceptor} from "./Services/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    MailHomeComponent,
    ReplaceLineBreaksPipe,
    OrderByPipe,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
