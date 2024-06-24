# **Short Questions Homework2 - MingHao Lee**

## 1. Writeup Example code to demonstrate the three fundamental concepts of OOP (reference CodeDemo repo as example)
Refer to the link: https://github.com/Liam-Zhou/chuwa0610/tree/MingHao_Lee/hw2/Coding/java-core/src/main/java/com/chuwa/learn/hw1_OOP_example
For the example 

## 2. **What is a wrapper class in Java and why do we need a wrapper class**?
   - In Java, a wrapper class is a class that encapsulates a primitive type.
   - The reasons we need wrapper classes are:
      - Wrapper classes allow us to store primitive values in collections that can only store objects, such as `ArrayList` and `HashSet`.
      - Wrapper classes enable primitives to be treated as objects: `Integer num = 10;  // Autoboxing: int to Integer`.
      - They provide useful utility methods, such as `Integer.parseInt()` for converting a `String` to an `int`, which are not available for primitive types.
      - Unlike primitives, wrapper classes can represent null values, which is useful for indicating the absence of a value.
      - Wrapper classes can be synchronized, whereas primitives cannot, which is beneficial in concurrent programming scenarios.

## 4. **What is the difference between HashMap and HashTable**?
   - `HashMap` is unsynchronized, allowing null keys/values, and is faster for single-threaded applications. `Hashtable` is synchronized, disallows null keys/values, and is thread-safe but slower. `HashMap` uses fail-fast iterators, while `Hashtable` uses enumerators. `HashMap` is preferred for new code.

## 6. **What is String pool in Java and why do we need String pool**?
   - String Pool is a special memory region where Java stores literal string values. When you create a string literal, Java checks the string pool to see if an identical string already exists. If it does, Java will reuse the existing string. If it does not, Java will create a new string in the pool.
   - The reasons we need a string pool are:
      - It benefits memory efficiency by reusing existing strings.
      - It improves performance through faster string comparison using reference equality.
      - It ensures immutability and safe reuse of string instances.

## 8. **What is Java garbage collection**?
   - Java garbage collection is an automatic memory management process that the Java Virtual Machine (JVM) uses to identify and dispose of objects that are no longer needed by a Java application. This helps in reclaiming memory, preventing memory leaks, and ensuring efficient memory utilization.

## 10. **What are access modifiers and their scopes in Java**?
- Access modifiers in Java are keywords that set the accessibility (visibility) of classes, methods, and variables. There are four primary access modifiers:

  1. **Public**
  2. **Protected**
  3. **Default (no modifier)**
  4. **Private**

### 1. Public
  - **Scope:** The member or class is accessible from any other class.
  - **Usage:** Classes, methods, and variables.

### 2. Protected
  - **Scope:** The member is accessible within the same package and by subclasses in different packages.
  - **Usage:** Methods and variables (not classes, except for nested classes).

### 3. Default (No Modifier)
  - **Scope:** The member or class is accessible only within the same package. This is also known as package-private access.
  - **Usage:** Classes, methods, and variables.

### 4. Private
  - **Scope:** The member is accessible only within the same class.
  - **Usage:** Methods and variables (not classes, except for nested classes).

## 12. **What is the final keyword? (Field, Method, Class)**
   - **Field:**
      - When a variable is declared as `final`, its value cannot be changed once it is initialized.
      - It must be initialized at the time of declaration or within the constructor for instance variables.
   - **Method:**
     - When a method is declared as `final`, it cannot be overridden by subclasses.
     - This is useful for preventing subclasses from modifying the behavior of methods.
   - **Class:**
     - When a class is declared as `final`, it cannot be subclassed.
     - This is useful for creating immutable classes or for security purposes to prevent inheritance.

## 13. **What is the static keyword? (Field, Method, Class) When do we usually use it?**
   - **Field:** A static field is shared among all instances of a class.
   - **Method:** A static method belongs to the class rather than any instance and can be called without creating an instance of the class.
   - **Class:** A static class (nested) is a class within another class that can be accessed without an instance of the outer class.
   - **Usage:** We use the static keyword when we want to share a variable or method across all instances of a class or when a method does not depend on instance variables.

## 15. **What is the difference between overriding and overloading?**
   - **Overriding:** Redefining a method in a subclass that already exists in the superclass with the same signature, runtime method, less performance and Private and final methods can NOT be overridden also Base and derived(child) classes are required here.
   - **Overloading:** Defining multiple methods in the same class with the same name but different parameters (signature), compile method, better performance, Private and final methods can be overloaded also It is being done in the same class.

## 16. **What is the difference between super and this?**
   - **super:** Refers to the superclass and is used to call the superclass's methods and constructors.
   - **this:** Refers to the current instance of the class and is used to call the current class's methods and constructors.

## 17. **What is the Java load sequence?**
   - The Java load sequence involves loading, linking, and initializing classes in the following order:
     1. **Loading:** The class loader loads the class file into memory.
     2. **Linking:** The linking phase involves verification, preparation, and resolution of the class.
     3. **Initialization:** The static variables are initialized, and the static blocks are executed.

## 18. **What is Polymorphism? And how does Java implement it?**
   - Polymorphism is the ability of a variable, function, or object to take on multiple forms.
     Java implements polymorphism through method overriding (runtime) and method overloading (compile-time).

## 19. **What is Encapsulation? How does Java implement it? And why do we need encapsulation?**
   - Encapsulation is the bundling of data and methods that operate on that data within a single unit (class).
     Java implements encapsulation through access modifiers and getter/setter methods.
     Encapsulation is needed to protect the internal state of an object and to maintain control over how the data is accessed and modified.

## 20. **What is Interface and what is an abstract class? What are the differences between them?**
   - **Interface:** A reference type in Java that can contain only abstract methods, default methods, static methods, and constants. It defines a contract that implementing classes must follow.
   - **Abstract Class:** A class that cannot be instantiated and can contain both abstract and concrete methods. It provides a base for subclasses to extend and implement abstract methods.
   - **Differences:**
     - Interfaces can only declare methods, whereas abstract classes can define and declare methods.
     - A class can implement multiple interfaces but can extend only one abstract class.
     - Interfaces are used to define a contract, while abstract classes are used to provide a common base with shared code.

## 21. **Design a parking lot (put the code in the codingQuestions/coding1 folder)**
   Refer to the link: https://github.com/Liam-Zhou/chuwa0610/tree/MingHao_Lee/hw2/Coding/java-core/src/main/java/com/chuwa/learn/ParkingLot 

## 22. **What are Queue interface implementations and what are the differences and when to use what?**
   - The Queue interface in Java is implemented by various classes such as LinkedList, PriorityQueue, and ArrayBlockingQueue.
     - **LinkedList:** Implements a doubly-linked list and can be used when frequent insertions and deletions are needed.
     - **PriorityQueue:** Implements a priority queue using a binary heap and can be used when elements need to be processed based on priority.
     - **ArrayBlockingQueue:** Implements a bounded blocking queue backed by an array and can be used in multi-threaded environments where a fixed-size queue is needed.
