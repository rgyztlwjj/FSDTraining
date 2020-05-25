import { Injectable } from '@angular/core';
import {HttpClient, HttpParams,HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class CartService {
  items = [];

  constructor(private http:HttpClient)  {}

   /* add to cart */
  addToCart(item){
    this.items.push(item);
      alert("input:"+ JSON.stringify(item));
      return this.http.post(`buyer/cart/add`, JSON.stringify(item), httpOptions);
  }

  /* search by userId */
  getCart(userId: string) {
    return this.http.get(`buyer/cart`,
      { params: new HttpParams()
                .set('userId', userId)});
  }

  clearCart(){
    this.items = [];
    return this.items;
  }

}