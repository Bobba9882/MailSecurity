import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MailHomeComponent } from './Components/mail-home/mail-home.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { ReplaceLineBreaksPipe } from './Pipes/replace-line-breaks.pipe';
import { OrderByPipe } from './Pipes/order-by.pipe';

@NgModule({
  declarations: [
    AppComponent,
    MailHomeComponent,
    ReplaceLineBreaksPipe,
    OrderByPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
