export interface Manufacturer {
    id:number;
    name:string;
}
export interface Category {
    id:number;
    categoryName:string;
}
export interface SubCategory {
    id:number;
    categoryId:number;
    subcategoryname:string;
}
export interface SelectedSub{
    id:number;
    name:string;
  }

export  interface Stock{
    itemid:number;
    manufacturName:string;
    itemName:string;
    price:number;
    sellerId:number;
}
