import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { NgZorroAntdModule } from 'ng-zorro-antd';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';


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
import { MoneyPipe } from './pipe/money.pipe';


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
      MoneyPipe
   ],
   imports: [
      HttpClientModule,
      NgbModule,
      FormsModule,
      ReactiveFormsModule,
      NgZorroAntdModule,
      NzIconModule,
      BrowserModule,
      AppRoutingModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
