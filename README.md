# Recipe RESTful Secured API

This is my first attempt at using Spring Framework to build JVM-based web services. The API lets users create, read, update, and delete Recipes. The Recipes are stored locally on H2 database.

This API uses the following features to achieve its task:

* Java JPA and Hibernate to work with persistent data.
* Spring Components such as Controllers, Services, and Repositories.
* Spring Security for controlling access to the API endpoints.
* H2 Database for local storage of data.

This project is part of Java Backend Development track as offered by JetBrains Academy.

## Project Description (Per JB Academy)

Imagine a service that supports the registration process, can handle a lot of users, and each of them can add their own recipes. Also, a user can update or delete only their recipes but can view recipes added by other users. In this stage, you will implement all this functionality with Spring Boot Security.

The stage is divided into 3 steps. In the first step, you need to add an endpoint responsible for the user registration. The endpoint receives 2 fields: email and password. The second step is to enable Spring Security and configure the access restrictions – only the registered users with the correct login and password should have the rights to use the service. After that, restrict the deletion and updating to the recipe author only.
Objectives


## Last Stage Objectives

The service should contain all features from the previous stages. To complete the project, you need to add the following functionality:

* New endpoint `POST /api/register` receives a JSON object with two fields: `email (string)`, and `password (string)`. If a user with a specified email does not exist, the program saves (registers) the user in a database and responds with `200 (Ok)`. If a user is already in the database, respond with the `400 (Bad Request)` status code. Both fields are required and must be valid: email should contain `@` and `.` symbols, password should contain at least 8 characters and shoudn't be blank. If the fields do not meet these restrictions, the service should respond with `400 (Bad Request)`. Also, do not forget to use an encoder before storing a password in a database. BCryptPasswordEncoder is a good choice.
* Include the Spring Boot Security dependency and configure access to the endpoints – all implemented endpoints (except `/api/register`) should be available only to the registered and then authenticated and authorized via HTTP Basic auth users. Otherwise, the server should respond with the `401 (Unauthorized)` status code.
* Add additional restrictions – only an author of a recipe can delete or update a recipe. If a user is not the author of a recipe, but they try to carry out the actions mentioned above, the service should respond with the `403 (Forbidden)` status code.




