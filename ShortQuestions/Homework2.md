## Homework 2
### Leo Wang 2024-06-16

#### 1. Write Example code to demonstrate the three fundamental concepts of OOP.(referenceCodeDemo repo as example) 1.Encapsulation; 2. Polymorphism; 3. Inheritance;
- Encapsulation
```java
//Person.java
package com.example.encapuslation;
public class Person {
    private String name;
    private int age;
    private String address;
    public Person (String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void displayDetails() {
        System.out.println("Name:" + name);
        System.out.println("Age:" + age);
        System.out.println("Address:" + address);

    }
}
// Main.java
public class Main {
    public static void main(String[] args) {
        Person person = new Person("John Snow", 30, "123 Main St");
        person.displayDetails();

        person.setName("Jone Swan");
        person.setAge(32);
        person.setAddress("123 W Main St");

        person.displayDetails();
    }
}
```
- Polymorphism
```java
package com.example.polymorphism;
// Animal.java
public class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}
// Dog.java
public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}
//Main.java
public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();
        myAnimal.makeSound();
        myDog.makeSound();
        OverloadingExample example = new OverloadingExample();
        example.print(10);
        example.print(10.5);
        example.print("Test overloading");
    }
}
//OverloadingExample.java
public class OverloadingExample {
    public void print(int num) {
        System.out.println("Printing integer: " + num);
    }
    public void print(double num) {
        System.out.println("Printing double: " + num);
    }
    public void print(String str) {
        System.out.println("Printing string: " + str);
    }
}
```
- Inheritance
```java
//vehicle.java
public class Vehicle {
    private String brand;
    private int year;
    public vehicle (String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    public String getBrand() {
        return brand;
    }
    public int getYear() {
        return year;
    }
    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
    }
}
//car.java
public class Car extends Vehicle {
    private int doors;
    public Car(String brand, int Year, int doors) {
        super(brand, year);
        this.doors = doors;
    }
    public int getDoor() {
        return doors;
    }
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Doors: " + doors);
    }
}
//Main.java
public class Main {
    public static void main (Strings[] arg) {
        Car car = new car("Toyota", 2020, 4);
        System.out.println("Car details:");
        car.displayDetails();
    }
}
```
#### 2. What is wrapper class in Java and Why we need wrapper class?
In Java, a wrapper class is an object representation of a primitive data type. 
Each primitive data type has a corresponding wrapper class in the java.lang package. 
The primary purpose of wrapper classes is to provide a way to use primitive data types as objects.

#### 3. What is the difference between Hash Map and HashTable?
- Hashmap:
  - Not Synchronized
  - Allows one null key and multiple null values
  - Generally faster
  - Part of the Java Collections Framework
  - Use fail-fast iterator
- Hashtable:
  - Synchronized
  - Does not allow null keys or values
  - Generally slower due to synchronization
  - Legacy class from early Java versions
  - Users enumeration and it not fail-fast

#### 4. What is String pool in Java and why we need String pool?
The String pool in Java is a special memory region where Java stores string literals. 
This pool is part of the Java heap memory. 
The main purpose of the String pool is to reduce memory usage and improve the performance of string handling by reusing instances of strings.
The immutability allows the safe sharing of string instances across different parts of an application without risk of modification.

#### 5. What is Java garbage collection?
Java garbage collection is an automatic memory management process that reclaims memory occupied by objects that are no longer in use by the application.
This helps in preventing memory leaks and optimizing the performance of Java applications by freeing up resources that are no longer needed.

#### 6. What are access modifiers and their scopes in Java?
Access modifiers in Java are keywords that set the accessibility or scope of classes, methods, and other members. 
They control where these members can be accessed from within the code.
- Private: Accessible only within the class it is defined.
  - Use when you want to hide the implementation details and protect the data from being accessed directly from outside the class.
- Default: 
  - Accessible only within the same package
- Protected: 
  - Accessible within the same package and also accessible in subclasses even if they are in different packages.
- Public:
  - Use when you want to allow access to the member from any other class, promoting maximum accessibility. 

#### 7. What is final keyword? (Field,Method,Class)
Define an entity that cannot be changed or modified.
- final variable: value can not be changed when assigned
- final method: method can not be overridden by subclasses, prevent subclasses from altering the method
- final class: class can not be subclassed, used to prevent inheritance

#### 8. What is static keyword?(Field,Method,Class).When do we usually use it?
The Static keyword signifies that the member belongs to the class itself, rather than to any specific instance of the class.
This means the static member are shared among all instances of the class.
- static variable (aka class variable): shared among all instances of a class
  - There is only one copy of a static variable, regardless of how many instances of the class exists.
- static method: Static method belongs to the class rather than to any specific instance.
  - Static method can access static variables and other static methods directly, but not access instance variables or instance methods.
- static block: Used for static initialization of a class.
  - The block is executed when the class is first loaded into memory. It can be useful for initializing static variable.
- static nested class: 
  - can be instantiated without an instance of the outer class
  - can be access static member of the outer class
  - behave like top-level classes

#### 9. What is the differences between overriding and overloading?
- Runtime Polymorphism (Method Overriding): 
  - Achieved by defining a method in a subclass that has the same signature as a method in the superclass. 
  - The appropriate method is called based on the actual object type at runtime.
- Compile-time Polymorphism (Method Overloading): 
  - Achieved by defining multiple methods with the same name but different parameter lists in the same class. 
  - The appropriate method is selected based on the argument types at compile time.

#### 10. What is the differences between super and this?
- this:
  - refers to the current instance of the class
  - used to access instance variables, methods, and constructors within the same class.
  - helps in resolving variable shadowing and constructor chaining within the same class
- super:
  - refers to the immediate parent class of the current class
  - used to access variables, methods, and constructors of the parent class
  - helps in resolving variable shadowing and constructor chaining from the parent class.

#### 11. What is the Java load sequence?
- loading: The class loader loads the .class file into memory.
- Linking: The linking process includes verification, preparation, and resolution of the class
- Initialization: This includes static initialization blocks, static fields, instance fields, instance initialization blocks, and constructors.

#### 12. What is Polymorphism ? And how Java implements it?
- Runtime Polymorphism (Method Overriding):
  - Achieved by defining a method in a subclass that has the same signature as a method in the superclass.
  - The appropriate method is called based on the **actual object** type at runtime.
- Compile-time Polymorphism (Method Overloading):
  - Achieved by defining multiple methods with the same name but different parameter lists in the same class.
  - The appropriate method is selected based on the **argument types** at compile time.

#### 13. What is Encapsulation ? How Java implements it? And why we need encapsulation?
Encapsulation restricts direct access to some of an object's components, which can prevent the accidental modification of data. 
The primary goal is to ensure that the internal representation of an object is hidden from the outside and only accessible through a well-defined interface.
- Private Fields:
  - Class fields (variables) are declared as private. 
  - This means they cannot be accessed directly from outside the class.
- Public Getter and Setter Methods:
  - Public methods (getters and setters) are provided to access and update the value of private fields. 
  - These methods can include validation logic to enforce constraints on the data.

#### 14. What is Interface and what is abstract class? What are the differences between them?
Interface cannot contain instance fields or constructors. 
They are used to specify a contract that a class must follow, but they do not provide any implementation for the methods.
An abstract class is a class that cannot be instantiated on its own and intended to be subclassed.
It can have both abstract methods and concrete methods.
An abstract class can also have instance variables and constructors.

#### 16. What are Queue interface implementations and what are the differences and when to use what?
The Queue interface in Java is part of the Java Collections Framework and provides a way to handle collections in a FIFO manner.
- LinkedList: When you need a general purpose queue or deque, implements List, Deque, Queue interface
- PriorityQueue: priority based queue, sinking and swimming.
- ArrayDeque: Use for a resizable array-backed deque with efficient insertions and deletions from both ends.
- ConcurrentLinkedQueue: Use for a thread-safe non-blocking queue for high-throughput applications.
- LinkedBlockingQueue: Use for a thread-safe blocking queue with optional capacity bounds for producer-consumer scenarios.
- ArrayBlockingQueue: Use for a thread-safe blocking queue with fixed capacity.
- PriorityBlockingQueue: Use for a thread-safe blocking queue that processes elements based on priority.
- DelayQueue: Use for a thread-safe blocking queue where elements become available after a delay.