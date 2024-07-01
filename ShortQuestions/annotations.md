# Annotations in Spring Boot

## Annotations used by Entity
### @Entity
- **Usage:** Indicates that the class is an entity and is mapped to a database table.
- **Example:**
  ```java
  @Entity
  @Table(name = "users")
  public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
  
      @Column(name = "user_name", nullable = false)
      private String userName;
  
      // Getters and Setters
  }

### @Table
- **Usage:** Specifies the primary table for the annotated entity.
- **Example:**
  ```java
  @Entity
    @Table(name = "employees")
    public class Employee {
        // Fields and methods
    }
  }


### @Id
- **Usage:** Specifies the primary key of an entity.
- **Example:**
  ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



### @GeneratedValue
- **Usage:** Provides for the specification of generation strategies for the primary keys.
- **Example:**
  ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


### @Column
- **Usage:** Used to specify the mapped column for a persistent property or field.
- **Example:**
  ```java
    @Column(name = "email_address", nullable = false)
    private String emailAddress;



### @RestController
- **Usage:** Combines `@Controlle` and `@ResponseBody`, indicating that the class handles HTTP requests and responses directly.
- **Example:**
  ```java
    @RestController
    @RequestMapping("/api/users")
    public class UserController {
        // Controller methods
    }




### @RequestMapping
- **Usage:**  Maps HTTP requests to handler methods of MVC and REST controllers.
- **Example:**
  ```java
    @RequestMapping("/api")
    public class ApiController {
        // Controller methods
    }


### @GetMapping
- **Usage:**  Shortcut for `@RequestMapping(method = RequestMethod.GET)`, mapping HTTP GET requests onto specific handler methods.

- **Example:**
  ```java
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // Method implementation
    }


### @PostMapping
- **Usage:**  Usage: Shortcut for `@RequestMapping(method = RequestMethod.POST)`, mapping HTTP POST requests onto specific handler methods.

- **Example:**
  ```java
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Method implementation
    }



### @PutMapping
- **Usage:**  Usage: Shortcut for `@RequestMapping(method = RequestMethod.PUT)`, mapping HTTP PUT requests onto specific handler methods.
- **Example:**
  ```java
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Method implementation
    }



### @DeleteMapping
- **Usage:** Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`, mapping HTTP DELETE requests onto specific handler methods.

- **Example:**
  ```java
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Method implementation
    }


