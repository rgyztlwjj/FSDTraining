import { Component, OnInit, NgModule } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../services/user.service';

interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  role: string;
  username: string;
  password: string;
  confirmPassword: string;
  mobilePhone: string;
  email: string;
  companyname: string;
  GSTIN: string;
  brief: string;
  postaladdress: string;
  website: string;
  isshow: boolean =true;
  alerts: Alert[];
  validateForm: FormGroup;

  constructor(private userService: UserService,private fb: FormBuilder,private router: Router,
    private routerinfo:ActivatedRoute) { 
      this.reset();
    }

  ngOnInit() {
    this.role=this.routerinfo.snapshot.queryParams["role"];
    
    if("1" === this.role){
      this.isshow=false;

      this.validateForm = this.fb.group({
        username: [null, [Validators.required]],
        password: [null, [Validators.required]],
        email: [null, [Validators.email, Validators.required]],
        mobilePhone: [null, [Validators.required]],
        confirmPassword: [null, [Validators.required, this.confirmationValidator]]
      });
    }else{
      this.isshow=true;

      this.validateForm = this.fb.group({
        username: [null, [Validators.required]],
        password: [null, [Validators.required]],
        email: [null, [Validators.email, Validators.required]],
        mobilePhone: [null, [Validators.required]],
        confirmPassword: [null, [Validators.required, this.confirmationValidator]],
        companyname: [null, [Validators.required]],
        brief: [null, [Validators.required]],
        GSTIN: [null, [Validators.required]],
        postaladdress: [null, [Validators.required]],
        website: [null, [Validators.required]]
      });
    }
  }
  
  /*register*/
  submitForm(): void {
    this.reset();
    let isok:boolean =false;
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
      
      if (this.validateForm.controls[i].errors) {
        isok = true;
      }
    }

    if (!isok) {
      if("1" === this.role){
        //regist as a buyer
        this.postAsbuyer();
      }else{
        //regist as a seller
        this.postAsSeller();
      }
    }
  }

  postAsbuyer(){

    let buyer ={
      username:this.username,
      password:this.password,
      email:this.email,
      mobilePhone:this.mobilePhone
    }

    console.log("Buyer:"+JSON.stringify(buyer));
    this.userService.postSignUp(buyer).subscribe(
      data => {
        console.log(JSON.stringify(data));

        const info: any =  data;

        if(info){
          console.log('creat a buyer');
          this.alerts.push({type : 'success', message: 'You have created your accont'});
          // this.router.navigate(['/login'], { queryParams: { userName: this.username } });
        }else{
          this.alerts.push({type : 'danger', message: 'The username has been registered by other'});
        }
      },
      res => {
        const response: any = res;
        console.log(response.status);
        this.alerts.push({type : 'danger', message: 'The username has been registered by other'});
      }
    );
  }

  postAsSeller(){

    let seller ={
      username:this.username,
      password:this.password,
      email:this.email,
      contactNumber:this.mobilePhone,
      gstin:this.GSTIN,
      companyName:this.companyname,
      briefAboutCompany:this.brief,
      postalAddress:this.postaladdress,
      website:this.website
    }

    console.log("Buyer:"+JSON.stringify(seller));
    this.userService.postSignUpSeller(seller).subscribe(
      data => {
        console.log(JSON.stringify(data));

        const info: any =  data;

        if(info){
          console.log('creat a seller');
          this.alerts.push({type : 'success', message: 'You have created your accont'});
          // this.router.navigate(['/login'], { queryParams: { userName: this.username } });
        }else{
          console.log('seller in');
          this.alerts.push({type : 'danger', message: 'The username has been registered by other'});
        }
      },
      res => {
        const response: any = res;
        console.log(response.status);
        this.alerts.push({type : 'danger', message: 'The username has been registered by other'});
      }
    );
  }
  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }
  reset() {
    this.alerts = Array.from(ALERTS);
  }

}
