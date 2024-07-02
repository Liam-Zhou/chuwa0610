# Spring Boot Annotations

## 1. Common Annotations

### `@SpringBootApplication`
- **Usage:** Applied on the main class of a Spring Boot application.
- **Explanation:** Combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. It sets up the Spring Boot application context and enables auto-configuration.

### `@Configuration`
- **Usage:** Applied on classes that define beans or configuration settings.
- **Explanation:** Indicates that the class can be used by the Spring IoC container as a source of bean definitions.

### `@Bean`
- **Usage:** Applied on methods within a `@Configuration` class.
- **Explanation:** Indicates that a method produces a bean to be managed by the Spring container.

## 2. Controller Annotations

### `@RestController`
- **Usage:** Applied on classes to handle RESTful web services.
- **Explanation:** Combines `@Controller` and `@ResponseBody`, eliminating the need to annotate each method with `@ResponseBody`.

### `@RequestMapping`
- **Usage:** Applied on methods and classes to map web requests to specific handler methods.
- **Explanation:** Specifies the URL pattern for which the controller method will be invoked.

### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- **Usage:** Applied on methods to handle HTTP GET, POST, PUT, and DELETE requests, respectively.
- **Explanation:** Specialized versions of `@RequestMapping` for specific HTTP methods.

### `@PathVariable`
- **Usage:** Applied on method parameters to extract values from URI template variables.
- **Explanation:** Binds the method parameter to a URI template variable.

### `@RequestParam`
- **Usage:** Applied on method parameters to extract query parameters from the request URL.
- **Explanation:** Binds the method parameter to a web request parameter.

## 3. Entity Annotations

### `@Entity`
- **Usage:** Applied on classes to mark them as JPA entities.
- **Explanation:** Specifies that the class is an entity and is mapped to a database table.

### `@Table`
- **Usage:** Applied on entity classes to specify the table name.
- **Explanation:** Provides the table name for the entity. If not specified, the class name is used as the table name.

### `@Id`
- **Usage:** Applied on fields to specify the primary key of an entity.
- **Explanation:** Marks the field as the primary key.

### `@GeneratedValue`
- **Usage:** Applied on fields marked with `@Id`.
- **Explanation:** Specifies the generation strategy for the primary key values.

### `@Column`
- **Usage:** Applied on fields to customize the column mapping.
- **Explanation:** Specifies the column name, length, nullable status, etc.

### `@ManyToOne`, `@OneToMany`, `@ManyToMany`, `@OneToOne`
- **Usage:** Applied on fields to define relationships between entities.
- **Explanation:** Specifies the type of relationship between entities and how they should be mapped.

## 4. Service Annotations

### `@Service`
- **Usage:** Applied on classes to mark them as service components.
- **Explanation:** Indicates that the class holds business logic and is a service component in the Spring context.

### `@Transactional`
- **Usage:** Applied on methods and classes.
- **Explanation:** Indicates that the method or class should be executed within a transaction context.



