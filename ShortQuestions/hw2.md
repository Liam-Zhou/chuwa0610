##  1. Write up Example code to demonstrate the three foundmental concepts of OOP. (reference Code Demo repo as example)

- Encapsulation

  ```java
  class Person{
      private String name;
      private int age;
      public Person(String name) {
          this.name = name;
      }
      public int getAge() {
          return age;
      }
      public void setAge(int age) {
          this.age = age;
      }
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

- Polymorphism

  ```java
  // overloading
  class Person{
      private String name;
      private int age;
      public Person() {
      }
      public Person(String name) {
          this.name = name;
      }
      public Person(int age) {
          this.name = name;
          this.age = age;
      }
      public int getAge() {
          return age;
      }
      public void setAge(int age) {
          this.age = age;
      }
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public void printInfo(){
          System.out.println(name+", "+age);
      }
  }
  ```

  ```java
  // overriding
      class Studednt extends Person{
          private String name;
      	private int age;
          @Override
          public void printInfo(){
          	System.out.println("Student: "+name+", "+age);
      	}
      }
  ```

- Inheritance

  ```java
  // extends
  class Studednt extends Person{
          private String name;
      	private int age;
          @Override
          public void printInfo(){
          	System.out.println("Student: "+name+", "+age);
      	}
      }
  ```

  ```java
  // implements
  public interface SchoolEntity{
      void printAll();
  }
  class Studednt implements SchoolEntity{
          private String name;
      	private int age;
          @Override
          public void printAll(){
          	System.out.println("Student: "+name+", "+age);
      	}
      }
  ```
## 2. What is **wrapper class** in Java and Why we need wrapper class?

  A wrapper class is an object representation of a primitive data type, we use their functions like Integer.parseInt()

## 3.What is the difference between **HashMap** and **HashTable**?

  HashMap save a key-value pair. Hashtable is synchronized and does not allow null keys or values.

## 4. What is **String pool** in Java and why we need String pool?

String pool is a special storage area in Java heap for string literals, used to save memory by reusing instances of strings.

## 5. What is Java **garbage collection**?

Java garbage collection is an automatic memory management process that reclaims memory occupied by objects that are no longer in use.

## 6. What are **access modifiers** and their scopes in Java?

- public: Accessible from any other class.
- protected: Accessible within the same package and by subclasses.
- default: Accessible only within the same package.
- private: Accessible only within the same class.

## 7. What is **final** key word? (Filed, Method, Class)

- Filed: constant
- Method: to prevent overriding
- Class: to prevent inheritance

## 8. What is **static** keyword? (Filed, Method, Class). When do we usually use it?

- Filed: all instance share one copy of field
- Method: can be called without an instance
- Class: associated with the outer class

it's usually used for utility methods and shared properties.

## 9. What is the differences between **overriding** and **overloading**?

- Overriding: override method in a subclass, with same method signature
- Overloading: multiple methods with the same name but different parameters within the same class

## 10. What is the differences between **super** and **this**?

`super` refers to the superclass object, while `this` refers to the current class instance.

## 11. What is the Java **load sequence**?

The Java load sequence includes loading, linking (verification, preparation, resolution), and initialization of classes and interfaces.

## 12. What is **Polymorphism** ? And how Java implements it ?

Polymorphism is the ability of an object to take on many forms

overriding and overloading

## 13. What is **Encapsulation** ? How Java implements it? And why we need encapsulation?

Encapsulation is the bundling of data with methods that operate on that data, implemented in Java through private fields and public getter/setter methods, and needed to protect object integrity.

## 14. What is **Interface** and what is **abstract** class? What are the differences between them?

- interface:  only contains abstract methods and constants
- abstract class: have both abstract and concrete methods

## 16. What are Queue interface implementations and what are the differences and when to use what?

Implementations include LinkedList, PriorityQueue, and ArrayDeque.

PriorityQueue has order

LinkedList and ArrayQueue are First in First out