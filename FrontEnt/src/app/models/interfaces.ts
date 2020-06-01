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

export interface Cart{
    number:number;
    itemId:number;
    buyerId:any;

}

export  interface Stock{
    itemid:number;
    manufacturName:string;
    itemName:string;
    price:number;
    sellerId:number;
}

export interface SearchItem{
    startPrice: string;
    endPrice: string;
    manufacturer: string;
}
export  interface product{
    id:any;
    itemname:any;
    manufacturename:any;
    price:any;
    description:any;
    pictures:string[];
}
