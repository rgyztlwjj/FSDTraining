import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';

import { NgZorroAntdModule } from 'ng-zorro-antd';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { AppComponent } from './app.component';
import { NavbarComponent} from './component/navbar/navbar.component'
import { TopBarComponent } from './top-bar/top-bar.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { LoginComponent} from './pages/login/login.component';
import { SearchComponent} from './pages/search/search.component';
import { RegisterComponent} from './pages/register/register.component';
import { MycartComponent } from './pages/mycart/mycart.component';
import { HistorylistComponent } from './pages/historylist/historylist.component';
import { ProductlistComponent } from './pages/productlist/productlist.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { SalesManageComponent } from './pages/sales-manage/sales-manage.component';
import { AddProductComponent } from './pages/sales-manage/add-product/add-product.component';
import { ManageStockComponent } from './pages/sales-manage/manage-stock/manage-stock.component';
import { ReportsComponent } from './pages/sales-manage/reports/reports.component';
import { UploadPictureComponent }from './pages/sales-manage/upload-picture/upload-picture.component';
import { MoneyPipe } from './pipe/money.pipe';
import {JwtInterceptor} from './interceptor/jwt.interceptor';
import { UserService } from './services/user.service'


@NgModule({
   declarations: [
      AppComponent,
      NavbarComponent,
      TopBarComponent,
      CheckoutComponent,
      LoginComponent,
      SearchComponent,
      RegisterComponent,
      MycartComponent,
      HistorylistComponent,
      ProductlistComponent,
      ProductDetailsComponent,
      SalesManageComponent,
      MoneyPipe,
      AddProductComponent,
      ManageStockComponent,
      ReportsComponent,
      UploadPictureComponent
   ],
   imports: [
      CommonModule,
      HttpClientModule,
      NgbModule,
      FormsModule,
      ReactiveFormsModule,
      NgZorroAntdModule,
      NzIconModule,
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule
   ],
   providers: [ { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
      UserService],
   bootstrap: [
      AppComponent
   ]
 
})
export class AppModule {

 }
