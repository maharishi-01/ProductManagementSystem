
# Product Management System

This project is a **Product Management System** built using **Spring Boot**. It provides a set of RESTful API endpoints for managing products, including features like pagination, sorting, and basic CRUD operations. The system is secured using **Basic Authentication**, and the endpoints are documented using **Swagger**.

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Project Setup](#project-setup)
3. [Security Configuration](#security-configuration)
4. [API Endpoints](#api-endpoints)
5. [How to Make Requests](#how-to-make-requests)
6. [Swagger UI](#swagger-ui)
7. [Testing with CURL](#testing-with-curl)

## Technologies Used
- **Spring Boot** - Backend framework
- **Spring Security** - For securing the API with Basic Authentication
- **Swagger** - API documentation
- **BCrypt** - For password encryption
- Follow design patterns and SOLID principles.
- Proper implemented validation and error handling mechanisms.




## Project Setup

## Step 1: Clone the Repository
To get started with this project, clone it to your local machine:

```bash
git clone https://github.com/maharishi-01/ProductManagementSystem.git
cd product-management-system
```

## Step 2: Set up the Project
Make sure you have Java 17 or above installed. You can verify your Java version with:

```bash
java -version
```

## Step 3: Build and Run the Project
You can run the project by using Maven or Gradle. If you're using Maven, you can run:

```bash
./mvnw spring-boot:run
```

The application will start running on [http://localhost:8080](http://localhost:8080).

---

## Security Configuration
The application uses Basic Authentication to secure the endpoints. The default username and password are:

- **Username:** admin
- **Password:** admin123

### Security Setup Overview
- Basic Authentication is used for accessing protected resources.
- The `SecurityConfig` class defines the security rules, disabling CSRF for simplicity in testing and securing all endpoints except for Swagger documentation and GET requests.

---

## API Endpoints

### 1. Create Product
- **Endpoint:** `POST /api/products`
- **Description:** Creates a new product.
- **Request Body Example:**
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "price": 100.00,
    "quantity": 10
  }
  ```

### 2. Get Product by ID
- **Endpoint:** `GET /api/products/{id}`
- **Description:** Retrieves a product by its ID.

### 3. Get All Products (Pagination & Sorting)
- **Endpoint:** `GET /api/products`
- **Description:** Retrieves a list of products with pagination and sorting.
- **Query Parameters:**
    - `page`: The page number (default is 0).
    - `size`: The page size (default is 5).
    - `sortBy`: The field to sort by (default is `name`).
    - `sortDir`: The sorting direction (`asc` or `desc`).

### 4. Update Product
- **Endpoint:** `PUT /api/products/{id}`
- **Description:** Updates an existing product.
- **Request Body Example:**
  ```json
  {
    "name": "Updated Product Name",
    "description": "Updated Product Description",
    "price": 120.00,
    "quantity": 15
  }
  ```

### 5. Delete Product
- **Endpoint:** `DELETE /api/products/{id}`
- **Description:** Deletes a product by its ID.

---

## How to Make Requests

### Security
To access protected endpoints, you must provide Basic Authentication credentials:

- **Username:** admin
- **Password:** admin123

### Swagger UI
You can view and interact with the API using the Swagger UI:

- **Swagger URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Swagger provides a user-friendly interface to test the API endpoints and view the API documentation.

---

## Testing with cURL
You can use `curl` commands to interact with the API. Here are some examples:

1. **Create Product (POST)**
   ```bash
   curl -u admin:admin123 -X POST http://localhost:8080/api/products        -H "Content-Type: application/json"        -d '{
           "name": "New Product",
           "description": "This is a new product.",
           "price": 99.99,
           "quantity": 50
       }'
   ```

2. **Get Product by ID (GET)**
   ```bash
   curl -u admin:admin123 http://localhost:8080/api/products/{id}
   ```

3. **Get All Products (GET with Pagination and Sorting)**
   ```bash
   curl -u admin:admin123 "http://localhost:8080/api/products?page=0&size=5&sortBy=name&sortDir=asc"
   ```

4. **Update Product (PUT)**
   ```bash
   curl -u admin:admin123 -X PUT http://localhost:8080/api/products/{id}        -H "Content-Type: application/json"        -d '{
           "name": "Updated Product",
           "description": "Updated Description",
           "price": 150.00,
           "quantity": 20
       }'
   ```

5. **Delete Product (DELETE)**
   ```bash
   curl -u admin:admin123 -X DELETE http://localhost:8080/api/products/{id}
   ```

---

## Conclusion
This Product Management System allows for basic CRUD operations and supports pagination and sorting for the products. It uses Basic Authentication for security and provides an easy-to-use Swagger UI for interacting with the API. You can use `curl` to make requests or use the Swagger UI to test the endpoints interactively.

If you encounter any issues, make sure to check the API logs for errors.
