import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { SearchItem,Stock} from '../models/interfaces';
interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  alerts: Alert[];
constructor(private http:HttpClient)  {}

/* search by inputted context */
getContexProducts(context: string) {
  return this.http.get(`buyer/item/search`,
    { params: new HttpParams()
              .set('context', context)});
}


/* search by id */
getProductbyID(id: string) {
  return this.http.get(`buyer/item/detail`,
    { params: new HttpParams()
              .set('itemId', id)});
}

/* search by inputted filter */
/* startPrice; endPrice; manufacturer*/
getProducts(searchItem: SearchItem) {
  return this.http.get(`buyer/item/filter`,
    { params: new HttpParams()
              .set('startPrice', String(searchItem.startPrice))
              .set('endPrice', String(searchItem.endPrice))
              .set('manufacturer', searchItem.manufacturer)});
}

/*Set warning message */
setWarning():Alert[]{
  this.alerts = Array.from(ALERTS);
  this.alerts.push({type : 'warning', message: 'No data'});
  return this.alerts;
}
 
  update(id: string,stock: string) {
    alert("itemId" + id);
    alert("stock" + stock);
    return this.http.post(`seller/updatestock`,
      { params: new HttpParams()
                .set('itemId', id)
                .set('stock', stock)});
  }




  getreport(id:string,fromDate:string,toDate:string){
    alert("parar:"+ id);
    return this.http.get(`seller/report`,{ 
      params: new HttpParams()
              .set('userId', id)
              .set('strFromDate', fromDate)
              .set('strToDate', toDate)});
    // return this.http.post(`user/signinbuyer`, JSON.stringify(buyer), httpOptions);
  }

}
