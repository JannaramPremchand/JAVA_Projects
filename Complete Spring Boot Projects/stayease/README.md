
---

# StayEase API

StayEase API is a RESTful service designed to manage user registration, hotel details, and booking functionalities. The API is built using **Spring Boot** and utilizes **JWT** for authentication and authorization. The backend is supported by **MySQL** for data storage, and **Gradle** is used for building the application.

## Tech Stack
- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **Spring Security**: For authentication and authorization
- **Gradle**: Build automation tool
- **MySQL**: Database management system
- **Hibernate**: ORM (Object-Relational Mapping) framework

## Setup

### Prerequisites
- Java 8 or higher
- Gradle
- MySQL (local or cloud-based)
- Postman (optional, for API testing)

### Clone the Repository
Clone the repository to your local machine:

```bash
git clone https://github.com/rajatsinha05/StayEase.git
cd StayEase
```

### Configure Database
- Set up a MySQL database named `stay_ease` (or modify `application.properties` to match your database settings).
- Ensure you have the necessary credentials for the database connection.

### Build the Project

Run the following command to build the project using Gradle:

```bash
./gradlew build
```

### Running the Application

After building the project, you can run the application with the following command:

```bash
java -jar build/libs/StayEase.jar
```

The application will be accessible at `http://localhost:8080`.

## Routes

### User Management

#### Register User
- **Route**: `POST /users/register`
- **Description**: Registers a new user in the system.
- **Request Body**:
  ```json
  {
    "firstName": "Jannaram",
    "lastName": "Premchand",
    "email": "jannaram.premchand@example.com",
    "password": "password123",
    "role": "USER"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Jannaram",
    "lastName": "Premchand",
    "email": "jannaram.premchand@example.com",
    "role": "USER"
  }
  ```

#### User Login
- **Route**: `POST /users/login`
- **Description**: Authenticates a user and returns an access token.
- **Request Body**:
  ```json
  {
    "email": "jannaram.premchand@example.com",
    "password": "password123"
  }
  ```
- **Response**:
  ```json
  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
  ```

#### Get All Users
- **Route**: `GET /users`
- **Description**: Retrieves information about all users (only accessible to admins).
- **Response**:
  ```json
  [
    {
      "id": 1,
      "firstName": "Jannaram",
      "lastName": "Premchand",
      "email": "jannaram.premchand@example.com",
      "role": "USER"
    },
    {
      "id": 2,
      "firstName": "Sangita",
      "lastName": "Rawlo",
      "email": "sangita.rawlo@example.com",
      "role": "ADMIN"
    }
  ]
  ```

#### Get User By ID
- **Route**: `GET /users/{id}`
- **Description**: Retrieves information about a specific user.
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Jannaram",
    "lastName": "Premchand",
    "email": "jannaram.premchand@example.com",
    "role": "USER"
  }
  ```

#### Update User
- **Route**: `PUT /users/{id}`
- **Description**: Allows users to update their own information.
- **Request Body**:
  ```json
  {
    "firstName": "Updated First Name"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Updated First Name",
    "lastName": "Premchand",
    "email": "jannaram.premchand@example.com",
    "role": "USER"
  }
  ```

#### Delete User
- **Route**: `DELETE /users/{id}`
- **Description**: Allows users to delete their own account.

---

### Hotel Management

#### Add Hotel
- **Route**: `POST /hotels`
- **Description**: Adds a new hotel to the system.
- **Request Body**:
  ```json
  {
    "name": "Sample Hotel",
    "location": "City A",
    "rating": 4.5
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Sample Hotel",
    "location": "City A",
    "rating": 4.5
  }
  ```

#### Get All Hotels
- **Route**: `GET /hotels`
- **Description**: Retrieves information about all hotels.
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Sample Hotel",
      "location": "City A",
      "rating": 4.5
    },
    {
      "id": 2,
      "name": "Another Hotel",
      "location": "City B",
      "rating": 4.2
    }
  ]
  ```

#### Get Hotel By ID
- **Route**: `GET /hotels/{id}`
- **Description**: Retrieves information about a specific hotel.
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Sample Hotel",
    "location": "City A",
    "rating": 4.5
  }
  ```

---

## Running the Application

After setting up the environment, running the application with the following command:

```bash
java -jar build/libs/StayEase.jar
```

The application will be hosted locally on `http://localhost:8080`.

## Additional Resources

- [Spring Security: Implementing JWT Authentication](https://www.baeldung.com/security/spring-security-oauth-jwt)
- [Setting Up Applications Using Spring Initializr](https://start.spring.io/)
- [Deploying Spring Boot Applications using Render](https://render.com/docs/deploy-java-spring-boot)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---