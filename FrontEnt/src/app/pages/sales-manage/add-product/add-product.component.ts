import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UploadFile } from 'ng-zorro-antd/upload';

import { CommonService } from '../../../services/common.service';
import { ProductService } from '../../../services/product.service';
import { Manufacturer,Category,SubCategory ,SelectedSub} from '../../../models/interfaces';

interface Alert {
  type: string;
  message: string;
}
function getBase64(file: File): Promise<string | ArrayBuffer | null> {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}
const ALERTS: Alert[] = [];

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
  description:string;
  sellerId:string;

  validateForm: FormGroup;

    //picture
    pictureControls:UploadFile[] = [];
    previewImage: string | undefined = '';
    previewVisible = false;

  constructor(
    private commonservice:CommonService ,
    private proservice:ProductService,
    private routerinfo:ActivatedRoute) { }

  ngOnInit() {
    this.reset();

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


      let pictures : string[] = [];
      for (let picture of this.pictureControls) {
        pictures.push(picture.response.path);
      }

      let item={
        categoryId:this.categoryvalue,
        subcategoryId:this.subcategoryValue,
        manufacturId:this.manufacturersValue,
        itemname:this.itemname,
        price:this.price,
        descriptionString:this.description,
        sellerId:this.sellerId,
        pictures:pictures
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

    handlePreview = async (file: UploadFile) => {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj!);
      }
      this.previewImage = file.url || file.preview;
      this.previewVisible = true;
    };
  
    handleUploadPictureChange(info: { file: UploadFile }): void {
      switch (info.file.status) {
        case 'uploading':
          break;
        case 'done':
          info.file.url = info.file.response.path;
          break;
      }
    }

    close(alert: Alert) {
      this.alerts.splice(this.alerts.indexOf(alert), 1);
    }

    reset() {
      this.alerts = Array.from(ALERTS);
    }
}
