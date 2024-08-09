# Task Management System

## Overview

The Task Management System is a Spring Boot application designed to help with task management. This guide provides instructions on how to set up and run the application locally using Docker.

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/products/docker-desktop) (for running the application in containers)
- [Docker Compose](https://docs.docker.com/compose/install/) (for managing multi-container Docker applications)

## Setup Instructions

1. **Clone the Repository**

   Start by cloning the repository to your local machine:

   ```bash
   git clone https://github.com/Pruthvik007/task-management-system.git
   cd task-management-system
2. Create the .env File
   In the root folder of the project, create a file named .env with the following content:
```properties
MYSQL_DATABASE=tms
MYSQL_USER=user
MYSQL_PASSWORD=2512
MYSQL_ROOT_USER=root
MYSQL_ROOT_PASSWORD=2512
JWT_SECRET=fedb3c5e91f18e60a97d95de789d673b65983d1bb596b4ca81ebcb00aff0f509
```
3. Run Docker Compose
   Use Docker Compose to build and start the application along with its dependencies. Run the following command in the root folder of the project:
```
docker-compose up -d
```
4. Access the Application
   Once the application is running, you can access the Swagger UI to see the available endpoints by navigating to:
```
http://localhost:8080/swagger-ui/index.html
```
5. Stopping the Application
   To stop the application and remove the containers, run:
```
docker-compose down
```