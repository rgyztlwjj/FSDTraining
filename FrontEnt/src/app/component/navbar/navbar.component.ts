import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  constructor(private router:Router) { }

  ngOnInit() {
  }
  
  isLogined() : boolean {
    if (window.sessionStorage["token"]) {
      return true;
    }
    return false;
  }

  logout() {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.removeItem("userId");
    window.sessionStorage.removeItem("role");
    this.router.navigate(['/']);
  }
  isBuyer():boolean{
    if (window.sessionStorage["role"] === "1") {
      return true;
    }
    return false;
  }

}
