<h1 align="center">ShopKaro</h1>
<img src="https://i.postimg.cc/mD0c26cT/logo.jpg" alt="" style="width: 80%; height:350px; margin: 0 auto; border: 2px solid darkred;">

This is an Online Shopping Application developed for ABC Company. The application is designed to allow customers to browse, purchase, and view their order details, and to allow admins to manage the products in the store.

The application consists of several modules including Login, Customer, Product, Order, and Cart modules. The Login module allows users to register, log in and log out of the application. The Customer module enables customers to add products to their cart and place orders. The Product module enables admins to search, add, remove, and update products. The Order module allows customers to view their order history, and the Cart module allows customers to view and manage the products in their cart.

The application is built using Spring Boot and follows the Model-View-Controller (MVC) architecture. It uses a relational database to store and retrieve data.

<h1>Class Design</h1>
The application uses POJO classes to represent the entities in the system. The classes include Customer, Admin, Product, Order, and Cart. The service layer is designed using interfaces, such as CustomerService and AdminService, to provide separation between the business logic and the controller layer.

Overall, this Online Shopping Application is a simple and easy-to-use platform that allows customers to shop online and admins to manage the products in the store.

# ER DIAGRAM

![ERDIAGRAM](https://user-images.githubusercontent.com/71522419/236664589-b3302ac1-f1bc-4010-9bdc-80f90c69b7d7.png)


<br/>


# Tech Stack
 # Backend
  
  1. Java Core
  2. MySql
  3. Maven
  4. SpringBoot
  
 # Frontend

  1. HTML
  2. CSS
  3. BOOTSTRAP
  4. JAVASCRIPT
# Features:-
  
  # Customer Module
  
    1. User registration and login
    2. Browse and search products
    3. Add products to cart
    4, Edit cart contents
    5. Check out and purchase products
    6. View order history and details
    
  # Product Module
  
    1. Search for products
    2. View product details and images
    3. Add new products to the database
    4. Update existing products
    5. Remove products from the database
    
  # Order Module
  
    1. Create and manage orders
    2. View order history and details
    3. Generate order confirmation emails
    4. Cart Module
    5. Add products to cart
    6. Edit cart contents
    7. View cart contents
    8. Remove products from cart
    
  # Cart Module 
  
    1. Add products to the cart
    2. Remove products from cart
    3. Update the quantity of products in the cart
    4. Remove all products from the cart
    5. View List of products from the cart 
    
    
  # Login Module
  
    1. Secure user authentication and authorization
    2. Password reset functionality
