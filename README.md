# Book My Show Application

This is a Spring Boot application that implements a movie ticket booking system similar to BookMyShow. The application allows users to browse movies, theaters, and shows, book tickets, and make payments.

## Features

- User registration and authentication
- Browse movies by city
- View theaters and available shows
- Select seats and book tickets
- Process payments
- View booking history

## Getting Started

### Prerequisites

- Java 21
- Gradle
- MySQL/H2 Database

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using:
   ```
   ./gradlew bootRun
   ```
4. Access the application at `http://localhost:8080`

## Architecture

The application follows a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Interfaces with the database
- **Model Layer**: Defines entity classes that map to database tables
- **DTO Layer**: Data Transfer Objects for request/response handling

## Domain Model

### Core Entities

- **User**: Represents a registered user
- **Movie**: Represents a movie
- **Theatre**: Represents a theater venue
- **Screen**: Represents a screen within a theater
- **Seat**: Represents a seat within a screen
- **Show**: Represents a movie screening at a specific time
- **ShowSeat**: Represents a seat for a specific show
- **Booking**: Represents a user's booking
- **Payment**: Represents payment information for a booking

## API Endpoints

The application exposes the following REST APIs:

### User APIs
- `POST /api/v1/users` - Register a new user
- `GET /api/v1/users/{userId}` - Get user details

### Movie APIs
- `GET /api/v1/movies` - Get all movies
- `GET /api/v1/movies/{movieId}` - Get movie details

### Theatre APIs
- `GET /api/v1/theatres` - Get all theatres
- `GET /api/v1/theatres/{theatreId}` - Get theatre details

### Show APIs
- `GET /api/v1/shows` - Get all shows
- `GET /api/v1/shows/{showId}` - Get show details
- `GET /api/v1/shows/movie/{movieId}` - Get shows by movie
- `GET /api/v1/shows/city/{cityId}` - Get shows by city

### Booking APIs
- `POST /api/v1/bookings` - Create a booking
- `GET /api/v1/bookings/{bookingId}` - Get booking details

### Payment APIs
- `POST /api/v1/payments` - Process payment
- `GET /api/v1/payments/{paymentId}` - Get payment details

## Database Configuration

The application uses H2 in-memory database for testing and can be configured to use MySQL for production.

H2 Console is available at `http://localhost:8080/h2-console` when running in development mode.

## Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.0/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.0/reference/using/devtools.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.5.0/specification/configuration-metadata/annotation-processor.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Troubleshooting

### Common Issues and Solutions

#### Entity Naming in JPQL Queries
- When using `@Entity(name = "customName")`, make sure to use the custom name in JPQL queries.
- Example: For `Show` entity with `@Entity(name = "shows")`, use `SELECT s FROM shows s` in JPQL.

#### Validation Annotations
- Use `@NotNull` instead of `@Nonnull` when you need to specify a message attribute.
- Example: `@NotNull(message = "User ID cannot be null")`

#### ShowSeatStatus Attribute
- The attribute name should follow camelCase convention: `showSeatStatus` not `ShowSeatStatus`.

#### PaymentStatus Enum
- Avoid duplicate values in the enum (e.g., SUCCESS and SUCCESSFUL).

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License.

