# My Repath Project

Simple Spring Java 8 Application developed at request of Repath.

## Description

Spring Boot (2.4.2) REST application with the purpose of performing CRUD operations on a User Entity.
Uses an H2 in-memory database to store data. Testing was done utilizing Junit4 as well as Mockito.

## Getting Started

### Installing

* Clone this github repository to any PC.
* Open cloned project with any Java-compatible IDE.

### Executing program

* Run main method of application through RepathprojectApplication class.
* Navigate to repathproject/postman-collection/RepathProject.postman_collection.json and add
this postman request collection to your Postman.
* Note that some test data are automatically loaded to the configured database at application startup and 
can be immediately accessed.
* Execute GetAllUsers request to access endpoint responsible for retrieving all users.
* Execute GetUserByCompany request to access endpoint responsible for retrieving all users based on company.
* Execute SaveUser request to access endpoint responsible for saving a new user to the database.
* Execute DeleteUser request to access endpoint responsible for deleting a user from the database.
* Execute UpdateUser request to access endpoint responsible for updating a user in the database.
* Feel free to issue any additional requests to create, read, update or delete users.

## Authors

ex. [Odisseas Korovesis-Danon](https://www.linkedin.com/in/odisseas-korovesis-danon/)