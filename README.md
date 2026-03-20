# WorkTrack

A production-grade Employee Management REST API built with Spring Boot.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Features
- Create, Read, Update employees
- Soft delete (status = INACTIVE)
- Pagination support
- Global exception handling
- DTO pattern

## API Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| POST | /employees | Create employee |
| GET | /employees/{id} | Get employee by ID |
| GET | /employees?page=0&size=10 | Get all employees |
| PUT | /employees/{id} | Update employee |
| DELETE | /employees/{id} | Soft delete employee |
