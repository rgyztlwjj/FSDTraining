import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

constructor() { }

isLogined() : boolean {
  if (window.sessionStorage["token"]) {
    return true;
  }
  return false;
}
}
