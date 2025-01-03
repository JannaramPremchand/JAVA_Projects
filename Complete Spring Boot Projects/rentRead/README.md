# RentRead - Book Rental API

RentRead is a simple RESTful API built using **Spring Boot** to manage an online book rental system. It uses **MySQL** to persist data and implements basic authentication and authorization features for user and admin roles.

## Key Features

- **User Registration & Login**: Users can register by providing their email, password, first name, last name, and role (defaults to "USER"). Passwords are hashed using BCrypt.
  
- **Book Management**: 
  - Users can browse all available books.
  - Admins can create, update, and delete books.

- **Rental Management**:
  - Users can rent books, but can have a maximum of two active rentals at a time.
  - Users can return rented books.
  
- **Authentication & Authorization**: 
  - Basic Authentication is used to secure the API.
  - Two roles: `USER` and `ADMIN`. 
  - Public endpoints: Registration, Login.
  - Private endpoints: Book management and rentals (restricted based on roles).

## API Endpoints
### Postman Collection

### Public Endpoints

#### User Registration
`POST /api/auth/register`

- **Request Body**:
    ```json
    {
        "email": "testuser@example.com",
        "password": "password123",
        "firstName": "Prem",
        "lastName": "Chand"
    }
    ```

- **Response**:
    ```json
    {
        "message": "User registered successfully."
    }
    ```

#### User Login
`POST /api/auth/login`

- **Request Body**:
    ```json
    {
        "email": "testuser@example.com",
        "password": "password123"
    }
    ```

- **Response**:
    ```json
    {
        "token": "your_jwt_token_here"
    }
    ```

### Private Endpoints

#### Get All Books
`GET /api/books`

- **Response**:
    ```json
    [
        {
            "id": 1,
            "title": "Book Title 1",
            "author": "Author 1",
            "genre": "Fiction",
            "availabilityStatus": "AVAILABLE"
        }
    ]
    ```

#### Create Book (Admin Only)
`POST /api/books`

- **Request Body**:
    ```json
    {
        "title": "New Book Title",
        "author": "New Author",
        "genre": "Science Fiction",
        "availabilityStatus": "AVAILABLE"
    }
    ```

- **Response**:
    ```json
    {
        "message": "Book created successfully."
    }
    ```

#### Update Book (Admin Only)
`PUT /api/books/{id}`

- **Request Body**:
    ```json
    {
        "title": "Updated Book Title",
        "author": "Updated Author",
        "genre": "Fantasy",
        "availabilityStatus": "AVAILABLE"
    }
    ```

- **Response**:
    ```json
    {
        "message": "Book updated successfully."
    }
    ```

#### Delete Book (Admin Only)
`DELETE /api/books/{id}`

- **Response**:
    ```json
    {
        "message": "Book deleted successfully."
    }
    ```

#### Rent a Book (User Only)
`POST /api/rentals`

- **Request Body**:
    ```json
    {
        "bookId": 1
    }
    ```

- **Response**:
    ```json
    {
        "message": "Book rented successfully."
    }
    ```

#### Return a Book (User Only)
`DELETE /api/rentals/{rentalId}`

- **Response**:
    ```json
    {
        "message": "Book returned successfully."
    }
    ```

## Database Schema

### Users Table
| Field        | Type         | Description                            |
|--------------|--------------|----------------------------------------|
| id           | INT (Auto Increment) | Unique identifier for each user         |
| email        | VARCHAR(255) | Unique email address                   |
| password     | VARCHAR(255) | Hashed password                        |
| first_name   | VARCHAR(255) | User's first name                      |
| last_name    | VARCHAR(255) | User's last name                       |
| role         | ENUM('USER', 'ADMIN') | User's role (defaults to 'USER')        |

### Books Table
| Field          | Type          | Description                            |
|----------------|---------------|----------------------------------------|
| id             | INT (Auto Increment) | Unique identifier for each book         |
| title          | VARCHAR(255)  | Book title                             |
| author         | VARCHAR(255)  | Book author                            |
| genre          | VARCHAR(255)  | Genre of the book                      |
| availability   | BOOLEAN       | Book availability (True if available)  |

### Rentals Table
| Field          | Type          | Description                            |
|----------------|---------------|----------------------------------------|
| id             | INT (Auto Increment) | Unique identifier for each rental        |
| user_id        | INT           | User renting the book                  |
| book_id        | INT           | Book being rented                      |
| rent_date      | DATETIME      | Date when the book was rented          |
| return_date    | DATETIME      | Date when the book was returned (nullable) |

## Setup Instructions

### Prerequisites

- Java 11 or later
- MySQL Database
- Gradle

### Running Locally

1. Clone this repository:
   ```bash
   git clone https://github.com/jannarampremchand/rentread.git
   cd rentread
   ```

2. Set up your MySQL database and update the `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/rentread
   spring.datasource.username=root
   spring.datasource.password=root
   ```

3. Build the project:
   ```bash
   gradle build
   ```

4. Run the application:
   ```bash
   gradle bootRun
   ```

The application will be available at `http://localhost:8080`.

## Authentication

- Authentication is done via Basic Auth and JWT.
- For login, use the registered email and password. The token returned on successful login must be included in the header for all private endpoints.

## Testing

Basic unit tests have been created using **MockMvc** and **Mockito**. To run tests:

```bash
gradle test
```


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to open issues for any bugs or feature requests. Happy coding!
```

This updated README includes the specific API endpoints for both public and private routes, complete with request and response formats. You can modify the URLs and example content to fit the actual values used in your project.