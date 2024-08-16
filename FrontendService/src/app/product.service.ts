import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { response } from 'express';

export interface IProduct {
  id: number;            // Maps to Long in Java
  name: string;          // Maps to String in Java
  description: string;   // Maps to String in Java
  price: number;         // Maps to BigDecimal in Java
  image: string;         // Maps to String in Java
  stock: number;         // Maps to Integer in Java
}

export interface ICartItem {
  id: number;            // Maps to Long in Java
  name: string;          // Maps to String in Java
  description: string;   // Maps to String in Java
  price: number;         // Maps to BigDecimal in Java
  image: string;         // Maps to String in Java
  stock: number; 
  quantity: number;        // Maps to Integer in Java
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productServiceUrl: string = 'http://localhost:8080/api/products'
  private orderServiceUrl: string = 'http://localhost:8080/api/orders/add';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<IProduct[]> {
    return this.http.get<any>(this.productServiceUrl);
  }




  placeOrder(order: any): Observable<any> {
    console.log("in Service: ", order);
    return this.http.post<any>(this.orderServiceUrl, order, { observe: 'response' as const });
  }


  // http.post<Config>('/api/config', newConfig).subscribe(config => {
  //   console.log('Updated config:', config);
  // });

  // placeOrder(): Observable<any> {
  //   return this.http.post<any>(this.orderServiceUrl, {
  //     title: 'foo',
  //     body: 'bar',
  //     userId: 1,
  //   });
  // }
}
