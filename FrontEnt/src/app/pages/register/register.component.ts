import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  validateForm: FormGroup;
  
  submitForm(value:FormGroup): void {
    if(this.valideInput(value)){

    }
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }
  valideInput(value: FormGroup):boolean {
    if(value.getRawValue){

    }
    throw new Error("Method not implemented.");
  }
  constructor(private fb: FormBuilder) { }


  ngOnInit() {
  }

}
