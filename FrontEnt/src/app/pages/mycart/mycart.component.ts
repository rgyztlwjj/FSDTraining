import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Route, Router } from '@angular/router';

import { CartService } from '../../services/cart.service';

interface ItemData {
  id: string;
  itemname: string;
  price: string;
  number: string;
}
@Component({
  selector: 'app-mycart',
  templateUrl: './mycart.component.html',
  styleUrls: ['./mycart.component.css']
})
export class MycartComponent implements OnInit {

  constructor(
    private router:Router,
    private activerouter: ActivatedRoute,
    private carservice: CartService
  ) { }

  i = 0;
  editnumber: string | null;
  listOfData: ItemData[] = [];
  alerts:any;

  startEdit(number: string): void {
    this.editnumber=number;
  }

  stopEdit(): void {
    this.editnumber = null;
  }

  addRow(): void {
    this.listOfData = [
      ...this.listOfData,
      {
        id: `${this.i}`,
        itemname: `Edward King ${this.i}`,
        price: '3200',
        number: `${this.i}`
      }
    ];
    this.i++;
  }

  deleteRow(id: string): void {
    if(confirm("sure delete?")){

      this.listOfData = this.listOfData.filter(d => d.id !== id);
    }
  }

  ngOnInit(): void {
    let userId = window.sessionStorage.getItem('userId');
    console.log("userId" + userId);
    
     /* get Item Detail information buy id*/
     this.carservice.getCart(userId).subscribe(data => {
      console.log(JSON.stringify(data));
      const info: any = data;
      if(info){
        this.listOfData = info;
      }
    },
    res => {
      const response: any = res;
      console.log(response.status);
      // this.alerts=this.service.setWarning();
    });
  }
}
