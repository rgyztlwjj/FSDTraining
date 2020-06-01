import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Route, Router } from '@angular/router';

import { product,Cart} from '../../models/interfaces';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  effect = 'scrollx';
  product:product;
  id:string;'';
  alerts:any;
  context:any;
  cart:Cart ;

  constructor(
    private router:Router,
    private activerouter: ActivatedRoute,
    private service: ProductService,
    private carservice: CartService
  ) { }
  addToCart(){
    window.alert('Your product has been added to the cart');
    let userId = window.sessionStorage.getItem('userId');
    this.cart={
      buyerId : userId,
      itemId :this.product.id,
      number :1
    }
    console.log("cartData" + JSON.stringify(this.cart));
    this.carservice.addToCart(this.cart).subscribe(data => {
      console.log(JSON.stringify(data));
      const info: any = data;
      if(info){
        console.log("ReturnData" + JSON.stringify(info));
        this.router.navigate(['/mycart']);
      }
    },
    res => {
      const response: any = res;
      console.log(response.status);
      this.alerts=this.service.setWarning();
    });
  }

  ngOnInit() {

    this.id=this.activerouter.snapshot.queryParams["itemId"];
    this.context =this.activerouter.snapshot.queryParams["Item"];

    /* get Item Detail information buy id*/
    this.service.getProductbyID(this.id).subscribe(data => {
      console.log(JSON.stringify(data));
      const info: any = data;
      if(info){
        this.product = info;
        this.product.manufacturename = info.manufacture.name;
        this.product.description=info.descriptionString
      }
    },
    res => {
      const response: any = res;
      console.log(response.status);
      this.alerts=this.service.setWarning();
    });
  }

  /*goback Item Search Results */
  goto(){
      this.router.navigate(['/productlist'],{queryParams:{Item:this.context}});
    
  }

}
