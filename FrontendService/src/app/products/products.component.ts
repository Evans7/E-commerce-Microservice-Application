import { Component, inject, OnInit } from '@angular/core';
import { ProductService, IProduct, ICartItem } from '../product.service';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: IProduct[] = []; 
  filteredProducts: IProduct[] = [];
  searchControl = new FormControl('');
  added: boolean = false;
  productQuantities: { [key: number]: number } = {}; // Track quantities by product ID

  private productService = inject(ProductService);

  ngOnInit(): void {
    console.log('ProductsComponent: Initializing component');

    localStorage.removeItem('cart');

    this.productService.getProducts().subscribe({
      next: (data: IProduct[]) => {
        console.log('ProductsComponent: Products received', data);

        this.products = data;
        this.filteredProducts = data;

        // Initialize quantities for each product
        this.products.forEach(product => {
          this.productQuantities[product.id] = 1; // Default to 1 or any default quantity
        });
      },
      error: (err) => {
        console.error('ProductsComponent: Error occurred while fetching products', err);
      }
    });

    console.log('ProductsComponent: Fetching products initiated');

    // Implement search functionality
    this.searchControl.valueChanges.pipe(
      debounceTime(300) // Wait for 300ms after the last keystroke before applying the filter
    ).subscribe((searchTerm: string | null) => {
      console.log(searchTerm);
      const normalizedSearchTerm = searchTerm?.toLowerCase() ?? ''; // Handle null or empty string
      this.filteredProducts = this.products.filter(product => 
        product.name.toLowerCase().includes(normalizedSearchTerm)
      );
    });
  }

  increaseQuantity(productId: number): void {
    if (this.productQuantities[productId] !== undefined) {
      this.productQuantities[productId]++;
    }
  }

  decreaseQuantity(productId: number): void {
    if (this.productQuantities[productId] > 1) {
      this.productQuantities[productId]--;
    }
  }

  addToCart(product: IProduct): void {
    const quantity = this.productQuantities[product.id] || 1;

    const cartItem: ICartItem = {
      ...product,
      quantity: quantity
    }
    
    this.added = true;
    setTimeout(() => {
      this.added = false;
    }, 1000);

    let cart = JSON.parse(localStorage.getItem('cart') || '[]');
    cart.push(cartItem);
    localStorage.setItem('cart', JSON.stringify(cart));
    console.log(`Product added to cart: ${product.name}`);
  }
}
