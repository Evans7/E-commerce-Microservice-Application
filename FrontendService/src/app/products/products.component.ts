import { Component, inject, OnInit } from '@angular/core';
import { ProductService, IProduct} from '../product.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  products: IProduct[] = []; 

  private productService = inject(ProductService);


  ngOnInit(): void {
    console.log('ProductsComponent: Initializing component');

    this.productService.getProducts().subscribe({
      next: (data: IProduct[]) => {
        console.log('ProductsComponent: Products received', data);
        this.products = data;
      },
      error: (err) => {
        console.error('ProductsComponent: Error occurred while fetching products', err);
      }
    });

    console.log('ProductsComponent: Fetching products initiated');
  }
}
