import { Component, OnInit } from '@angular/core';
import { products } from '../../services/products';
interface ItemData {
  name: string;
  price: number;
  description: string;
  stock: number;
}


@Component({
  selector: 'app-sales-manage',
  templateUrl: './sales-manage.component.html',
  styleUrls: ['./sales-manage.component.css']
})
export class SalesManageComponent implements OnInit {

  listOfOption: Array<{ label: string; value: string }> = [];
  listOfTagOptions = [];

  listOfCurrentPageData: ItemData[] = [];
  listOfData: ItemData[] = [];
  yearValue = 2020;
  monthValue = 1;
  dayValue = 1;

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
  // tabs = [1, 2, 3];

}
