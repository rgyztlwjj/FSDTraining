import { Component, OnInit } from '@angular/core';
import { reports } from '../../../services/reports';



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


  constructor() { }

  ngOnInit() {
    this.listOfreport = reports;
  }

}
