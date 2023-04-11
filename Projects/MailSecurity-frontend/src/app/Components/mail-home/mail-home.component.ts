import {Component, OnInit} from '@angular/core';
import {Mail} from "../../Models/mail";
import {MailService} from "../../Services/mail.service";

@Component({
  selector: 'app-mail-home',
  templateUrl: './mail-home.component.html',
  styleUrls: ['./mail-home.component.css']
})
export class MailHomeComponent implements OnInit{

  Mails : Mail[]
  selectedMail: Mail = new Mail()

  constructor(private mailService: MailService) {
  }

  ngOnInit(): void {
    this.mailService.getMails().subscribe({
      next: value => {
        this.Mails = value
      }
    })
  }

  selectMail(mail : Mail): void {
    this.selectedMail = mail
  }

}
