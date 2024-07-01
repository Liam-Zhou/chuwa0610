## HW2 
### 1. Write up Example code to demonstrate the three fundamental concepts of OOP. (reference Code Demorepo as example)
1.  Encapsulation;
2.  Polymorphism;
3.  Inheritance;
```bash
// Encapsulation
class Person {
  private int age;
  private double height;
  private double weight;
  private String hairColor;
  ...
  public int getAge() {
    return age;
  }
  public void setAge(int age;) {
    this.age = age;
  }
}

// Polymorphism
// Dynamic override
class Animal {
  ...
  public void run() {
    System.out.println("Animal running");
  }
}

class Dog extends Animal {
  @Override
  public void run() {
    System.out.println("Dog running");
  }
}
// static overload
class Computer {
  public int add(int a, int b) {
    return a + b;
  }
  public double add(double a, double b) {
    return a + b;
  }
  public String add(String a, String b) {
    return a + b;
  }
}

```
### 2. What is wrapper class in Java and Why we need wrapper class?
```bash

```
### 3. What is the difference between HashMap and HashTable?
```bash
git checkout -b new_branch
```
### 4. What is String pool in Java and why we need String pool?
```bash
git checkout master 
git merge branch_test // solve the conflicts 
git commit
```
### 5. What is Java garbage collection?
```bash
git stash -m "save the change not reday to commit"
git checkout other_branch
...
git checkout branch_leave_stash
git stash pop
```
when to use git stash?
1. Switching to another branch
2. Pulling updates from a remote repository without committing the current changes
3. Resetting your working directory to a clean state
4. working on an urgent bug on a different branch then return to your original work

### What are access modifiers and their scopes in Java? 
PR aka pull request, is used on collaborative platform like GitHub, GitLab. 
```text
propose the changes from one branch(source branch) to another branch(target branch).
team members can review the changes, leave comments, give suggestions.
require at least one approval before merging.
once PR is reviewed and approved, the change can be merged to the target branch. 
(Automatically done by platform if there's no conflicts. )
```

### What is final key word? (Filed, Method, Class)
role:
```text
1. manage dependencies. 'pom.xml'
2. build project: automates compiling source code, running test, packaging compiled code, deploying the package code.
3. documentation & reporting (dependencies, code coverage, test results)
```

### What is static keyword? (Filed, Method, Class). When do we usually use it?
prepare resources -> validate -> compile -> test -> package -> install -> deploy
```text
prepare resources: resources copying
validate: validate the project/information
```

local repo, central repo, remote repo

### What is the differences between overriding and overloading?
```text
package: create JAR/WAR package as mentioned in packaging in pom.xml file (without installing in local)
install: install the package in a local/remote maven repo
```
### What is the differences between super and this?
Maven plugins are the central part of the Maven system, allowing you to customize and extend the build process. Plugins perform tasks during the build lifecycle, such as compiling code, running tests, packaging artifacts, and deploying them. 

maven compile, maven install, maven depoly, maven clean, maven jar/war etc

### What is the Java load sequence?
```text
groupID: com.chuwa.learn
artifactID: java-core
```
###  What is Polymorphism ? And how Java implements it ? 

