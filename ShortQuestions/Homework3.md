# Homework 3

### Leo Wang, 2024-06-19

#### 2. What is the checked exception and unchecked exception in Java,could you give one example?
Checked exceptions are exceptions that the compiler checks at compile time. 
These exceptions must be either caught using a try-catch block or declared in the method signature using the throws keyword.

Unchecked exceptions are exceptions that are not checked by the compiler at compile time. 
These exceptions are subclasses of RuntimeException. 
Unchecked exceptions do not need to be declared in a method's throws clause, and they can occur at any point during the program's execution, often due to programming errors like logic mistakes or improper use of APIs.

```java
//unchecked
public class example {
    public static void main1(String[] args) {
        try {
            readFile("example.txt");
        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }
//checked
    public static void main2(String[] args) {
        int[] numbers = {1, 2, 3};
        try {
            System.out.println(getElement(numbers, 5));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("An ArrayIndexOutOfBoundsException was caught: " + e.getMessage());
        }
    }
}
```
#### 3. Can there be multiple finally blocks?
NO
In Java, a try block can be followed by one or more catch blocks to handle different exceptions, but it can only be followed by a single finally block. 
The "finally" block is used to execute important code such as cleanup operations, regardless of whether an exception is thrown or not.

#### 4.When both catch and finally return values, what will be the final result?
The return value from the finally block will override the return value from the catch block. 
This behavior occurs because the finally block is always executed after the try and catch blocks, regardless of whether an exception is thrown or caught.

#### 5. What is Runtime/unchecked exception? what is Compile/CheckedException?
Checked exceptions are exceptions that the compiler checks at compile time.
These exceptions must be either caught using a try-catch block or declared in the method signature using the throws keyword.

Unchecked exceptions are exceptions that are not checked by the compiler at compile time.
These exceptions are subclasses of RuntimeException.
Unchecked exceptions do not need to be declared in a method's throws clause, and they can occur at any point during the program's execution, often due to programming errors like logic mistakes or improper use of APIs.

#### 6. What is the difference between throw and throws?
- Throw
  - Purpose: The throw keyword is used to explicitly throw an exception from a method or any block of code.
  - Usage: It is used within a method.
- Throws
  - Purpose: The throws keyword is used in a method signature to declare that the method might throw one or more exceptions. This informs the caller of the method that it must handle these exceptions.
  - Usage: It is used in the method signature.

#### 7. Run the below three pieces codes,Noticed the printed exceptions. Why do we put the Null/Runtime exception before Exception ?
```java
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
        String s = null;
        try {
            System.out.println(b / a);
            System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
}
        System.out.println("End ...");
    }
}
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
        String s = null;
        try {
            // System.out.println(b / a);
            System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
}
        System.out.println("End ...");
    }
}
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
        String s = null;
        try {
            // System.out.println(b / a);
            // System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
}
        System.out.println("End ...");
    }
}
```
- First: ArithmeticException
- Second: NullPointerException since null.equal()
- Third: RuntimeException
Java check exceptions from top to bottom, we want to catch as detailed info as possible rather than general exception.
First to identify this exception happened at runtime, then detailed exception caught.

#### 7. What is optional? why do you use it? write an optional example.
Optional is a container object introduced in Java 8 that may or may not contain a non-null value. 
It provides a way to explicitly handle potentially missing values, helping to avoid NullPointerException and making the code more expressive and readable.
```java
Optional<String> optionalNullable = Optional.ofNullable(null);
String valueOrDefault = optionalNullable.orElse("Default Value");
System.out.println("Value or default: " + valueOrDefault);
```
#### 8. Why finally always be executed?
This guarantees that resources are properly released and the program can handle unexpected situations more gracefully.

#### 9. Practice collection problems
chuwa0610/Coding/coding3/src/test/java/com/chuwa/learn

#### 10. What are the types of design patterns in Java ?
- Creational Design Patterns
  - Trying to create objects in a manner suitable to the situation.
  - Singleton Pattern, Factory Method Pattern, Builder Patter, etc.
- Structural Design Patterns
  - Structural design patterns deal with object composition or the relationship between objects. They help ensure that if one part of a system changes, the entire system does not need to change.
  - Adapter Pattern, Composite Pattern, etc. 
- Behavioral Design Patterns
  - Behavioral design patterns are concerned with algorithms and the assignment of responsibilities between objects. They help make interactions between objects flexible and manageable.
  - Strategy Pattern, Observer Pattern, etc.

#### 11. What are the SOLID Principles ?
The SOLID principles are a set of five design principles in object-oriented programming that help make software designs more understandable, flexible, and maintainable. These principles were introduced by Robert C. Martin, also known as Uncle Bob. Each principle addresses a specific aspect of software design, and together they form a foundation for writing well-structured and robust code.
SOLID Principles
1. Single Responsibility Principle (SRP)
   - A class should have only one reason to change, meaning it should have only one job or responsibility.
2. Open/Closed Principle (OCP)
   - Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
3. Liskov Substitution Principle (LSP)
   - Subtypes must be substitutable for their base types without altering the correctness of the program.
4. Interface Segregation Principle (ISP)
   - Clients should not be forced to depend on interfaces they do not use.
5. Dependency Inversion Principle (DIP)
   - High-level modules should not depend on low-level modules. Both should depend on abstractions. Additionally, abstractions should not depend on details. Details should depend on abstractions.

#### 12. How can you achieve thread-safe singleton patterns in Java ?
Thread-Safe Singleton (Using synchronized keyword)
    - This approach ensures thread safety by synchronizing the method that returns the singleton instance.
```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```

#### 13. What do you understand by the Open-Closed Principle (OCP) ?
- Open for Extension:
  - A module should allow its behavior to be extended. 
  - This can be achieved by adding new functionality or modifying existing behavior without changing the existing code base.
- Closed for Modification:
  - Once a module is developed and tested, it should not be modified. 
  - This helps to prevent introducing bugs into existing and stable code.

#### 14. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
1. It means that if the object of type A can do something, the object of type B could also be able to perform the same thing.

#### 15. Watch the design pattern video, and type the code, submit it to MavenProject folder
chuwa0610/Coding/coding3/src/main/java/com/chuwa/learn/MavenProject