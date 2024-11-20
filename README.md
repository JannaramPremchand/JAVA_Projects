# JAVA_Projects
This repository showcases various Java projects that I've built during my learning process. These projects demonstrate my skills in Java, Gradle, Maven and Spring Boot

## Projects Overview

### 1. **BUILDOUT_XLIDO**
**Xlido** is a robust event engagement platform inspired by Slido, designed to enhance real-time participation and interaction during events and meetings. The platform allows organizers to manage Q&A sessions efficiently, ensuring dynamic communication between organizers and attendees.
- **Key Features:**
  - User registration system with email validation, preventing duplicate accounts.
  - Event management system where organizers can create and update event details (title, description, etc.).
  - Q&A session management, allowing real-time audience interaction, where questions can be submitted and moderated.
  - Strong adherence to Object-Oriented Programming (OOP) and SOLID principles, ensuring scalability and maintainability.
  - Test-driven development, ensuring that the code is thoroughly tested for potential edge cases and ensuring robustness.
- **Skills Used:** API Development, Low-Level Design, OOP, SOLID Principles, Unit Testing.

### 2. **BUILDOUT_XNEWS**
**XNews** is a command-line news application that personalizes news feeds based on user preferences. It connects to a third-party news API to fetch and display news articles tailored to user interests, such as language preference or sorting by relevance or recency.
- **Key Features:**
  - User preference management via 'users.json', where each user's news preferences (like language and sort order) are stored.
  - Command-line interface for reading user preferences and fetching news articles from the API based on these preferences.
  - Parsing and handling of JSON responses from the news API to extract and display relevant articles.
  - Unit tests to verify the correctness of various components, including parsing and API calls.
  - A robust error-handling mechanism to deal with API failures or incorrect inputs.
- **Skills Used:** Java, REST API Integration, JSON Parsing, Command Line Interface (CLI), Unit Testing.

### 3. **BUILDOUT_XURL**
**XURL** is a URL shortening service that allows users to generate short, easy-to-share URLs from long ones, similar to services like bit.ly. The project was built from scratch, focusing on efficiency and scalability, with functionality for creating, managing, and looking up short URLs.
- **Key Features:**
  - Generate short URLs for long URLs, using a random 9-character code, and map them in a hash map for quick retrieval.
  - Custom short URLs can also be registered if desired, providing flexibility in URL management.
  - Lookup functionality allows users to retrieve long URLs from their short counterparts quickly.
  - Hit counting for each URL, tracking how often each URL has been accessed.
  - Comprehensive error handling to avoid duplicate mappings or invalid URLs.
  - Testing implemented to ensure all functionalities work as intended and edge cases are handled.
- **Skills Used:** Core Java, HashMap, Data Structures, Interfaces, Exception Handling, Unit Testing.

### 4. **GEEKTRUST_BACKEND_METROCARD**
The **MetroCard** project is a simulation of a backend system for a metro train service, focusing on travel charge management and passenger tracking. The system allows passengers to use MetroCards for non-stop journeys between two stations and calculates charges based on passenger type and journey.
- **Key Features:**
  - Travel charges vary based on the passenger's age (Adult, Senior Citizen, Kid) and whether the trip is a one-way or return journey.
  - Auto-recharge system with a 2% service fee if the balance is insufficient for travel.
  - Discounts are applied for return journeys, with a comprehensive system for tracking and calculating travel charges.
  - Generates summaries for travel charges and passenger counts per station (Central and Airport), providing detailed statistics.
  - Optimized data handling for processing multiple journeys efficiently and calculating total revenue and discounts.
- **Skills Used:** Java, Command-Line application, OOP, Data Structures, Algorithm Design.

### 5. **MACHINE_CODING_JUKEBOX**
**JukeBox** is a music player application where users can create and manage playlists from a pool of available songs. The application simulates the functionalities of popular music apps by providing a user-friendly interface to manage songs and playlists.
- **Key Features:**
  - Users can browse a pool of songs and create custom playlists.
  - Songs can be added or removed from playlists, and users can switch between songs during playback (forward/backward navigation).
  - Playlists can be played sequentially, and songs are displayed in the order they were added.
  - Test-driven approach was used to ensure the correct behavior of the playlist, song switching, and navigation features.
  - Implements SOLID principles and proper object-oriented design patterns to ensure modularity and extensibility.
- **Skills Used:** Java, OOP, SOLID Principles, Test-Driven Development, Design Patterns.

### 6. **QCALC**
**QCalc** is a subset of calculator functionalities designed to demonstrate key OOP principles. It supports basic arithmetic operations and has been extended to handle more advanced scientific functions.
- **Key Features:**
  - Basic arithmetic operations (addition, subtraction, multiplication, division) with support for method overloading to handle different data types (integers, floating point numbers).
  - Exception handling to manage division by zero and other invalid operations.
  - Unit tests cover a range of scenarios, ensuring that edge cases are handled properly.
  - Extendable design to add more advanced calculator features (scientific functions).
  - Detailed error logging for invalid inputs and operations, helping improve the user experience and debugging process.
- **Skills Used:** Java, Exception Handling, Unit Testing, OOP, Method Overloading.

### 7. **QCONTEST**
**QContest** is a simulation platform for coding contests, similar to Hackerrank. It allows users to participate in programming challenges by creating contests and managing submissions. The application emphasizes scalable architecture and robust design.
- **Key Features:**
  - Users can create coding contests, define problems, and manage participants.
  - Command and service layers strictly adhere to SOLID principles to ensure a flexible and maintainable codebase.
  - Test-driven development (TDD) was used to implement the core logic for running the contests, ensuring code quality and functionality.
  - Well-structured business logic that simulates real-time coding challenges, including score calculations and contest rankings.
  - Comprehensive unit tests to ensure contest logic and services work correctly.
- **Skills Used:** Core Java, OOP, SOLID Principles, Unit Testing, Design Patterns, Exception Handling.

### 8. **QMONEY**
**QMoney** is a stock portfolio management application designed to assist portfolio managers in making informed trading decisions. It fetches stock quotes and calculates annualized returns to help users optimize their portfolios.
- **Key Features:**
  - Fetch stock quotes using Tiingo's REST API and calculate annualized returns based on stock purchase dates and holding periods.
  - Refactored code to support multiple stock quote services, ensuring better reliability and fault tolerance.
  - Published the portfolio manager as a JAR file, allowing easy integration and versioning for future use.
  - Detailed exception handling and error reporting to ensure stability and improve the user experience during data retrieval and analysis.
  - Unit tests ensure the accuracy of return calculations and API responses, validating the overall functionality of the portfolio manager.
- **Skills Used:** Java, REST API Integration, Code Refactoring, Gradle, Exception Handling, Unit Testing.

### 9. **SQL**
This section focuses on practicing SQL aggregates and joins to analyze and query large datasets. It includes various exercises where you work with SQL aggregate functions, groupings, and joins to manipulate data effectively.

- **Key Features:**
  - Master SQL aggregate functions like 'COUNT()', 'SUM()', 'AVG()', 'MAX()', and 'MIN()' to perform computations.
  - Practice using the 'GROUP BY' clause to group data by columns and apply aggregate functions for analysis.
  - Implement different types of SQL joins, including:
    - **Inner Joins**: Combine data from multiple tables where there is a match.
    - **Left/Right Joins**: Retrieve all records from one table and matching records from another.
    - **Full Joins**: Return rows when there is a match in either of the tables.
    - **Self Joins**: Join a table with itself for data analysis.
  - Work with subqueries for nested queries and advanced data manipulations.
  
- **Skills Used:** SQL, Aggregate Functions, Joins, Subqueries, Data Analysis.

### 10. **JDBC_XPOLL_SQL_PRACTICE**
The **JDBC_XPOLL_SQL_PRACTICE** section integrates SQL queries into a Java application using JDBC. This project focuses on using SQL queries with aggregates and joins to interact with databases in the context of a poll management system.

- **Key Features:**
  - **Database Integration**: Use JDBC to connect and interact with SQL databases (e.g., MySQL, SQLite).
  - **Aggregate Functions**: Utilize SQL functions like 'COUNT()', 'SUM()', and 'AVG()' to analyze poll data, such as counting votes or calculating popular choices.
  - **Joins**: Implement various joins like 'INNER JOIN' and 'LEFT JOIN' to retrieve and analyze data from multiple tables (e.g., 'polls', 'responses', and 'users').
  - **Subqueries**: Write subqueries for advanced data filtering and manipulation.
  - **SQL Injection Prevention**: Use prepared statements to prevent SQL injection vulnerabilities.
  - **Transactions**: Implement transactions for secure and consistent data changes in polls and responses.
  - **Error Handling**: Manage database errors and handle exceptions for database integrity and smooth operation.
  
- **Skills Used:** SQL, JDBC, Aggregate Functions, Joins, Subqueries, Transactions, Data Integrity, SQL Injection Prevention.

### 11. **QEats**
**QEats** is a popular food ordering app that allows users to browse and order their favorite dishes from nearby restaurants. This project involved building and optimizing the backend of the application using Spring Boot.

- **Scope of Work:**
  - Implemented the `GET /api/v1/restaurants` endpoint along with the corresponding request handlers and response methods.
  - Used Mockito to develop the relevant MVCS layers independently.
  - Retrieved restaurant data from MongoDB based on a user's location.
  - Improved application performance under large load scenarios.
  - Added an advanced search feature to enhance the app's usability.
  - Investigated and resolved production issues using Scientific Debugging methods.

- **Skills Used:** 
  Spring Boot, Spring Data, REST API, Jackson, Mockito, JUnit, MongoDB
