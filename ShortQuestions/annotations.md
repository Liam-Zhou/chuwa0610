
# Annotations in Java

## Annotations Used by Entity

1. **@Entity**
   - **Description**: Specifies that the class is an entity and is mapped to a database table.
   - **Usage**:
     ```java
     @Entity
     public class User {
         // fields, getters, setters
     }
     ```

2. **@Table**
   - **Description**: Specifies the primary table for the annotated entity.
   - **Usage**:
     ```java
     @Entity
     @Table(name = "users")
     public class User {
         // fields, getters, setters
     }
     ```

3. **@Id**
   - **Description**: Specifies the primary key of an entity.
   - **Usage**:
     ```java
     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         // other fields, getters, setters
     }
     ```

4. **@GeneratedValue**
   - **Description**: Provides for the specification of generation strategies for the values of primary keys.
   - **Usage**:
     ```java
     @Entity
     public class User {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         // other fields, getters, setters
     }
     ```

## Annotations Used by Controller

1. **@RestController**
   - **Description**: Combines @Controller and @ResponseBody, eliminating the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
   - **Usage**:
     ```java
     @RestController
     public class UserController {
         // request handling methods
     }
     ```

2. **@RequestMapping**
   - **Description**: Used to map web requests onto specific handler classes and/or handler methods.
   - **Usage**:
     ```java
     @RestController
     @RequestMapping("/users")
     public class UserController {
         // request handling methods
     }
     ```

3. **@GetMapping**
   - **Description**: Shortcut for @RequestMapping(method = RequestMethod.GET).
   - **Usage**:
     ```java
     @RestController
     @RequestMapping("/users")
     public class UserController {
         @GetMapping("/{id}")
         public User getUserById(@PathVariable Long id) {
             // method implementation
         }
     }
     ```

4. **@PostMapping**
   - **Description**: Shortcut for @RequestMapping(method = RequestMethod.POST).
   - **Usage**:
     ```java
     @RestController
     @RequestMapping("/users")
     public class UserController {
         @PostMapping
         public User createUser(@RequestBody User user) {
             // method implementation
         }
     }
     ```

5. **@PutMapping**
   - **Description**: Shortcut for @RequestMapping(method = RequestMethod.PUT).
   - **Usage**:
     ```java
     @RestController
     @RequestMapping("/users")
     public class UserController {
         @PutMapping("/{id}")
         public User updateUser(@PathVariable Long id, @RequestBody User user) {
             // method implementation
         }
     }
     ```

6. **@DeleteMapping**
   - **Description**: Shortcut for @RequestMapping(method = RequestMethod.DELETE).
   - **Usage**:
     ```java
     @RestController
     @RequestMapping("/users")
     public class UserController {
         @DeleteMapping("/{id}")
         public void deleteUser(@PathVariable Long id) {
             // method implementation
         }
     }
     ```
