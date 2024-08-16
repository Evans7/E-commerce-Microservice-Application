import { Component, inject, OnInit } from '@angular/core';
import { ProductService, IProduct, ICartItem} from '../product.service';
import { NgIf, NgFor  } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [NgIf, NgFor ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  cartItems: ICartItem[] = []; 
  submitted: boolean = false;

  private productService = inject(ProductService);

  ngOnInit(): void {
    this.loadCartItems();
  }

  // Method to load cart items from localStorage
  loadCartItems(): void {
    const storedCart = localStorage.getItem('cart');
    if (storedCart) {
      this.cartItems = JSON.parse(storedCart);
    } else {
      this.cartItems = [];
    }
  }

  placeOrder(): void {

   
    const order = this.cartItems.map(item => 
      {return {productId: item.id, quantity: item.quantity}});


      this.productService.placeOrder(order).subscribe(
        response => {
          console.log('Order placed successfully!', response);
  

    this.submitted = true;
    setTimeout(() => {
      this.submitted = false;
    }, 2000);
         // Clear the cart
    localStorage.removeItem('cart');
    this.cartItems = [];
     // Display a confirmation message or navigate to a confirmation page
     console.log('Order placed successfully!', order);
      },
      error => {
        console.error('Error adding product', error);
      }
    );
  
   
   
    
  }
}
