import { Component, OnInit } from '@angular/core';

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

  i = 0;
  editnumber: string | null;
  listOfData: ItemData[] = [];

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
    this.addRow();
    this.addRow();
    this.addRow();
    this.addRow();
    this.addRow();
    this.addRow();
    this.addRow();
    this.addRow();
  }
}
