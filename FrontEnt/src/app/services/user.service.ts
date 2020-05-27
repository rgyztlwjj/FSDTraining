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

  /*login */
  postSignIn(user) {
    return this.http.post(`user`, JSON.stringify(user), httpOptions);
  }

   /*registe as buyer */
  postSignUp(buyer) {
    return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
  }

   /*registe as seller */
  postSignUpSeller(seller) {
    return this.http.post(`user/signinseller`, JSON.stringify(seller), httpOptions);
  }

  /*get current token */
  public get currentUserToken(): string {
    return sessionStorage.getItem('token');
  }


}
