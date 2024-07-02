## Annotations Used by Entity

1. **@Entity**
   - **Usage**: Marks a class as a database table.
   - **Explanation**: This annotation is used to indicate that the class is an entity in a database. It is typically used in frameworks like Hibernate or JPA to map the class to a database table.
2. **@Table(name = "table_name")**
   - **Usage**: Specifies the table name in the database.
   - **Explanation**: Used in conjunction with @Entity to provide the specific table name that the entity will be mapped to in the database.
3. **@Id**
   - **Usage**: Marks a field as the primary key of the entity.
   - **Explanation**: This is used to designate a field as the primary key of the table in the database.
4. **@Column(name = "column_name")**
   - **Usage**: Specifies the column name in the database for a field.
   - **Explanation**: Allows specifying the exact column name in the database; if not used, the field name of the class will be used as the column name by default.
5. **@GeneratedValue(strategy = GenerationType.IDENTITY)**
   - **Usage**: Configures the strategy for generating primary key values.
   - **Explanation**: This is often used with the @Id annotation to specify how the primary key should be generated (e.g., automatically incremented).
6. **@CreatedDate**
7. **@UpdateTimestamp**

## Annotations Used by Controller (Spring Framework)

1. **@Controller**
   - **Usage**: Marks a class as a web controller, capable of handling requests.
   - **Explanation**: This Spring MVC annotation indicates that the class will be used to handle web requests.
2. **@RequestMapping(method = RequestMethod.GET, path = "/endpoint")**
   - **Usage**: Maps HTTP requests to handler methods of MVC and REST controllers.
   - **Explanation**: Used to map web requests onto specific handler functions. The method attribute specifies the HTTP method to be handled.
3. **@GetMapping("/path")**
   - **Usage**: A convenient shortcut for @RequestMapping(method = RequestMethod.GET).
   - **Explanation**: This is a specialized version of @RequestMapping that acts as a shortcut for handling HTTP GET requests.
4. **@PostMapping("/path")**
   - **Usage**: A convenient shortcut for @RequestMapping(method = RequestMethod.POST).
   - **Explanation**: Similar to @GetMapping, but for HTTP POST requests.
5. **@ResponseBody**
   - **Usage**: Indicates that the return type should be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name).
   - **Explanation**: Often used in RESTful web services, this annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.

## Annotations Used in Service Layers

1. **@Service**
   - **Usage**: Marks a class as a service provider.
   - **Explanation**: Denotes a class that performs service tasks, often something like business logic or call to persistence layer.
2. **@Component**
   - **Usage**: Generic stereotype for any Spring-managed component.
   - **Explanation**: Indicates that a class is a Spring component. This annotation is a generic stereotype for any Spring-managed component. `@Repository`, `@Service`, and `@Controller` are specializations of `@Component` for more specific use cases.
3. **@Repository**
   - **Usage**: Marks a class as a Data Access Object.
   - **Explanation**: Indicates that the class deals with data access logic.

## General Annotations

1. **@Override**
   - **Usage**: Indicates that a method overrides a method in a superclass.
   - **Explanation**: Ensures that the subclass method correctly overrides a method in its superclass.
2. **@Autowired**
   - **Usage**: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.
   - **Explanation**: This annotation is used to auto-inject bean on the setter method, constructor, a property, or methods.