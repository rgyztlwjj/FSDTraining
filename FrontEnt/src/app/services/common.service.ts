import { Injectable } from '@angular/core';
import { HttpClient, HttpParams ,HttpRequest,HttpHeaders} from '@angular/common/http';

import { Manufacturer, Category ,SubCategory,Stock} from '../models/interfaces';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private http: HttpClient) { }

  getManufactrue(){
    return this.http.get<Manufacturer[]>(`seller/manufacturer`);
    // return this.http.get<Manufacturer[]>(`seller-micoroserver/seller`);
  }

  getCategory(){
    return this.http.get<Category[]>(`seller/category`);
  }

  getSubcategory(){
    return this.http.get<SubCategory[]>(`seller/subcategory`);
  }

  additem(item){
    alert("input:"+ JSON.stringify(item));
    return this.http.post(`seller/additem`, JSON.stringify(item), httpOptions);
  }



  getitemlist(id){
    alert("parar:"+ id);
    return this.http.get<Stock[]>(`seller/stock`,{ params: new HttpParams().set('userId', id)});
    // return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
  }


}
