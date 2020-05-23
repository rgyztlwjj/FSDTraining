import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import { Manufacturer, Category ,SubCategory} from '../models/interfaces';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class UserService {

  Manufacturer:Manufacturer[];
  constructor(private http: HttpClient) { }

  postSignIn(user) {
    alert("input:"+ JSON.stringify(user));
    return this.http.post(`user`, JSON.stringify(user), httpOptions);
    // return this.http.get(`seller`);
  }

  postSignUp(buyer) {
    alert("input:"+ JSON.stringify(buyer));
    return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
    // return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
  }

  postSignUpSeller(seller) {
    alert("input:"+ JSON.stringify(seller));
    return this.http.post(`user/signinseller`, JSON.stringify(seller), httpOptions);
    // return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
  }
  public get currentUserToken(): string {
    return sessionStorage.getItem('token');
  }


}
