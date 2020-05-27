import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service'

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';


interface Reportlist{
  itemname: string;
  price: string;
  sales: number;
  stock: number;
}
@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})

export class ReportsComponent implements OnInit {
  listOfCurrentreprotData: Reportlist[] = [];
  listOfreport: Reportlist[] = [];

  yearValue:string;
  monthValue:string;
  dayValue:string;
  yearValuestr:string;
  monthValuestr:string;
  dayValuestr:string;
  id="";
  strFromDate="";
  strToDate="";

  constructor(private proservice:ProductService) { }

  ngOnInit() {
    this.id = window.sessionStorage.getItem('userId');

    console.log("tab2+ID:"+ this.id+this.strFromDate+this.strToDate);

  }


  getdata(){
    this.proservice.getreport(this.id,this.strFromDate,this.strToDate).subscribe(
      data =>{
      console.log(JSON.stringify(data));
      const reports: any =data;
      if(reports){
        this.listOfreport = reports;
        console.log("list:" + JSON.stringify(this.listOfreport));
      }
    });
  }
  onSubmit(value: any){
    this.strFromDate =value.yearValuestr+this.fixdata(value.monthValuestr)+this.fixdata(value.dayValuestr);
    this.strToDate = value.yearValue+this.fixdata(value.monthValue)+this.fixdata(value.dayValue);
    this.getdata();
  }
  fixdata(data:string):string{
    var result=data;
    var le =data.toString.length;
    if(le < 2){
      result="0"+data;
      return result;
    }else{
      return result;
    }
  }

}
