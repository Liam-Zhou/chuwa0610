1. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.

   >Use Optional to wrap the object and use the optional object to do the following operations.
   >```java
   >Optional.ofNullable(obj)
   >```

2. Discuss Java 8 new features with code snippet.

   >1. Optional
   >
   >   ```java
   >   Optional.ofNullable(obj)
   >   ```
   >
   >2. stream
   >
   >   ```java
   >   stream.map(String::toUpperCase).collect(Collectors.toList())
   >   ```
   >
   >3. lambda
   >
   >   ```java
   >   (name1, name2) -> name2.length() - name1.length()
   >   ```
   >
   >4. Default method and static method in interface
   >
   >   ```java
   >   public interface DIMLearn {
   >       static final String BLOG = "is Chuwa a";
   >       int add(int a, int b);
   >       default int substract(int a, int b) {
   >           return a - b;
   >   }
   >       static String blogName() {
   >           return BLOG;
   >   } }
   >   ```
   >
   >   

3. What are the advantages of the Optional class?

   >It forces you to handle the case where the value is absent so that avoids the potential risk when a null pointer happens.

4. Explain Functional Interface and Lambda with code samples.

   >Functional interface is an interface with only one single Abstract method.
   >
   >lambda is the implementation of the abstract method.
   >
   >```java
   >@FunctionalInterface
   >public interface Bar {
   >// 唯一一个abstract method String method(String string);
   >String method(String string);
   >default String defaultBar() {
   >        String s = "default Bar method";
   >        System.out.println(s);
   >        return s;
   >}
   >    default String defaultCommon() {
   >        String s = "defaultCommon from Bar";
   >        System.out.println(s);
   >        return s;
   >} }
   >```
   >
   >```java
   >Bar BarByIC = new Bar() {
   >            @Override
   >            public String method(String string) {
   >                return string + " from Bar";
   >} };
   >// equals
   >Bar bar = parameter -> parameter + " from Bar";
   >```
   >
   >

5. Explain Method Reference with code samples?

   >```java
   >List<Person> people = Arrays.asList(
   >              new Person("Alice", 30),
   >              new Person("Bob", 25),
   >              new Person("Charlie", 35));
   >
   >// 1. 静态方法引用
   >people.sort(Person::compareByName);
   >
   >// 使用Lambda表达式
   >people.sort((p1, p2) -> Person.compareByName(p1, p2));
   >
   >// 2. 实例方法引用(特定对象的实例方法)
   >Comparator<Person> byAgeComparator = Comparator.comparingInt(Person::getAge); people.sort(byAgeComparator);
   >
   >// 3. 类的实例方法引用
   >Function<Person, String> getNameFunction = Person::getName; 
   >List<String> names = Arrays.asList("Alice", "Bob", "Charlie"); names.sort(String::compareToIgnoreCase);
   >// 4. 构造方法引用
   >BiFunction<String, Integer, Person> personCreator = Person::new; 
   >Person newPerson = personCreator.apply("David", 40);
   >System.out.println(people);
   >```

6. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

   >1. Final variable
   >
   >   ```java
   >   final String localVariable = "Local";
   >   Foo foo = parameter -> {
   >       return parameter + " " + localVariable;
   >   };
   >   System.out.println(foo.aMethod("hello"));
   >   ```
   >
   >2. Non-final variable however never changed
   >
   >   ```java
   >   String localVariable = "Local";
   >   Foo foo = parameter -> {
   >       return parameter + " " + localVariable;
   >   };
   >   System.out.println(foo.aMethod("hello"));
   >   ```
   >
   >3. Object variable
   >
   >   ```java
   >   List<Employee> employees = EmployeeData.getEmployees();
   >   Employee employee = employees.get(0);
   >   employee.setAge(55);
   >   Foo foo = parameter -> {
   >       return parameter + " " + employee;
   >   };
   >   System.out.println(foo.aMethod("hello"));
   >   ```

7. Can a functional interface extend/inherit another interface?

   >A functional interface can extend another interface when it contains only static and default methods in it

8. What are Intermediate and Terminal operations?

   >Intermediate are operations used during the stream, while terminal operations can only used once when the full process is finished.

9. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

   >1. filter(Predicate p)  filter elements 
   >2. limit(n)  limit n elements from it.
   >3. skip(n) - skip first n elements
   >4. distinct() - remove redundant elements.
   >5. map(function f) 
   >6. sort
   >
   >```java
   >List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva",
   >"Frank");
   >List<String> filteredNames = names.stream()
   >            .filter(name -> name.length() >= 4)
   >  					.limit(4)
   >  					.skip(1)
   >  					.distinct()
   >            .map(String::toUpperCase)
   >            .sorted((name1, name2) -> name2.length() - name1.length()) 
   >            .collect(Collectors.toList());                 
   >```
   >
   >

10. How are Collections different from Stream?

    >Collections are used to store the data, usually computed before adding to the collections. Stream is used to take collections as input and do the computation based on it to get an expected results

11. Leetcode

    ```java
    return Arrays.stream(nums)
          .boxed()
      		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
          .entrySet().stream()
          .filter(e->e.getValue()==1)
          .mapToInt(Map.Entry::getKey)
          .sum();
    ```

    