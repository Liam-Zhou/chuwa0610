2. What is the checked exception and unchecked exception in Java, could you give one example?
- checked exception: compile exception
  - IOException, SQLException
- unchecked exception: runtime exception
  - IndexOutOfBoundsException, ArithmeticException
3. Can there be multiple finally blocks?  
    No     


4. When both catch and finally return values, what will be the final result?
    value in finally.


5. What is Runtime/unchecked exception? what is Compile/Checked Exception?
    - runtime exception/unchecked exception
      - extends Exception
    - compile/checked exception
      - extends RuntimeException
6. What is the difference between throw and throws?
   - both related to exception
   - throw keyword is used to explicitly throw an exception in try catch block
     ```
     try {
            
            throw new ArithmeticException("This is an example of throwing an exception");
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
     ```

   - throws keyword is used in method declarations to specify that the method may throw one or more exceptions.
     - ```returnType methodName(parameterList) throws exception1, exception2, ...;```
7. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime
   exception before Exception ?
    catch scope should be from small to large.
    Exception has the biggest scope.


7. What is optional? why do you use it? write an optional example.
-  type-level solution for representing optional values instead of null references.
- handle scenarios where a value may be absent
```
@Test
public void TEST() {
    String name = "baeldung";
    Optional<String> opt = Optional.of(name);
    assertTrue(opt.isPresent());
}
```
8.  Why finally always be executed ?
- clean up purpose
  - close FileReader

9.  Practice collection problems here:    
    - [folder](../Coding/collection)
    

10. What are the types of design patterns in Java ?   
    - Creational Patterns
      - Singleton Pattern, Factory Method Pattern, Abstract Factory Pattern
    - Structural Patterns
      - Adapter Pattern, Decorator Pattern, Facade Pattern
    - Behavior Patterns
      - Observer Pattern, Strategy Pattern, Iterator Pattern
    
11. What are the SOLID Principles ?
    - Single Responsibility Principle
      - each class has only one responsibility
    - Open/Close Principle
      - open to extension, close to modification
    - Liskov Substitution Principle
      - superclass should be replaceable by its subclasses without affecting the correctness
    - Interface Segregation Principle
      - clients should not depend on interface they don't use, better to have small, more specific interfaces
    - Dependency Inversion Principle
      - high-level modules/classes should not depend on low-level modules classes, Both should depend on abstractions.
    

12. How can you achieve thread-safe singleton patterns in Java ?
    ```
    // static volatible variable
    // make constructor private
    // static synchornize getInstance method
    public class Singeleton {
        private static Singeleton instance = new Singeleton();
        private Singeleton(){}
        public static Singeleton getInstance() {
            return instance;
        }
    }
    ```
13. What do you understand by the Open-Closed Principle (OCP) ?
    open to extension, close to modification.
    Using interface and abstract classes to define contracts that can be implemented or extended by difference classes without changing existing code.


14. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be
            substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.

    select 3

    ~~1.It means that if the object of type A can do something, the object of type B could also be able tp
        perform the same thing~~   
    ~~2. It means that all the objects of type A could execute all the methods present in its subtype B~~     
    3 It means if a method is present in class A, it should also be present in class B so that the object of
                type B could substitute object of type A.   
    ~~4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same.~~
15.  Watch the design pattern video, and type the code, submit it to MavenProject folder
     singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22
     Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6
     d9
     Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6
     d9
     Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f
     7accf859bf7f6d9

