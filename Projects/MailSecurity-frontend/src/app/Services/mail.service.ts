import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Mail} from "../Models/mail";

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private mailURL: string = "http://localhost:9090/api/v1/mail/"
  constructor(private httpClient : HttpClient) { }

  getMails() : Observable<Mail[]>{
    let user = JSON.parse(String(localStorage.getItem("user info")))
    return this.httpClient.get<Mail[]>(`${this.mailURL + user.id}`)
  }
}
