import { Component, OnInit } from '@angular/core';

import { products } from '../../services/products';

interface ItemData {
  name: string;
  price: number;
  description: string;
  stock:number;
}
@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.component.html',
  styleUrls: ['./productlist.component.css']
})
export class ProductlistComponent implements OnInit {
  listOfOption: Array<{ label: string; value: string }> = [];
  listOfTagOptions = [];

  // ngOnInit(): void {
  //   const children: Array<{ label: string; value: string }> = [];
  //   for (let i = 10; i < 36; i++) {
  //     children.push({ label: i.toString(36) + i, value: i.toString(36) + i });
  //   }
  //   this.listOfOption = children;
  // }

 
  listOfCurrentPageData: ItemData[] = [];
  listOfData: ItemData[] = [];

  onCurrentPageDataChange($event: ItemData[]): void {
    this.listOfCurrentPageData = $event;
  }

  ngOnInit(): void {

     const children: Array<{ label: string; value: string }> = [];
    for (let i = 10; i < 36; i++) {
      children.push({ label: i.toString(36) + i, value: i.toString(36) + i });
    }
    this.listOfOption = children;

    this.listOfData = products;
  }
}
