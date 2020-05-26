import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { PurchasehistoryService } from '../../services/purchasehistory.service';
interface ItemData {
  id: number;
  name: string;
  price: number;
  date: string;
}

@Component({
  selector: 'app-historylist',
  templateUrl: './historylist.component.html',
  styleUrls: ['./historylist.component.css']
})
export class HistorylistComponent implements OnInit {

  listOfCurrentPageData: ItemData[] = [];
  listOfData: ItemData[] = [];

  userId:string;

  constructor(
    private router:Router,
    private activerouter: ActivatedRoute,
    private service: PurchasehistoryService
  ) { }

  onCurrentPageDataChange($event: ItemData[]): void {
    this.listOfCurrentPageData = $event;
    // this.refreshCheckedStatus();
  }


  ngOnInit(): void {
    this.userId = window.sessionStorage.getItem('userId');
    console.log("userId" + this.userId);

       /* get Item Detail information buy id*/
       this.service.getHistory(this.userId).subscribe(data => {
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
  goto(id){
    this.router.navigate(['/products'],{queryParams:{itemId:id}});
}
}
