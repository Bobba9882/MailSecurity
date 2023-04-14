import { Injectable } from '@angular/core';
import jwtDecode from "jwt-decode";
import {User} from "../Models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  public decodeUserToken(token: string) {
    const decodedInfo = jwtDecode<User>(token)

    const user = new User()
    user.username = decodedInfo.username
    user.id = decodedInfo.id
    localStorage.setItem("user info", JSON.stringify(user))
  }
}
