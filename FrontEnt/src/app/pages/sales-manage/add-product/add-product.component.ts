import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { CommonService } from '../../../services/common.service';
import { ProductService } from '../../../services/product.service';
import { Manufacturer,Category,SubCategory ,SelectedSub} from '../../../models/interfaces';

interface Alert {
  type: string;
  message: string;
}
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  manufacturers:Manufacturer[];
  categorys: Category[];
  subcategorys: SubCategory[];
  selectsubs: SelectedSub[];

  alerts: Alert[];

  categoryvalue:string;
  subcategoryValue:string;
  manufacturersValue:string;
  itemname:string;
  price:string;
  stock:string;
  sellerId:string;

  validateForm: FormGroup;

  constructor(
    private commonservice:CommonService ,
    private proservice:ProductService,
    private routerinfo:ActivatedRoute) { }

  ngOnInit() {

    this.sellerId=this.routerinfo.snapshot.queryParams["Id"];
    console.info("sellerId" + this.sellerId);
    /*get manufacturer */
    this.commonservice.getManufactrue().subscribe(
      data =>{
        console.log(JSON.stringify(data));
        const manu: any =data;
        if(manu){
          this.manufacturers=manu;
        }
      }
    );

    /*get category */
    this.commonservice.getCategory().subscribe(
      data =>{
        console.log(JSON.stringify(data));
        const cate: any =data;
        if(cate){
          this.categorys=cate;
          console.log("list:" + JSON.stringify(this.categorys));
        }
      }
    );

    /*get subcategory */
    this.commonservice.getSubcategory().subscribe(
      data =>{
        console.log(JSON.stringify(data));
        const subcate: any =data;
        if(subcate){
          this.subcategorys=subcate;
        }
      }
    );

    }


    /*get subcategory by selected categy */
    subcateChange( categoryvalue){
      this.selectsubs=[];
      for( let i in this.subcategorys){
        let obj={id:0,name:""};
        if( this.subcategorys[i].categoryId === categoryvalue){
          obj.id = this.subcategorys[i].id;
          obj.name = this.subcategorys[i].subcategoryname;
          this.selectsubs.push(obj);
        }

      }
    }

    onSubmit(){
      console.info("categoryvalue=" + this.categoryvalue);
      console.info("subcategoryValue=" + this.subcategoryValue);
      console.info("manufacturersValue=" + this.manufacturersValue);
      let item={
        categoryId:this.categoryvalue,
        subcategoryId:this.subcategoryValue,
        manufacturId:this.manufacturersValue,
        itemName:this.itemname,
        price:this.price,
        stock:this.stock,
        sellerId:this.sellerId
      }
      console.log(JSON.stringify(item));

      this.proservice.additem(item).subscribe(
        data =>{
          console.log(JSON.stringify(data));
          const result: any =data;
          if(result){
            console.log('Added a item');
            this.alerts.push({type : 'success', message: 'You have created your accont'});
          }
        }
      );
    }


}
