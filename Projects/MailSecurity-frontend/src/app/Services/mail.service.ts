import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Mail} from "../Models/mail";

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private mailURL: string = "http://localhost:8080/api/v1/mail"
  constructor(private httpClient : HttpClient) { }

  getMails() : Observable<Mail[]>{
    return this.httpClient.get<Mail[]>(`${this.mailURL}`)
  }
}
