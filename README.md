# 🏬 ArchiMart

**ArchiMart** is a full-stack eCommerce platform developed using Java and Spring Boot, backed by MySQL. It allows users to register, browse products, manage their shopping cart, and place orders. It also includes endpoints for product management and customer data control.

---

## 🚀 Features

### 👤 Customer Panel
- Customer registration & authentication
- Browse available products
- Add/update/remove products in cart
- Place orders with payment method
- View past orders

### ⚙️ Admin Panel (API-level)
- Add and update products
- Manage inventory stock (quantity reduction after order)

---

## 📸 Screenshots / Demo Links

> Replace these with real links or screenshots from your project.

- **User Interface**  
  ![Frontend Screenshot](https://via.placeholder.com/800x400?text=ArchiMart+Frontend+UI)

- **Admin Interface**  
  ![Admin Screenshot](https://via.placeholder.com/800x400?text=ArchiMart+Admin+Dashboard)

- 🔗 **Live Demo:** [https://archimart-demo.com](https://archimart-demo.com)

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot
- **Frontend:** *(To be integrated / Specify if using React/Angular/etc)*
- **Database:** MySQL
- **Build Tool:** Maven
- **API Style:** RESTful
- **Security:** *(JWT-based auth planned / optional)*

---

## ⚙️ Installation Instructions

### 💻 Prerequisites
- Java 17+
- Maven 3.6+
- MySQL Server

### 🛠 Setup

```bash
# Clone the project
git clone https://github.com/your-username/archimart.git
cd archimart

# Open the project in your preferred IDE (IntelliJ, Eclipse)

# Create MySQL database
mysql> CREATE DATABASE ARCHIMART;

# Update database credentials in application.properties (see below)

# Run the Spring Boot app
mvn spring-boot:run
```

## 🔐 Environment Configuration

Your configuration should be placed in:  
`src/main/resources/application.properties`

```properties
spring.application.name=archimart

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ARCHIMART?useSSL=false
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

# Hibernate & JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.hibernate.ddl-auto=update

```

# 📡 API Overview

## 🧑 Customer APIs

| Method | Endpoint                                           | Description                          |
|--------|----------------------------------------------------|--------------------------------------|
| POST   | `/customer-api/register`                           | Register a new customer              |
| POST   | `/customer-api/login`                              | Login with email & password          |
| PUT    | `/customer-api/customer/{email}/address/`          | Update customer's shipping address   |
| DELETE | `/customer-api/customer/{email}`                   | Delete customer account/address      |

---

## 🛒 Cart APIs

| Method | Endpoint                                                       | Description                       |
|--------|----------------------------------------------------------------|-----------------------------------|
| POST   | `/customercart-api/products`                                   | Add product(s) to cart            |
| GET    | `/customercart-api/customer/{email}/products`                 | View items in cart                |
| PUT    | `/customercart-api/customer/{email}/product/{productId}`      | Update product quantity in cart   |
| DELETE | `/customercart-api/customer/{email}/product/{productId}`      | Remove item from cart             |
| DELETE | `/customercart-api/customer/{email}/products`                 | Clear all cart items              |

---

## 📦 Product APIs

| Method | Endpoint                                     | Description                          |
|--------|----------------------------------------------|--------------------------------------|
| GET    | `/product-api/products`                      | Retrieve all available products      |
| GET    | `/product-api/product/{productId}`           | Get product by ID                    |
| POST   | `/product-api/addProduct`                    | Add a new product (Admin)            |
| PUT    | `/product-api/update/{productId}`            | Reduce quantity after order          |

---

## 🧾 Order APIs

| Method | Endpoint                                                               | Description                              |
|--------|------------------------------------------------------------------------|------------------------------------------|
| POST   | `/customerorder-api/place-order`                                       | Place an order from the cart             |
| GET    | `/customerorder-api/order/{orderId}`                                   | Get specific order details               |
| GET    | `/customerorder-api/customer/{email}/orders`                          | Get all orders by customer               |
| PUT    | `/customerorder-api/order/{orderId}/update/order-status`              | Update order status (after payment)      |
| PUT    | `/customerorder-api/order/{orderId}/update/payment-through`           | Update payment method (Credit/Debit)     |



## 🧪 Testing

Run tests using **Maven**:

```bash
mvn test

```

## 🤝 Contributing

We welcome contributions! Follow these steps:

1. **Fork** this repository.

2. **Create your feature branch**:
   ```bash
   git checkout -b feature/your-feature
	```
3. **Commit your changes:**

   ```bash
   git commit -am 'Add new feature'
   ```
4. **Push to the branch:**
   ```bash
   git push origin feature/your-feature
   ```
5. **Create a new Pull Request.**



## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 👤 Author

**Archi Thakkar**  
🔗 GitHub: [https://github.com/ArchiThakkar12/](https://github.com/ArchiThakkar12/)

