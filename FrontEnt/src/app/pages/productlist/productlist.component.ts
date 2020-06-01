import { Component, OnInit } from '@angular/core';

import { ProductService } from '../../services/product.service';
import { CommonService } from '../../services/common.service';
import { Manufacturer,SearchItem,product} from '../../models/interfaces';
import { ActivatedRoute ,Router} from'@angular/router';

@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.component.html',
  styleUrls: ['./productlist.component.css']
})
export class ProductlistComponent implements OnInit {
  effect = 'scrollx';
  manufacturers:Manufacturer[];
  searchItem: SearchItem;
  productlst:product[];
  pic:string;


  constructor(private commonservice: CommonService,
              private service: ProductService,
              private routerinfo: ActivatedRoute,
              private router: Router) {}

  listOfOption: Array<{ label: string; value: string }> = [];
  listOfTagOptions = [];

 
  listOfCurrentPageData: product[] = [];
  listOfData: product[] = [];
  alerts:any;
  

  context="";

  onCurrentPageDataChange($event: product[]): void {
    this.listOfCurrentPageData = $event;
  }

  ngOnInit(): void {
    /*get paramters from prepage  */
    this.context=this.routerinfo.snapshot.queryParams["Item"];
    console.log("para:"+ this.context);
   /*display manufacturer list */
   this.commonservice.getManufactrue().subscribe(
    data =>{
      console.log(JSON.stringify(data));
      const manu: any =data;
      if(manu){
        this.manufacturers=manu;
        }
      }
    );

    /*blank the input filter */
    this.searchItem = {
      startPrice: '',
      endPrice: '',
      manufacturer: '',
    }
    // this.products$ = this.service.getProducts(this.searchItem);
    
    /* */
    this.service.getContexProducts(this.context).subscribe(data => {
      console.log(JSON.stringify(data));
      const info: any = data;
      if(info){
        this.listOfData = info;
      }
    },
    res => {
      const response: any = res;
      console.log(response.status);
      this.alerts=this.service.setWarning();
    });

  }

  /* */
  onSubmit(value: any) {
    if(!(value.startPrice ==='')||!(value.endPrice==='')||!(value.manufacturer==='') ){
      this.service.getProducts(value).subscribe(data => {
        console.log(JSON.stringify(data));
        const info: any = data;

        if(info){
          this.listOfData = info;
          console.log("list:"+JSON.stringify(this.listOfData));
        }
      },
      res => {
        const response: any = res;
        console.log(response.status);
        this.alerts=this.service.setWarning();
      }
      );
    }

  }

  close(any) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  goto(id){
      this.router.navigate(['/products'],{queryParams:{Item:this.context,itemId:id}});
  }
}
