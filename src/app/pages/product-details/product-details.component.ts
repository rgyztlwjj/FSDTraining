import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { products } from '../../services/products';
// import { CartService } from '../cart.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product;

  constructor(
    private route: ActivatedRoute,
    // private cService: CartService
  ) { }
  addToCart(product){
    window.alert('Your product has been added to the cart');
    // this.cService.addToCart(product);
  }
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // window.alert(+params.get('productId'));
      this.product = products[+params.get('productId')];
      
    })
  }
}
