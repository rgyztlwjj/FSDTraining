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

  /*get manufacturer list */
  getManufactrue(){
    return this.http.get<Manufacturer[]>(`seller/manufacturer`);
  }

  /*get category list */
  getCategory(){
    return this.http.get<Category[]>(`seller/category`);
  }

  /*get subcategory list */
  getSubcategory(){
    return this.http.get<SubCategory[]>(`seller/subcategory`);
  }


}
