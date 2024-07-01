## 1. `@SpringBootApplication`
This annotation is used to mark the main class of a Spring Boot application. It encapsulates @SpringBootConfiguration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.  
Ex:
```
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

## 2. `@RestController`
This annotation is used to handle REST APIs such as GET, PUT, POST, DELETE etc. and also used to create RESTful web services using Spring MVC.

It encapsulates @Controller annotation and @ResponseBody annotation with their default attributes.  
```
@RestController
public class PostController{ 
   // ...
}
```

## 3. `@RequestMapping`
This annotation is used to map the HTTP requests with the handler methods inside the controller class.
```
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
   // ...
}
```

## 4. `@Autowired`
This annotation is used for automatic dependency injection
```
@Autowired
private PostService postService;
```

## 5. `@Repository`
Repository Annotation is a specialization of @Component annotation which is used to indicate that the class provides the mechanism for storage, retrieval, update, delete and search operation on objects.
```
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
```

## 6. `@Entity`
@Entity annotation is used in the context of Spring Data JPA (Java Persistence API) to mark a class as a persistent Java class. 
```
@Entity
public class Post{
   // ...
}
```

## 7. `@Service`
Service annotation indicates that the class belongs to the business logic layer. It is used to mark the class as a service provider.
```
@Service
public class PostServiceImpl implements PostService {
   // ...
}
```