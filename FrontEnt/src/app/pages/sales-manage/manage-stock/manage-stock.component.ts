import { Component, OnInit } from '@angular/core';
import { CommonService } from '../../../services/common.service'
import { products } from '../../../services/products';

interface ItemData {
  itemid;number;
  name: string;
  price: number;
  description: string;
  stock: number;
}

@Component({
  selector: 'app-manage-stock',
  templateUrl: './manage-stock.component.html',
  styleUrls: ['./manage-stock.component.css']
})
export class ManageStockComponent implements OnInit {
  listOfCurrentPageData: ItemData[] = [];
  listOfData: ItemData[] = [];

  constructor(private commonservice:CommonService ) { }

  ngOnInit() {

    let id = window.sessionStorage.getItem('userId');
    console.log("tab2+ID:"+ id);

    this.commonservice.getitemlist(id).subscribe(
            data =>{
            console.log(JSON.stringify(data));
            const products: any =data;
            if(products){
              this.listOfData = products;
              console.log("list:" + JSON.stringify(this.listOfData));
            }
    });
  }

  onCurrentPageDataChange($event: ItemData[]): void {
    this.listOfCurrentPageData = $event;
  }

  _submitForm(){

  }

}
