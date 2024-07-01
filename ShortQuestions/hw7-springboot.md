## explain how the below annotaitons specify the table in database?
```
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
```
First @Column will create the `name` column as a VARCHAR with maximum length of 255. It uses John Snow as default value if the name is not specified.  
Second @Column maps String `studentName` to column named `STUDENT_NAME` in the database. the `studentName` should be maximum of 50 characters and cannot be null. However, it allows duplicate values.

## What is the default column names of the table in database for @Column?
```
@Column
private String firstName;
@Column
private String operatingSystem;
```
The default colume names are `firstName` and `operatingSystem`.

## What are the layers in spring boot application?what is the role of each layer?
The Spring Boot is built on top of the core Spring framework. It is a simplified and automated version of the spring framework. The spring boot follows a layered architecture in which each layer communicates to other layers.
1. **Presentation Layer** 
The presentation layer is the top layer of the spring boot architecture. It consists of Views, which is the front-end part of the application. It handles the HTTP requests and performs authentication. It is responsible for converting the JSON field’s parameter to Java Objects and vice-versa. Once it performs the authentication of the request it passes it to the next layer - the business layer.
2. **Business Layer**
The business layer contains all the business logic. It consists of services classes. It is responsible for validation and authorization.
3. **Persistence Layer**
The persistence layer contains all the database storage logic. It is responsible for converting business objects to the database row and vice-versa.
4. **Database Layer**
The database layer contains all the databases such as MySql, MongoDB, etc. This layer can contain multiple databases. It is responsible for performing the CRUD operations.

## Describe the flow in all of the layers if an API is called by Postman.
Postman makes an HTTP request(GET, PUT, POST, DELETE, etc). The HTTP request is forwarded to the Controller. The Controller maps the request. It processes the handles and calls the server logic. The business logic is performed in the Service layer. The spring boot performs all the logic over the data of the database which is mapped to the spring boot model class through Java Persistence Library. The JSP page is returned as Response from the controller.

## What is the application.properties? Do you know application.yml?
application.properties file is used to write the application-related property into that file. This file contains the different configuration which is required to run the application in a different environment, and each environment will have a different property defined by it. Inside the application properties file, we define every type of property like changing the port, database connectivity, connection to the eureka server, and etc..  
The application.properties file sometimes can be hart to read. YAML is a superset of JSON. By using application.yml file, it is more convenient for specifying hierarchical configuration data and more readable.