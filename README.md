# Location Service

Location Management Microservice built using Spring Boot and Spring Data JPA.

## Technologies Used

* Java 17
* Spring Boot 3
* Spring Data JPA
* H2 Database
* Maven
* JUnit 5
* Mockito

## Features

* Create Location
* Get Location By Id
* Input Validation
* REST APIs
* Unit Testing

## Architecture

Location Service

↓

Location Controller

↓

Location Service

↓

Location Repository

↓

H2 Database

## Running the Project

### Run Application

mvn spring-boot:run

### Swagger URL

http://localhost:8081/swagger-ui.html

## Sample Response

{
"locationId": 1,
"locationName": "Bangalore",
"city": "Bangalore",
"state": "Karnataka",
"country": "India",
"latitude": 12.9716,
"longitude": 77.5946
}
