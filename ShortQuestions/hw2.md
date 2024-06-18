# HW2 
1. Write up Example code to demonstrate the three foundmental concepts of OOP. (reference Code Demo
    repo as example)   
- Encapsulation;
```java
class Pet {
    public String name;
    public int age;
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    public void displayInfo() {
        System.out.println("Pet Name: " + name);
        System.out.println("Pet Age: " + age);
    }
}

```
-  Polymorphism;  
```
Pet myPet = new Cat("April", 5, "British Shorthair");
myPet.displayInfo();
```
- Inheritance;
```
class Cat extends Pet{
    public String breed;
    public Cat(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return this.breed;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
}
```
2. What is wrapper class in Java and Why we need wrapper class?
- Java Wrapper classes allow developers to easily convert between primitive data type and their corresponding wrapper classes.
  - eg. int -> Integer, Integer -> int
- It enables developers to easily convert from one type to another without writing their conversion code.

3. What is the difference between HashMap and HashTable?   

   | HashMap                           | HashTable           |
   |-----------------------------------|---------------------|
   | Not synchronized                  | synchronized        |
   | not thread-safe                   | thread-safe         |
   | null is allowed for key and value | null is not allowed |


4. What is String pool in Java and why we need String pool?
- String pool is a collection of unique string instances that are stored by the JVM.
- When a string literal is created, the JVM checks the String Pool to see if an identical string exists. If yes, the JVM will 
return a reference to the existing string, if no, the JVM adds the new string to the pool and return a reference to it. Therefore, 
String Pool could improve the memory efficiency by reusing identical strings. also it will be faster when using "==", besides, 
String are immutable and safe.


5. What is Java garbage collection?   
Java Garbage Collection is a process when JVM automatically reclaim memory that is no longer in use.


6. What are access modifiers and their scopes in Java?
public, private, protected, default   

| Modifier  | same class | other class (same pkg) | subclass(same pkg) | subclass (diff pkg) | other class (diff pkg) |
|-----------|------------|------------------------|--------------------|---------------------|------------------------|
| public    | yes        | yes                    | yes                | yes                 | yes                    |
| private   | yes        | no                     | no                 | no                  | no                     |
| protected | yes        | yes                    | yes                | yes                 | no                     |
| default   | yes        | yes                    | yes                | no                  | no                     |


7. What is final key word? (Filed, Method, Class)
- Final filed: 
  - constant, cannot be changed once initialized
- Final Method: 
  - cannot be overridden by subclass
- Final Class: 
  - cannot be subclassed

8. What is static keyword? (Filed, Method, Class). When do we usually use it?   

- static field
  - belong to class, not any specific instance
  - shard among all instances of a class,
- static method
  - belong to class
  - can be called without creating an instance of the class
- static class
  - cannot be instantiated
  - static class only contains static members
  
- when to use
  - share data across all instances of a class

9. What is the differences between overriding and overloading? 
- Override
  - happens when extends a superclass
  - same signature
- Overload
  - same function name with different parameters
  
10. What is the differences between super and this? 
- this
  - refer to current object
- super
  - used to refer to the immediate parent class object

11. What is the Java load sequence?    
refers to the order of various components of a Java program are loaded and initialized.
the order will be : static variable and static blocks, instance creation


12. What is Polymorphism ? And how Java implements it ?
- Polymorphism
  - same operation may behave different bases on different class
- two types of polymorphism
  - override: run time
  - overloadL compile time
  
13. What is Encapsulation ? How Java implements it? And why we need encapsulation?
- encapsulation
  - bundling of data and methods into a single class
- how 
  - access modifiers
    - private, public, protected
  - getters, setters
- why
  - for code reliability, maintainability, and scalability 

14. What is Interface and what is abstract class? What are the differences between them?
- abstract class 
  - a class that cannot be instantiated and can contain both abstract and non-abstract methods. 
- interface
  - is a contract that specifies a set of methods that a class must implement.
- difference
  - Abstract Class:
    - Can have abstract and concrete methods.
    - no limitation of variables
    - Supports single inheritance.
    - Can have instance variables and constructors.
  - Interface:
    - Can only have abstract methods.
    - only static and final variables
    - Supports multiple inheritance.
    - Cannot have instance variables or constructors.
    
15. design a parking lot (put the code to codingQuestions/coding1 folder, )
    If you have no ability to design it, please find the solution in internet, then understand it, and re-type
        it.(Do NOT just copy and paste)
16. What are Queue interface implementations and what are the differences and when to use what?
- Queue
  - follows First-In-First-Out principle.
- Queue interface implementation
  - LinkedList
    - provides efficient operations for adding, removing elements
  - ArrayDeque
    - add or remove elements of both ends
  - PriorityQueue
    - retrieve elements with high-priority element first always