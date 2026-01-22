Here is the updated **README.md** in English, adjusted to the **MyCommerce** project naming conventions (singular endpoints, English fields, and specific Enum statuses) that we corrected during our troubleshooting.

---

# MyCommerce API 🍔

A robust REST API designed to manage e-commerce and delivery ecosystems. This system provides full control over customer data, restaurant menus, and real-time order processing workflows.

## Technologies Used

* **Java 21**: Core programming language.
* **Spring Boot 3**: Main framework for application setup and dependency management.
* **Spring Data JPA**: Data persistence abstraction.
* **H2 Database**: In-memory database for fast development and testing.
* **Lombok**: Reduces boilerplate code (Getters, Setters, Constructors).
* **Jakarta Persistence (Hibernate)**: Object-Relational Mapping (ORM).

---

## Architecture and Data Model

The API manages complex relationships to ensure business logic integrity:

* **Customer ↔ Order**: A customer can place multiple orders (1:N).
* **Restaurant ↔ Product**: Each restaurant manages its own menu (1:N).
* **Order ↔ ItemOrder**: An order consists of multiple items (1:N).
* **Product ↔ ItemOrder**: A product can be linked to multiple order items.

---

## Configuration and Execution

### 1. Initialization Settings (Important)

To ensure the `data.sql` file is executed **after** Hibernate creates the database schema, verify your `src/main/resources/application.properties`:

```properties
# Defers data.sql execution until Hibernate schema is ready
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=update

```

### 2. Running the Project

Ensure you have Maven installed and run the following command:

```bash
./mvnw spring-boot:run

```

The API will be available at: `http://localhost:8080`

---

## API Endpoints

### Customer Management

| Method | Endpoint | Description |
| --- | --- | --- |
| **POST** | `/customer` | Registers a new customer. |
| **GET** | `/customer` | Returns a list of all active customers. |
| **GET** | `/customer/{id}` | Fetches specific customer details by ID. |
| **PUT** | `/customer/{id}` | Updates registration info (name, address, etc.). |
| **DELETE** | `/customer/{id}` | Performs logical deletion (inactivation). |

### Restaurant Management

| Method | Endpoint | Description |
| --- | --- | --- |
| **POST** | `/restaurant` | Registers a new establishment. |
| **GET** | `/restaurant` | Lists all registered restaurants. |
| **GET** | `/restaurant/category/{cat}` | Filters restaurants by category (e.g., Pizza, Italian). |

### Product Management

| Method | Endpoint | Description |
| --- | --- | --- |
| **POST** | `/product` | Adds a new item to a restaurant's menu. |
| **GET** | `/product/restaurant/{id}` | Lists all products from a specific restaurant. |

### Order Workflow

| Method | Endpoint | Description |
| --- | --- | --- |
| **POST** | `/order` | Places a new order with multiple items. |
| **GET** | `/order/customer/{id}` | Retrieves a customer's order history. |
| **PATCH** | `/order/{id}/statusOrder` | Updates order status (**PENDING**, **CONFIRMED**, **DELIVERED**, **CANCELLED**). |

---

## Testing with Postman

To facilitate integration testing, a pre-configured collection is available:

1. **Locate the File**: The file is named `mycommerce.postman_collection.json` in the project root.
2. **Import**: In Postman, click **Import** and select the file.
3. **Environment**: The collection uses the `{{baseUrl}}` variable. Ensure it points to `http://localhost:8080`.

---

## Documentation

* **Visual Interface (Swagger UI)**: [http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)
* **Technical Spec (OpenAPI JSON)**: [http://localhost:8080/v3/api-docs](https://www.google.com/search?q=http://localhost:8080/v3/api-docs)

---

## Developer

**Giovanni de Carvalho** - [CLASS 2602]

Developed with JDK 21 and Spring Boot 3.2.x

Would you like me to help you add a section about the **H2 Console** access to this README?
