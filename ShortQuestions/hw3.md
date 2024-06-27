# 1. What is the checked exception and unchecked exception in Java, could you give one example?
1. **Checked Exceptions**
Checked exceptions are exceptions that are checked at compile-time, such as "IOException". The compiler requires that these exceptions are either handled using a try-catch block or declared in the method signature using the throws keyword.
```
// IOException
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            System.out.println(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
2. **Unchecked Exceptions**
Unchecked exceptions are not checked at compile-time. They include runtime exceptions and errors, which represent programming errors, such as logic mistakes or improper use of an API. They are subclasses of RuntimeException and Error.
```
// ArrayIndexOutOfBoundsException
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[5]); // This will cause an ArrayIndexOutOfBoundsException
    }
}
```

# 2. Can there be multiple finally blocks?
Since the finally block is used to execute important code such as closing resources and runs after the try block and any catch blocks regardless of whether an exception was thrown or caught, it can only have exactly one block. 

# 3. When both catch and finally return values, what will be the final result?
If both the catch block and the finally block contain return statements, the return value from the finally block will override the return value from the catch block. This is because the finally block is guaranteed to execute after the try and catch blocks, regardless of whether an exception was thrown.

# 4. What is Runtime/unchecked exception? what is Compile/Checked Exception?
1. **Checked (Compile-Time) Exceptions**  
They are exceptions that are checked by the compiler at compile time. The compiler requires that these exceptions are either caught and handled within the method where they occur or declared in the method signature using the throws keyword. It must be either handled using a try-catch block or declared using throws in the method signature.

2. **Unchecked (Runtime) Exceptions**  
Definition: Unchecked exceptions are exceptions that are not checked by the compiler at compile time. These exceptions include runtime exceptions and errors, which generally indicate programming errors or conditions that are not expected to be recovered from. They are not required to be caught or declared in the method signature and typically represent programming errors, such as logic errors or improper use of an API.

# 5. What is the difference between throw and throws?
1. **throw**  
The throw keyword is used to explicitly throw an exception from a method or any block of code.  
Usage:
    - Used within a method to throw an instance of an exception.
    - Can be used to throw both checked and unchecked exceptions.
    - Only one exception instance can be thrown at a time.

2. **throws**  
The throws keyword is used in a method signature to declare that the method might throw one or more exceptions. It indicates that the method does not handle these exceptions itself, but instead passes the responsibility to the caller of the method.  
Usage:
    - Used in method signatures to declare checked exceptions that might be thrown by the method.
    - Can declare multiple exceptions separated by commas.
    - Used for checked exceptions.

# 6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception ?
1. This first piece code throws an ArithmeticException at System.out.println(b / a); because dividing by zero is not allowed. The ArithmeticException catch block will handle it, and the stack trace will be printed. The program will then print "End ...".  
2. In second piece code, the division by zero is commented out. The code throws a NullPointerException at System.out.println(s.equals("aa")); because "s" is null. The NullPointerException catch block will handle it, and the stack trace will be printed. The program will then print "End ...".  
3. In third piece code, both the division by zero and the null string check are commented out. The code explicitly throws a RuntimeException. The RuntimeException catch block will handle it, and the stack trace will be printed. The program will then print "End ...".  

4. Why Put Null/Runtime Exceptions Before Exception
The order of catch blocks is important. When an exception is thrown, the Java runtime system searches for the first catch block that can handle it. This search goes from top to bottom. When general exception type (like Exception) is in front of  a more specific exception type (like NullPointerException or RuntimeException), the general catch block will catch the exception first, and the specific catch blocks will never get a chance to handle it.

# 7. What is optional? why do you use it? write an optional example.
Optional is a container object introduced in Java 8 that may or may not contain a non-null value. It is used to represent the presence or absence of a value and helps to avoid NullPointerException. By using Optional, developers can write more concise and expressive code, explicitly handling the possibility of missing values.
```
import java.util.Optional;
public class OptionalExample {
    public static void main(String[] args) {
        // Creating an Optional
        Optional<String> nonEmptyOptional = Optional.of("Hello, World!");
        Optional<String> emptyOptional = Optional.empty();
        
        // Using Optional to avoid null checks
        String value = "Hello";
        Optional<String> optionalValue = Optional.ofNullable(value);
        if (optionalValue.isPresent()) {
            System.out.println("Value is present: " + optionalValue.get());
        } else {
            System.out.println("Value is not present");
        }
}
```

# 8. Why finally always be executed?
The finally block is always executed because it is designed to ensure that cleanup code is executed regardless of whether an exception is thrown or caught. This behavior is crucial for resource management, such as closing files, releasing network resources, or cleaning up any other resources that need to be handled properly.

# 9. What are the types of design patterns in Java?
Design patterns are typical solutions to common problems in software design. They can speed up the development process by providing tested, proven development paradigms. Design patterns are generally categorized into three types: Creational, Structural, and Behavioral. Each category addresses a different aspect of software design.

1. Creational Design Patterns
Creational design patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or added complexity to the design. Creational design patterns solve this problem by controlling the object creation process.
2. Structural Design Patterns
Structural design patterns deal with object composition or how objects and classes are composed to form larger structures. They help ensure that if one part of a system changes, the entire system doesn’t need to change.
3. Behavioral Design Patterns
Behavioral design patterns deal with object collaboration and the assignment of responsibilities between objects. They help in defining how objects interact and communicate with each other.

# 10. What are the SOLID Principles?
The SOLID principles are a set of five design principles intended to make software designs more understandable, flexible, and maintainable. 
1. Single Responsibility Principle (SRP): A class should have only one reason to change, meaning it should have only one job or responsibility.
2. Open/Closed Principle (OCP): Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
3. Liskov Substitution Principle (LSP): Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
4. Interface Segregation Principle (ISP): Clients should not be forced to depend on interfaces they do not use.
5. Dependency Inversion Principle (DIP): High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.

# 11. How can you achieve thread-safe singleton patterns in Java ?
1. **Eager Initialization**  
In eager initialization, the instance of the singleton class is created at the time of class loading. This method is thread-safe because the instance is created when the class is loaded, and class loading is thread-safe in Java.
2. **Synchronized Method**  
This method ensures that only one thread can access the getInstance() method at a time by using the synchronized keyword. This can lead to performance issues due to the overhead of synchronization.
Optimization: Double-Checked Locking. It reduces the overhead of synchronized access by first checking the instance without synchronization. Only if the instance is null, it enters the synchronized block.
3. **Bill Pugh Singleton Design**  
This method takes advantage of the Java class loader mechanism to ensure that the singleton instance is created in a thread-safe manner. The inner static helper class is not loaded into memory until it is referenced.

# 12. What do you understand by the Open-Closed Principle (OCP)?
Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This principle means that the behavior of a module can be extended without modifying its source code, which can be achieved through inheritance or polymorphism.

# 13. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
*1. It mean that if the object of type A can do something, the object of type B could also be able to perform the same thing*
*2. It means that all the objects of type A could execute all the methods present in its subtype B*
*3. It means if a method is present in class A, it should also be present in class B so that the object of type B could substitute object of type A.*
*4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same.*   
**Answer**:  
The correct answer is 1. It correctly captures the essence of LSP. If B is a subtype of A, then an instance of B should be able to perform any operation that an instance of A can perform. This ensures that B can be substituted for A without causing issues in the program.







