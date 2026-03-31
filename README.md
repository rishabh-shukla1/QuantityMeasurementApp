---

#  Quantity Measurement App

##  Project Overview

The **Quantity Measurement App** is a Spring Boot backend application that allows users to perform **unit conversions and arithmetic operations** across multiple measurement categories like **Length, Weight, Volume, and Temperature**.

This project is designed using **clean architecture principles (N-Tier)** and demonstrates strong backend concepts like **OOP, Generics, REST APIs, Security, and Database Integration**.

---

##  Key Features

###  Measurement Operations

*  Compare quantities (e.g., 1 feet = 12 inches)
*  Unit conversion (feet ↔ inches, kg ↔ grams, etc.)
*  Arithmetic operations:

  * Addition
  * Subtraction
  * Division

###  Multi-Category Support

*  Length (Feet, Inch, Yard, etc.)
*  Weight (Kg, Gram)
*  Volume
*  Temperature (with controlled operations)

###  Backend Capabilities

*  JWT Authentication
*  Google OAuth Login
*  Database Integration (JPA + MySQL/H2)
*  RESTful APIs
*  Layered Architecture (Controller → Service → Repository)

---

##  Tech Stack

### Backend

* Java jdk
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* OAuth 2.0 (Google Login)

### Database

* H2 (Development)
* MySQL (Production)

### Tools

* Maven
* Lombok

---

##  Project Structure

```
src/main/java/com/app/quantitymeasurement/

├── controller/        # REST APIs
├── service/           # Business logic
├── repository/        # Database layer
├── entity/            # JPA entities
├── dto/               # Request/Response objects
├── model/             # Core quantity logic (units, conversions)
├── security/          # JWT & OAuth configuration
├── exception/         # Custom exceptions
├── config/            # Spring configuration
└── util/              # Utility classes
```

---

##  How to Run the Project

###  Prerequisites

* Java jdk
* Maven
* MySQL (optional)

---

###  Steps

1. Clone the repository

```bash
git clone https://github.com/vanshikaraghav/QuantityMeasurementApp.git
cd QuantityMeasurementApp
```

2. Build the project

```bash
mvn clean install
```

3. Run the application

```bash
mvn spring-boot:run
```

4. Open in browser

```
http://localhost:8080
```

5. H2 Console (if enabled)

```
http://localhost:8080/h2-console
```
## Use Case (UC) Breakdown
### UC1 – Feet Equality

Implemented the basic functionality to compare two quantities measured in feet.
This ensured that values with the same unit could be validated for equality.
It served as the foundation for building more complex unit comparisons.

### UC2 – Inch Equality

Extended the equality comparison logic to support inch measurements.
Allowed accurate comparison between quantities expressed in inches.
Helped generalize the equality mechanism beyond a single unit type.

### UC3 – Generic Length Equality

Introduced a generic approach to compare different length units like feet and inches.
Handled internal conversion before performing equality checks.
Improved flexibility by allowing cross-unit comparisons within the same category.

### UC4 – Yard Equality

Added support for yard as a new unit in the length category.
Integrated yard into the existing comparison and conversion logic.
Ensured seamless equality checks across feet, inches, and yards.

### UC5 – Unit Conversion

Implemented logic to convert values between compatible units.
Used predefined conversion factors to maintain accuracy.
Enabled operations like feet to inches and vice versa.

### UC6 – Addition of Quantities

Developed functionality to add two quantities of the same category.
Handled automatic conversion to a common unit before performing addition.
Ensured accurate results irrespective of input units.

### UC7 – Target Unit Addition

Enhanced addition operation by allowing results in a specified target unit.
Converted both operands into the desired unit before computing the result.
Improved usability by giving control over output format.

### UC8 – Standalone Units

Refactored the system to treat units as independent components.
Improved modularity and reduced tight coupling between units.
Made the system easier to extend with new measurement types.

### UC9 – Weight Measurement

Introduced weight measurement category including kilograms and grams.
Enabled comparison, conversion, and arithmetic operations for weight units.
Extended system capability beyond length measurements.

### UC10 – Generic Quantity Class

Designed a reusable generic quantity class using interfaces.
Allowed different unit types to be handled in a unified way.
Improved code reusability and type safety across categories.

### UC11 – Volume Measurement

Added support for volume units and their respective operations.
Enabled conversion and comparison within the volume category.
Further expanded the system to handle multiple measurement domains.

### UC12 – Subtraction & Division

Implemented subtraction and division operations for quantities.
Ensured operations are performed only on compatible unit types.
Handled conversions internally to maintain accuracy.

### UC13 – Centralized Logic

Refactored arithmetic operations into a centralized logic layer.
Reduced code duplication and improved maintainability.
Followed DRY (Don't Repeat Yourself) principles effectively.

### UC14 – Temperature Handling

Introduced temperature measurement with special conversion logic.
Restricted arithmetic operations where not logically valid.
Handled edge cases like Celsius to Fahrenheit conversion.

### UC15 – N-Tier Architecture

Structured the application into Controller, Service, and Repository layers.
Ensured proper separation of concerns across the system.
Improved scalability, readability, and maintainability of the codebase.

### UC16 – Database Integration

Integrated database support using Spring Data JPA.
Enabled persistent storage of measurement data.
Supported both H2 (development) and MySQL (production).

### UC17 – Spring Framework Integration

Leveraged Spring Boot features for dependency injection and configuration.
Simplified application setup and reduced boilerplate code.
Improved overall development efficiency and structure.

### UC18 – OAuth Authentication

Implemented secure authentication using JWT and Google OAuth.
Allowed users to log in via external authentication provider.
Enhanced application security and user management capabilities.

---

##  API Endpoints

###  Authentication

| Method | Endpoint                   | Description                  |
| ------ | -------------------------- | ---------------------------- |
| POST   | `/api/auth/login`          | Login with username/password |
| GET    | `/api/auth/google-success` | Google OAuth login           |

---

###  Quantity APIs

| Method | Endpoint                 | Description            |
| ------ | ------------------------ | ---------------------- |
| POST   | `/api/quantity/compare`  | Compare two quantities |
| POST   | `/api/quantity/add`      | Add quantities         |
| POST   | `/api/quantity/subtract` | Subtract quantities    |
| POST   | `/api/quantity/divide`   | Divide quantities      |
| POST   | `/api/quantity/convert`  | Convert units          |
| GET    | `/api/quantity/all`      | Get all records        |
| GET    | `/api/quantity/{id}`     | Get by ID              |
| DELETE | `/api/quantity/{id}`     | Delete record          |

---

##  Sample Request

### Compare Quantities

```json
{
  "q1": { "value": 1, "unit": "FEET" },
  "q2": { "value": 12, "unit": "INCH" }
}
```

---

### Convert Units

```json
{
  "value": 100,
  "fromUnit": "CENTIMETER",
  "toUnit": "METER"
}
```

---

##  Concepts Used

* Object-Oriented Programming (OOP)
* Generics in Java
* Interface-based design
* REST API design
* Dependency Injection (DI)
* Spring Security (JWT + OAuth)
* JPA & ORM Mapping

---

##  Challenges Solved

* Designing a **generic unit system** for multiple categories
* Handling **accurate unit conversions**
* Ensuring **type-safe arithmetic operations**
* Implementing **secure authentication flow**
* Structuring project using **clean architecture**

---

## Author
Rishabh Shukla


## 👩‍💻 Author

**Vanshika Raghav**
