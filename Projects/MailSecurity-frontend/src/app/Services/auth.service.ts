import { Injectable } from '@angular/core';
import {BehaviorSubject, tap} from "rxjs";
import {TokenService} from "./token.service";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false)
  public isLoggedIn$ = this._isLoggedIn$.asObservable()
  constructor(private tokenService: TokenService, private userService: UserService) { }

  createToken(username: string, password: string) {
    return this.tokenService.getToken(username,password).pipe(
      tap(response => {
        localStorage.setItem("auth token", response.text)
        this._isLoggedIn$.next(true)
        this.userService.decodeUserToken(response.text)
      }))
  }
}
