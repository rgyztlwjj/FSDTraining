import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CartService } from '../../services/cart.service';

interface ItemData {
  id: string;
  itemname: string;
  price: any;
  number: any;
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
  TotalP:any;
  TotalT:any;
  alerts:any;
  userId:string;

  startEdit(number: string): void {
    this.editnumber=number;
  }

  stopEdit(): void {
    this.editnumber = null;
  }

  deleteRow(id: string): void {
    if(confirm("sure delete?")){

      // this.listOfData = this.listOfData.filter(d => d.id !== id);
      this.carservice.deteleitem(id).subscribe(data => {
        console.log(JSON.stringify(data));
        const info: any = data;
        if(info){
          this.ngOnInit();
        }
      },
      res => {
        const response: any = res;
        console.log(response.status);
        // this.alerts=this.service.setWarning();
      });
    }
  }

  ngOnInit(): void {
    this.userId = window.sessionStorage.getItem('userId');
    console.log("userId" + this.userId);

     /* get Item Detail information buy id*/
     this.carservice.getCart(this.userId).subscribe(data => {
      console.log(JSON.stringify(data));
      const info: any = data;
      if(info){
        this.listOfData = info;
        this.countTotal();
      }
    },
    res => {
      const response: any = res;
      console.log(response.status);
      // this.alerts=this.service.setWarning();
    });
  }

  countTotal(){
    var tax =0;
    var total=0;
    var counter = this.listOfData.length;
    for(var i=0;i<counter;i++){
      var arr=this.listOfData[i];
      if(arr["tax"]){
        //  tax = (tax +arr["tax"]).toFix(2);
        tax = this.accAdd(tax,arr["tax"]*arr["number"]);
         console.log("tax:" +  tax);
      }
      if(arr["price"]){
        // total = (total +arr["price"]).toFix(2);
        total = this.accAdd(total,arr["price"]*arr["number"]);
        console.log("total:" +  total);
     }
    }
    this.TotalP = total;
    this.TotalT = tax;
  }

   accAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
    }
}
