import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})


export class SearchComponent implements OnInit {
  context:string;
  alerts:Alert[];

  constructor( private router:Router) { }

  ngOnInit() {
  }
  check(){
    this.reset();
    if (!this.context ) {
      this.alerts.push({type : 'danger', message: 'input search words!'});
    }else{
      this.router.navigate(['/productlist'],{queryParams:{Item:this.context}});
           
    }
  }
  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }
  reset() {
    this.alerts = Array.from(ALERTS);
  }
}
