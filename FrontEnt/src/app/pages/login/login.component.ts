import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import { Router } from '@angular/router';

interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  alerts: Alert[];

  constructor(private userService: UserService, private router: Router) {
    this.reset();
  }

  ngOnInit(): void {
    this.role = "1";
  }

  username: string;
  password: string;
  role: string
  /* login */
  onSubmit() {
    console.info("uername=" + this.username);
    console.info("password=" + this.password);

    let usr ={
      username:this.username,
      password:this.password,
      role :this.role
    }
    if (this.validInput()) {
      this.userService.postSignIn(usr).subscribe(
        data => {

          console.log("backg"+data.toString);
          console.log(JSON.stringify(data));
          const info: any =  data;

          alert(info.token)
          if(info.token){
            window.sessionStorage.setItem('token', info.token);
            window.sessionStorage.setItem('role', this.role);
            window.sessionStorage.setItem('userId', info.id);

            if ("1" === info.role) {
              console.log('buyer in');
              this.router.navigate(['/search']);
          } else if ("2" === info.role){
            console.log('seller in');
            this.router.navigate(['/salesmanage'],{queryParams:{Id:info.id}});
            }
          }
          else{
            this.alerts.push({type : 'danger', message: 'username or password error!'});
          }
        },
        res => {
          const response: any = res;
          console.log(response.status);
          this.alerts.push({type : 'danger', message: 'username or password error!'});
        }
      );
    }
  }
  /* valid input data */
  validInput(): boolean {
    this.reset();
    if (!this.username) {
      this.alerts.push({type : 'danger', message: 'username required!'});
      return false;
    }

    if (!this.password) {
      this.alerts.push({type : 'danger', message: 'password required!'});
      return false;
    }

    if (this.password.length < 6) {
      this.alerts.push({type : 'danger', message: 'password length must be greater than 6!'});
      return false;
    }

    if (!this.role) {
      this.alerts.push({type : 'danger', message: 'role required'});
      return false;
    }
    return true;
  }
  

  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  reset() {
    this.alerts = Array.from(ALERTS);
  }
      
}
