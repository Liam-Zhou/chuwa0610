### 2. explain how the below annotaitons specify the table in database?

```java
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
```

- Specifies that the name field is mapped to a column with a data type of varchar(255) and a default value of 'John Snow'.
- pecifies that the studentName field is mapped to a column named STUDENT_NAME.
  The column has a maximum length of 50 characters.
  The column cannot be null (nullable=false).
  The column does not need to be unique (unique=false).

### 3. What is the default column names of the table in database for @Column ?

```java
@Column
private String firstName;
@Column
private String operatingSystem;
```

The default column names in the database would be firstName and operatingSystem,

### 4. What are the layers in springboot application? what is the role of each layer?

- Presentation Layer: Handles HTTP requests and responses. Uses @Controller or @RestController to define endpoints.
- Service Layer: Contains business logic. Uses @Service to denote service classes.
- Data Access Layer: Interacts with the database. Uses @Repository for data access operations.
- Domain Layer: Contains the core business entities. Uses @Entity to map classes to database tables.

### 5. Flow When an API is Called by Postman

- Request Handling: Postman sends a request to an endpoint defined in the @RestController.
- Service Layer: The controller calls methods in the service layer (@Service) to process the request.
- Data Access: The service layer interacts with the repository layer (@Repository) to perform CRUD operations.
- Response: Data is returned from the repository to the service, then to the controller, which sends it back as an HTTP response.

### 6. application.properties and application.yml

- application.properties: A configuration file used to set up various parameters for a Spring Boot application. It uses key-value pairs for configuration.

  ```java
  server.port=8080
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  
  ```

  

- application.yml: An alternative to application.properties that uses YAML format, which can be more readable for complex configurations.

  ```java
  server:
    port: 8080
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/mydb
  
  ```

  