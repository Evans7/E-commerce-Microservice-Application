import { Component, Input, Output, EventEmitter } from '@angular/core';

export interface IProduct {
  id: number;            // Maps to Long in Java
  name: string;          // Maps to String in Java
  description: string;   // Maps to String in Java
  price: number;         // Maps to BigDecimal in Java
  image: string;         // Maps to String in Java
  stock: number;         // Maps to Integer in Java
}

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  @Input() p!: IProduct;

}
