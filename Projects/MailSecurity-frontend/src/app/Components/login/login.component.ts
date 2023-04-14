import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../Services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string;
  password: string;

  constructor(private authService : AuthService,public router : Router) {
  }

  onSubmit() {
    this.authService
      .createToken(this.username, this.password)
      .subscribe({
        next: () => {this.router.navigate(['home'])}
      })
  }
}
