# E-commerce-Microservice-Application

This is a microservice application for an ecommerce platform. Consists of 4 services and 2 helper services.

Admin Service- Responsible for doing admin functionalites such as creating/updating and deleting product

Image Service - Responsible for uploading and deleting images from AWS S3 bucket

Order Service - Responsible for order generation and processing of orders of products

Product Service - Responsible for CRUD operations of product.

The other helper services are as follows:-

Gateway Service - Responsible for routing of endpoints and abstraction of other services

Eureka Service - Responsible for registering services for interactions between them.

UI application :-

Checkout application - A simple UI Angular application used to browse through products, add them to cart and place an order.

Steps to run the application :-

1. Run Eureka Service.
2. Run Gateway Service.
3. Run Product Service.
4. Run Image Service.
5. Run Admin Service.
6. Run Order Service.
7. cd into Frontend Service.
8. Run npm i
9. Run the UI application with ng serve.
10. Browse to the endpoint which is running the UI application.

API endpoint exposed:-

1. api/admin - For all operations on Admin service

Get All Products Endpoint:
GET /admin/product
Description: Retrieves a list of all products managed by the admin.
Response: 200 OK: Returns a list of Product objects.

Get Product by ID Endpoint:
GET /admin/product/{id}
Description: Retrieves a specific product by its ID.
Path Variable: id (Long): The ID of the product to retrieve.
Response: 200 OK: Returns the Product object.
404 Not Found: If the product with the specified ID is not found.

Create a New Product Endpoint:
POST /admin/product
Description: Creates a new product with the provided details.
Request Body: A Product object in JSON format.
Response: 200 OK: Returns the created Product object.

Update an Existing Product
Endpoint: PUT /admin/product
Description: Updates an existing product with the provided details.
Request Body: A Product object in JSON format.
Response:
200 OK: Returns the updated Product object.

Delete a Product
Endpoint: DELETE /admin/product/{id}
Description: Deletes a product by its ID and sends a message to the product-service about the deletion.
Path Variable:
id (Long): The ID of the product to delete.
Response:
204 No Content: Indicates successful deletion of the product.


2. api/products - For all operations on Product service

Endpoint: GET /products
Description: Retrieves a list of all available products.
Response:
200 OK: Returns a list of Product objects.


Endpoint: GET /products/{id}
Description: Retrieves a specific product by its ID.
Path Variable:
id (Long): The ID of the product to retrieve.
Response:
200 OK: Returns the Product object.
404 Not Found: If the product with the specified ID is not found.


Endpoint: POST /products
Description: Creates a new product with the provided details.
Request Body: A Product object in JSON format.
Consumes: multipart/form-data
Response:
200 OK: Returns the created Product object.



3. api/orders - For all operations on Order service

Endpoint: POST /orders/add
Description: Creates a new order based on the provided list of cart items.
Request Body: A list of CartItem objects in JSON format.
Response:
200 OK: Returns the created Orders object.


Endpoint: GET /orders
Description: Retrieves a list of all orders that have been placed.
Response:
200 OK: Returns a list of Orders objects.


4. api/images - For all operations on Image service

Endpoint: POST /images/upload
Description: Uploads an image file to an S3 bucket and returns the file's URL.
Request Parameter:
file (MultipartFile): The image file to be uploaded.
Response:
200 OK: Returns the URL of the uploaded image.
500 Internal Server Error: Returns an error message if the upload fails.
Example Request:
Content-Type: multipart/form-data


Endpoint: DELETE /images/delete/{image}
Description: Deletes an image from the S3 bucket by its name.
Path Variable:
image (String): The name of the image to be deleted.
Response:
200 OK: Returns a confirmation message indicating the image was deleted successfully.




