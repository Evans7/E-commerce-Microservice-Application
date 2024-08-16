import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface IProduct {
  id: number;            // Maps to Long in Java
  name: string;          // Maps to String in Java
  description: string;   // Maps to String in Java
  price: number;         // Maps to BigDecimal in Java
  image: string;         // Maps to String in Java
  stock: number;         // Maps to Integer in Java
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productServiceUrl: string = 'http://localhost:8081/products'

  constructor(private http: HttpClient) { }

  getProducts(): Observable<IProduct[]> {
    return this.http.get<any>(this.productServiceUrl);
  }



}
