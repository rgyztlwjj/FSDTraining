import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent} from './pages/login/login.component';
import { RegisterComponent} from './pages/register/register.component';
import { SearchComponent} from './pages/search/search.component';
import { MycartComponent } from './pages/mycart/mycart.component';
import { HistorylistComponent } from './pages/historylist/historylist.component';
import { ProductlistComponent } from './pages/productlist/productlist.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { SalesManageComponent } from './pages/sales-manage/sales-manage.component';


const routes: Routes = [

  { path:  '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login',component: LoginComponent},
  { path: 'register',component: RegisterComponent},
  { path: 'search',component: SearchComponent},
  { path: 'mycart',component: MycartComponent},
  { path: 'historylist',component: HistorylistComponent},
  { path:  'productlist', component: ProductlistComponent},
  { path: 'products', component: ProductDetailsComponent},
  { path: 'salesmanage', component: SalesManageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
