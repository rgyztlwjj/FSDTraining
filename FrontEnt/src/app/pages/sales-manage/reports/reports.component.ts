import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service'


interface Reportlist{
  name: string;
  property: string;
  sold: number;
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

  yearValue = 2020;
  monthValue = 1;
  dayValue = 1;
  yearValuestr = 2020;
  monthValuestr = 1;
  dayValuestr = 1;

  strFromDate='20200101';
  strToDate='20200101';

  constructor(private proservice:ProductService) { }

  ngOnInit() {
    // this.listOfreport = reports;

    let id = window.sessionStorage.getItem('userId');
    console.log("tab2+ID:"+ id+this.strFromDate+this.strToDate);

    this.proservice.getreport(id,this.strFromDate,this.strToDate).subscribe(
            data =>{
            console.log(JSON.stringify(data));
            const reports: any =data;
            if(reports){
              this.listOfreport = reports;
              console.log("list:" + JSON.stringify(this.listOfreport));
            }
    });
  }

}
