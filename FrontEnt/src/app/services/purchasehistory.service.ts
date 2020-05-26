import { Injectable } from '@angular/core';
import {HttpClient, HttpParams,HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class PurchasehistoryService {

  constructor(private http:HttpClient)  {}


   /* search by userId */
   getHistory(userId: string) {
    return this.http.get(`buyer/history`,
      { params: new HttpParams()
                .set('userId', userId)});
  }

}
