# spring-boot-rest-api
###### Bookstore REST API with JWT authentication

### Prerequisites
- JDK 1.8+  
- Maven

### Technology stack:
* Spring Boot
* Spring Data JPA
* MySQL
* Spring Security
* JWT (JSON Web Tokens)
* PayPal Api

There are three user accounts :


```
/auth - authentication endpoint (HTTP method: POST) - place your credentials in JSON format in request body as JwtAuthenticationRequest 

Use Bearer Token for any listed request:
/admin/** - Operations available only for users with admin role.
/cart/** - endpoint for CRUD operations on cart (add to cart, delete etc.)
/checkout - payment transaction
/checkout/success - payment transaction success - this page will be return after successed payment.
/books/** - Crud operations on books
```


### Set up MySQL
Configure database according to [application.properties](https://github.com/Kvark900/spring-boot-rest-api/blob/master/src/main/resources/application.properties) file, or update this file with yours properties.
