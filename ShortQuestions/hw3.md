## 2. What is the checked exception and unchecked exception in Java, could you give one example?

- checked exception: Exceptions checked at compile-time, IOException`.
- Unchecked Exception: Exceptions checked at runtime, NullPointerException`.

## 3. Can there be multiple finally blocks?  

no

## 4. When both catch and finally return values, what will be the final result?

finally return

## 5. What is **Runtime/unchecked exception**? what is Compile/Checked Exception?

- checked exception: Exceptions checked at compile-time, IOException`.
- Unchecked Exception: Exceptions checked at runtime, NullPointerException`.

## 6. What is the difference between **throw** and **throws**?

- throw: Used to explicitly throw an exception.
- throws: Used in method signature to declare exceptions that might be thrown.

## 7.

because NULL exception's level is smaller than Exception

## 7. What is **optional**? why do you use it? write an optional example. 

Optional: Optional is a container object used to represent a value that may or may not be present, helping to avoid NullPointerException and to express the absence of a value more clearly.

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable(null);

        if (optional.isPresent()) {
            System.out.println("Value is: " + optional.get());
        } else {
            System.out.println("Value is not present");
        }
    }
}

```



## 8. Why finally always be executed ?

The finally block is always executed to ensure that resources are properly released, regardless of whether an exception is thrown or not.

## 10. What are the types of design patterns in Java ?

- Creational: Singleton, Factory, Abstract Factory, Builder, Prototype.
- Structural: Adapter, Composite, Proxy, Flyweight, Facade, Bridge, Decorator.
- Behavioral: Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor.

## 11. What are the **SOLID** Principles ?

- Single Responsibility Principle: A class should have only one reason to change.
- Open/Closed Principle: Software entities should be open for extension but closed for modification.
- Liskov Substitution Principle: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
- Interface Segregation Principle: Clients should not be forced to depend on interfaces they do not use.
- Dependency Inversion Principle: High-level modules should not depend on low-level modules. Both should depend on abstractions.

## 12. How can you achieve thread-safe singleton patterns in Java ?

 we can use synchronized methods and synchronized blocks

## 13. What do you understand by the Open-Closed Principle (OCP) ?

 Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This means that the behavior of a module can be extended without modifying its source code, typically achieved by using polymorphism and abstraction.

## 14.

It mean that if the object of type A can do something, the object of type B could also be able tp perform the same thing