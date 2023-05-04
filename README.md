**ShopKaro**
This is an Online Shopping Application developed for ABC Company. The application is designed to allow customers to browse, purchase, and view their order details, and to allow admins to manage the products in the store.

The application consists of several modules including Login, Customer, Product, Order, and Cart modules. The Login module allows users to register, log in and log out of the application. The Customer module enables customers to add products to their cart and place orders. The Product module enables admins to search, add, remove, and update products. The Order module allows customers to view their order history, and the Cart module allows customers to view and manage the products in their cart.

The application is built using Spring Boot and follows the Model-View-Controller (MVC) architecture. It uses a relational database to store and retrieve data.

**Class Design**
The application uses POJO classes to represent the entities in the system. The classes include Customer, Admin, Product, Order, and Cart. The service layer is designed using interfaces, such as CustomerService and AdminService, to provide separation between the business logic and the controller layer.

Overall, this Online Shopping Application is a simple and easy-to-use platform that allows customers to shop online and admins to manage the products in the store.

-Tech Stack
  1. Java Core
  2. MySql
  3. Maven
  4. SpringBoot
  
  ***Features:-**
  
  **Customer Module**
    -User registration and login
    -Browse and search products
    -Add products to cart
    -Edit cart contents
    -Check out and purchase products
    -View order history and details
  **Product Module**
    -Search for products
    -View product details and images
    -Add new products to the database
    -Update existing products
    -Remove products from the database
  **Order Module**
    -Create and manage orders
    -View order history and details
    -Generate order confirmation emails
    -Cart Module
    -Add products to cart
    -Edit cart contents
    -View cart contents
    -Remove products from cart
  **Login Module**
    -Secure user authentication and authorization
    -Password reset functionality
