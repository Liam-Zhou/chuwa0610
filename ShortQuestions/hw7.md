# Chuwa Homework& MingHao(Howard) Lee 

# Homework part 1: Postman practice

## Practice with below examples (You can find any Open APIs on the internet):
 - ## 5 GET APIs with different response type
 - API: JSONPlaceholder (Fake Online REST API for Testing)
 - Endpoint: https://jsonplaceholder.typicode.com/posts/1
 - Status: 200 OK 
 - responce:
   ```JSON
      {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit\nsuscipit..."
      }

   ```

 - API: United States Geological Survey (USGS) Earthquake Data
 - Endpoint: [https://jsonplaceholder.typicode.com/posts/1](https://earthquake.usgs.gov/fdsnws/event/1/query?format=xml&starttime=2024-06-01&endtime=2024-06-02&minmagnitude=5)
 - Status: 200 OK 
 - responce:
   ```XML
      <?xml version="1.0" encoding="UTF-8"?>
      <q:quakeml xmlns="http://quakeml.org/xmlns/bed/1.2" xmlns:anss="http://anss.org/xmlns/event/0.1" xmlns:catalog="http://anss.org/xmlns/catalog/0.1" xmlns:q="http://quakeml.org/xmlns/quakeml/1.2">
          <eventParameters publicID="quakeml:earthquake.usgs.gov/fdsnws/event/1/query?format=xml&amp;starttime=2024-06-01&amp;endtime=2024-06-02&amp;minmagnitude=5">
              <event catalog:datasource="us" catalog:eventsource="us" catalog:eventid="7000mpge" publicID="quakeml:earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000mpge&amp;format=quakeml">
                  <description>
                      <type>earthquake name</type>
                      <text>40 km ESE of Ransiki, Indonesia</text>
                  </description>
                  <origin catalog:datasource="us" catalog:dataid="us7000mpge" catalog:eventsource="us" catalog:eventid="7000mpge" publicID="quakeml:earthquake.usgs.gov/product/origin/us7000mpge/us/1718971585040/product.xml">
                      <time>
                          <value>2024-06-01T11:01:43.411Z</value>
                      </time>
                      <longitude>
                          <value>134.5006</value>
                      </longitude>
                      <latitude>
                          <value>-1.6626</value>
                      </latitude>
                      <depth>
                          <value>36038</value>
                          <uncertainty>7525</uncertainty>
                      </depth>
                      <originUncertainty>
                          <horizontalUncertainty>9570</horizontalUncertainty>
                          <preferredDescription>horizontal uncertainty</preferredDescription>
                      </originUncertainty>
                      <quality>
                          <usedPhaseCount>38</usedPhaseCount>
                          <usedStationCount>38</usedStationCount>
                          <standardError>0.34</standardError>
                          <azimuthalGap>72</azimuthalGap>
                          <minimumDistance>2.573</minimumDistance>
                      </quality>
                      <evaluationMode>manual</evaluationMode>
                      <creationInfo>
                          <agencyID>us</agencyID>
                          <creationTime>2024-06-21T12:06:25.040Z</creationTime>
                      </creationInfo>
                  </origin>
                  <magnitude catalog:datasource="us" catalog:dataid="us7000mpge" catalog:eventsource="us" catalog:eventid="7000mpge" 
              </event>
              <creationInfo>
                  <creationTime>2024-07-01T22:35:45.000Z</creationTime>
              </creationInfo>
          </eventParameters>
      </q:quakeml>

   ```

 - API: JSONPlaceholder (Fake Online REST API for Testing)
 - Endpoint: [https://jsonplaceholder.typicode.com/posts/1](https://jsonplaceholder.typicode.com/posts/999999)
 - Status: 404 Error
 - responce:
 ```JSON
   {
     "error": "Not Found"
   }
 ```
 - API: httpbin (Simple HTTP Request & Response Service)
 - Endpoint: https://httpbin.org/basic-auth/user/passwd
 - Status: 401 Unauthorized
 - responce:
   ```HTML
     <html>
       <head>
         <title>401 Authorization Required</title>
       </head>
       <body>
         <h1>401 Authorization Required</h1>
         <p>This server could not verify that you are authorized to access the document requested.</p>
       </body>
     </html>

   ```
 - API: httpstat.us (Simple HTTP Request & Response Service)
 - Endpoint: https://httpstat.us/500
 - Status: 500 Internal Server Error
 - responce:
 ```ARDUINO
   500 Internal Server Error
 ```

   
 - ## 5 Post API with json request body, please also paste the response here  
 - API: Reqres (Fake Online REST API for Testing)
 - Endpoint: https://reqres.in/api/users
 - Status: 201 Created
 - Request Body : 
 ```JSON
  {
  "name": "Howard",
  "job": "leader"
  }
 ```

 ```JSON
  {
  "name": "Howard",
  "job": "leader",
  "id": "123",
  "createdAt": "2024-07-01T14:28:55.123Z"
  }

 ```

   - API: httpbin (Simple HTTP Request & Response Service)
   - Endpoint: [https://reqres.in/api/users](https://httpbin.org/post)
   - Status: 201 Created
   - Request Body : 
 ```JSON
   {
     "name": "Howard",
     "email": "howard@example.com"
   }

 ```

 ```JSON
   {
     "args": {},
     "data": "{\"name\": \"Howard\", \"email\": \"howard@example.com\"}",
     "files": {},
     "form": {},
     "headers": {
       "Content-Type": "application/json",
       "Host": "httpbin.org"
     },
     "json": {
       "name": "Howard",
       "email": "howard@example.com"
     },
     "url": "https://httpbin.org/post"
   }
 ```

   - API: JSONPlaceholder (Fake Online REST API for Testing)
   - Endpoint: https://jsonplaceholder.typicode.com/posts
   - Status: 201 Created
   - Request Body : 
 ```JSON
   {
     "title": "foo",
     "body": "bar",
     "userId": 1
   }
 ```

 ```JSON
   {
     "title": "foo",
     "body": "bar",
     "userId": 1,
     "id": 101
   }
 ```

   - API: DummyJSON (Fake Online REST API for Testing)
   - Endpoint: https://dummyjson.com/products/add
   - Status: 201 Created
   - Request Body : 
 ```JSON
   {
    "title": "Howard's iPhone 15",
    "description": "An apple mobile which is nothing like apple",
    "price": 1549,
    "brand": "Apple",
    "category": "smartphones"
   }

 ```

 ```JSON
   {
     "id": 101,
     "title": "Howard's iPhone 15",
     "description": "An apple mobile which is nothing like apple",
     "price": 1549,
     "brand": "Apple",
     "category": "smartphones"
   }

 ```

   - API: PunkAPI (Open Source API for Brewdog's DIY Dog)
   - Endpoint: https://dummyjson.com/products/add)](https://api.punkapi.com/v2/beers
   - Status: 404 Error
   - Request Body : 
 ```JSON
   {
     "name": "Howard's Beer",
     "tagline": "Best Beer",
     "first_brewed": "07/2024",
     "description": "A test beer"
   }

 ```

 ```JSON
   {
     "statusCode": 404,
     "error": "Not Found",
     "message": "Resource not found"
   }

 ```

 - ## 3 PUT API with json request body, please also paste the response here
   - API: JSONPlaceholder (Fake Online REST API for Testing)
   - Endpoint: https://jsonplaceholder.typicode.com/posts/1
   - Status: 200 OK
   - Request Body : 
 ```JSON
   {
      "id": 1,
      "title": "Howard's Updated Title",
      "body": "This is the updated body",
      "userId": 1
   }

 ```

 ```JSON
  {
    "id": 1,
    "title": "Howard's Updated Title",
    "body": "This is the updated body",
    "userId": 1
  }
```



   - API: Reqres (Fake Online REST API for Testing)
   - Endpoint: https://reqres.in/api/users/2
   - Status: 401 Unauthorized
   - Request Body : 
 ```JSON
   {
     "name": "Howard",
     "job": "zion resident"
   }

 ```

 ```JSON
   {
     "name": "Howard",
     "job": "zion resident"
   }
 ```

   - API: httpbin (Simple HTTP Request & Response Service)
   - Endpoint: https://httpbin.org/status/500
   - Status: 500 Internal Server Error
   - Request Body : 
 ```JSON
   {
     "name": "Howard",
     "email": "howard@example.com"
   }


 ```

 ```JSON
   {
     "status": 500,
     "error": "Internal Server Error"
   }
 ``` 
 - ## 2 DELETE API
 - API: JSONPlaceholder (Fake Online REST API for Testing)
 - Endpoint: https://reqres.in/api/users/2
 - Status: 200 OK

 - API: Reqres (Fake Online REST API for Testing)
 - Endpoint: https://jsonplaceholder.typicode.com/posts/1
 - Status: 204 No Content
   
 - ## Each example with 404, 401,500 and any http status codes you know
   - 404 Not found: The server cannot find the requested resource.
   - 401 Unauthorized: The request requires user authentication.
   - 500 Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.
   - 204 No Content: The server successfully processed the request, but there's no content to return.
  
# Part 2 SpringBoot Post 
## 1. Create a file to list all of the annotations you learned and known, and explain the usage and how do you understand it.

   ### 1. File name: annotations.md
   ### 2. you'dbetter also list a code example under the annotations.
   - refer to the annotaion md
## 2. explainhowthebelowannotaitonsspecifythetableindatabase?

```JAVA
   @Column(columnDefinition = "varchar(255) default 'John Snow'")
   private String name;
   @Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
   private String studentName;
```
- `@Column(columnDefinition = "varchar(255) default 'John Snow'")`: This annotation specifies that the `name` column will be of type `varchar` with a length of 255 characters and a default value of 'John Snow'.
- `@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)`: This annotation specifies that the `studentName` column will be named `STUDENT_NAME` in the database, will have a length of 50 characters, cannot be null, and is not unique.

## 3. What is the default column name of the table in the database for `@Column`?
-  The default column name for @Column is the name of the field in the Java class.

## 4. What are the layers in a Spring Boot application? What is the role of each layer?
- ## Layers:
- Controller Layer: Manages incoming HTTP requests, invokes the service layer, and returns responses.
- Service Layer: Contains business logic and processes data between the controller and repository layers.
- Repository Layer: Manages database operations using Spring Data JPA or other data access frameworks.
- Model Layer: Represents the data and entities of the application.

## 5. Describe the flow in all of the layers if an API is called by Postman.
- ## Flow:
- Client (Postman) sends an HTTP request to the Spring Boot application.
- Controller Layer receives the request, processes it, and calls the appropriate service method.
- Service Layer contains business logic and may call multiple repository methods to perform operations.
- Repository Layer interacts with the database to perform CRUD operations.
- Service Layer processes the data from the repository and sends it back to the controller.
- Controller Layer sends the final response back to the client (Postman).

## 6.What is `application.properties`? Do you know `application.yml`?
- `application.properties`: A configuration file in Spring Boot used to define properties such as database configuration, server port, etc.
- `application.yml`: An alternative to `application.properties`, using YAML format for defining configurations.

## 7. Create a Project, name it `mongo-blog`, write a POST API for `mongo-blog`, change the database to MongoDB.
- Refer to the coding folder: MongoBlog


