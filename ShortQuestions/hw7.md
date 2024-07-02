## POSTMAN Exercise

#### HTTP GET

1. **Plain Text**

- **URL**: https://baconipsum.com/api/?type=meat-and-filler

- Response

  ```json
  [
    "Bacon ipsum dolor amet pancetta andouille doner, pastrami kielbasa pig leberkas chicken meatloaf swine venison pork chop biltong. Cupim pancetta frankfurter, pork belly ribeye andouille pig biltong pork loin kielbasa."
  ]
  ```

2. **HTML**

- **URL**: https://httpbin.org/html

- Response:

  ```html
  <!DOCTYPE html>
  <html>
    <head>
      <title>Sample</title>
    </head>
    <body>
      <h1>Sample Heading</h1>
    </body>
  </html>
  ```

3. **JSON**

- **URL**: https://api.github.com/users/octocat

- Response

  ```json
  {
    "login": "octocat",
    "id": 583231,
    "node_id": "MDQ6VXNlcjU4MzIzMQ==",
    "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
    ...
  }
  ```

4. **XML**

- **URL**: https://www.w3schools.com/xml/note.xml

- Response

  ```xml
  <note>
    <to>Tove</to>
    <from>Jani</from>
    <heading>Reminder</heading>
    <body>Don't forget me this weekend!</body>
  </note>
  ```

5. **Image (JPEG)**

- **URL**: https://placekitten.com/200/300

- **Response**:

  <img src="./assets/image-20240701182357728.png" alt="image-20240701182357728" style="zoom:33%;" />



#### HTTP POST

1. **JSONPlaceholder**

- **URL**: https://jsonplaceholder.typicode.com/posts

- Request Body

  ```json
  {
    "title": "foo",
    "body": "bar",
    "userId": 1
  }
  ```

- Response

  ```json
  {
    "id": 101
  }
  ```

2. **Reqres**

- **URL**: https://reqres.in/api/users

- Request Body

  ```json
  {
    "name": "morpheus",
    "job": "leader"
  }
  ```

- Response

  ```json
  {
    "name": "morpheus",
    "job": "leader",
    "id": "483",
    "createdAt": "2024-06-29T12:34:56.789Z"
  }
  ```

3. **DummyAPI**

- **URL**: https://dummyapi.io/data/api/user/create

- Request Body

  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
  ```

- Response

  ```json
  {
    "id": "60d0fe4f5311236168a109ca"
  }
  ```

4. **Petstore Swagger**

- **URL**: https://petstore.swagger.io/v2/pet

- Request Body

  ```json
  {
    "id": 12345,
    "category": {
      "id": 1,
      "name": "Dogs"
    },
    "name": "Doggie",
    "photoUrls": [
      "string"
    ],
    "tags": [
      {
        "id": 1,
        "name": "tag1"
      }
    ],
    "status": "available"
  }
  ```

- Response

  ```json
  {
    "id": 12345,
    "category": {
      "id": 1,
      "name": "Dogs"
    },
    "name": "Doggie",
    "photoUrls": [
      "string"
    ],
    "tags": [
      {
        "id": 1,
        "name": "tag1"
      }
    ],
    "status": "available"
  }
  ```

5. **Fake Store API**

- **URL**: https://fakestoreapi.com/products

- Request Body

  ```json
  {
    "title": "test product",
    "price": 13.5,
    "description": "lorem ipsum set",
    "image": "https://i.pravatar.cc",
    "category": "electronic"
  }
  ```

- Response

  ```json
  {
    "id": 21
  }
  ```

#### PUT APIs

1. **JSONPlaceholder**

   - **URL**: https://jsonplaceholder.typicode.com/posts/1

   - Request Body

     ```json
     {
       "id": 1,
       "title": "foo",
       "body": "bar",
       "userId": 1
     }
     ```

   - Response

     ```json
     {
       "id": 1,
       "title": "foo",
       "body": "bar",
       "userId": 1
     }
     ```

2. **Reqres**

   - **URL**: https://reqres.in/api/users/2

   - Request Body

     ```json
     {
       "name": "morpheus",
       "job": "zion resident"
     }
     ```

   - Response

     ```json
     {
       "name": "morpheus",
       "job": "zion resident",
       "updatedAt": "2024-06-29T12:34:56.789Z"
     }
     ```

3. **DummyAPI**

   - **URL**: https://dummyapi.io/data/api/user/60d0fe4f5311236168a109ca

   - Request Body

     ```json
     {
       "firstName": "Jane",
       "lastName": "Doe",
       "email": "jane.doe@example.com"
     }
     ```

   - Response

     ```json
     {
       "id": "60d0fe4f5311236168a109ca",
       "firstName": "Jane",
       "lastName": "Doe",
       "email": "jane.doe@example.com"
     }
     ```

#### DELETE APIs

1. **JSONPlaceholder**

   - **URL**: https://jsonplaceholder.typicode.com/posts/1

   - Response

     ```
     {}
     ```

2. **Reqres**

   - **URL**: https://reqres.in/api/users/2

   - Response

     ```
     {}
     ```



## Spring-boot exercise

2. explain how the below annotaitons specify the table in database?
   - The field `name` is mapped to a database column with a type of `varchar(255)` and a default value of `'John Snow'`
   - The `studentName` field is stored in a column named `STUDENT_NAME`, with a length of 50 characters, not allowing `null` values, and not requiring uniqueness.
3. What is the default column names of the table in database for  `@Column `
   - when no name is explicitly specified, the default column names of the table in the database will be the same as the field names in the entity class. so it will be `firstName` and `operatingSystem`

4. What are the layers in a Spring Boot application? What is the role of each layer?

- Controller Layer (Web Layer)
  - **Role:** Handles HTTP requests and responses. It acts as an entry point for the client to interact with the application. The controller layer processes the incoming requests, delegates the request to the appropriate service methods, and returns the results back to the client.
- Service Layer
  - **Role:** Contains the business logic of the application. It processes the data received from the controller layer and interacts with the repository layer to retrieve or store data. The service layer ensures that the business rules are applied correctly.
- Repository Layer (Data Access Layer)
  - **Role:** Interacts with the database. It contains methods for performing CRUD operations and is responsible for communicating with the database using JPA or other ORM frameworks. The repository layer abstracts the data access logic.
- Model Layer (Domain Layer)
  - **Role:** Represents the data structure of the application. It includes entity classes that define the structure of the data being managed. The model layer is used across other layers to transfer data.

5. Describe the flow in all of the layers if an API is called by Postman.

​	When an API is called by Postman, the flow through the layers of a Spring Boot application is as follows:

1. **Controller Layer:**
   - Postman sends an HTTP request to a specific endpoint.
   - The request is received by a controller method mapped to that endpoint.
   - The controller processes the request and may extract any necessary parameters or request body.
2. **Service Layer:**
   - The controller delegates the request to the appropriate service method.
   - The service method contains the business logic and processes the request data.
   - If data is needed from the database or needs to be persisted, the service layer interacts with the repository layer.
3. **Repository Layer:**
   - The service layer calls the repository methods to perform CRUD operations.
   - The repository interacts with the database to retrieve, save, update, or delete data.
   - The results from the repository are returned to the service layer.
4. **Model Layer:**
   - Throughout the flow, the model layer is used to transfer data between layers.
   - The entities and data transfer objects (DTOs) define the structure of the data being passed around.
5. **Controller Layer:**
   - The service layer returns the processed data to the controller.
   - The controller constructs an appropriate HTTP response and sends it back to Postman.'





6. What is the `application.properties`? Do you know `application.yml`?

- `application.properties`
  - **Explanation:** A configuration file used in Spring Boot applications to set various properties for the application. It allows configuring application settings such as database connection details, server port, logging level, and more. The properties are defined in a key-value format.
- `application.yml`
  - **Explanation:** An alternative to `application.properties` that uses YAML format. It provides a hierarchical way of defining configuration properties, which can be more readable and easier to manage for complex configurations.

### 



