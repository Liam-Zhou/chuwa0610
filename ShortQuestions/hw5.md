# HW5

## 2. Write a thread-safe singleton class
```java
public class Singleton {

    private Singleton() {
    }

    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

## 3. How to create a new thread(Please also consider Thread Pool approach)?

- Using Thread Class
```java
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
});
thread.start();
```

- Implementing Runnable Interface
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable is running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
```

- Using Thread Pool
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Pool is running");
            }
        });
        executor.shutdown();
    }
}
```

## 4. Difference between Runnable and Callable?

- Runnable and Callable interfaces are used to represent tasks that can be executed by a thread or an executor service, but they have key differences:

### 1. Runnable

- Method: run()
- Not return value
- Can not throw checked exceptions
- Used for tasks that do not need to return a result or handle checked exceptions

### 2. Callable

- Method: call()
- Has return value of type 'V'
- Can throw checked exceptions
- Used for tasks that need to return a result or handle exceptions

## 5. What is the difference between t.start() and t.run()?

- t.start() creates and starts a new thread, executing the run() method concurrently.
- t.run() executes the run() method in the current thread, without starting a new thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?

- Using the Runnable interface is generally considered better for creating threads because it separates the task (what needs to be done) from the threading mechanism (how it is done), promoting better code structure and reusability.

## 7. What are the thread statuses?

- New: The thread has been created but has not yet started.
- RUNNABLE: The thread is executing in JVM.
- BLOCKED: The thread is blocked waiting for a monitor lock to enter a synchronized block/method.
- WAITING: The thread is waiting indefinitely for another thread to perform a particular action.
- TIMED_WAITING: The thread is waiting for another thread to perform an action up to a specified waiting time.
- TERMINATED: The thread has exited and is no longer alive.

## 8. Demondtrate deadlock and how to resolve it in Java code.

- Deadlock in Java occurs when two or more threads are blocked forever, waiting for each other to release resources they need. 
- Deadlock Demonstration: Both threads acquire locks on resource1 and resource2 in different orders, leading to a potential deadlock where each thread is waiting for the resource held by the other.
- Resolution using Resource Ordering: Ensure that threads acquire resources in a consistent order. Here, both threads acquire resource1 before attempting to acquire resource2, preventing the possibility of deadlock.

## 9. How do threads communicate each other?

- Communicate through shared access to same object or data structure.
- Communicate through wait(), notify(), notifyAll() methods.

## 10. What’s the difference between class lock and object lock?

- Scope: Object lock applies to an instance of a class, ensuring thread safety for instance-specific data. Class lock applies to the entire class, ensuring thread safety for static data shared across all instances.
- Usage: Object lock is used with instance methods or blocks, while class lock is used with static methods or blocks.
- Instance Dependency: Object lock depends on instances of a class, whereas class lock applies uniformly across all instances and does not depend on any specific instance.

## 11. What is join() method?

- join() method is used to pause the execution of the current thread until the thread on which join() is called completes its execution. Using join() ensures that threads synchronize their execution in a desired order, useful for coordinating tasks that depend on the completion of other threads.
```java
Thread thread = new Thread(() -> {
    System.out.println("Thread is running");
});

thread.start();

try {
    thread.join(); // Wait for the thread to finish
    System.out.println("Thread has finished");
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

## 12. what is yield() method?

- yield() method is used to pause the execution of the current thread and give the processor a hint that it can switch to executing other threads of the same priority.
- yield() is useful in scenarios where threads with similar priorities need to share the CPU resources fairly, but its usage should be handled with caution as it depends on the JVM and operating system's scheduler implementation.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

- ThreadPool is a collection of threads designed to efficiently execute submitted tasks asynchronously. It helps in managing threads, reducing thread creation overhead, and improving application performance.

- FixedThreadPool: A fixed-size thread pool where the number of threads is fixed. If a thread is idle, a new one won't be created.

- CachedThreadPool: A thread pool that dynamically adjusts its size based on the workload. It creates new threads as needed but reuses existing ones when they are available.

- ScheduledThreadPool: A thread pool that can schedule tasks to execute after a given delay or periodically.

- TaskQueue: The TaskQueue in a ThreadPool holds tasks that are submitted to the ThreadPool but are waiting to be executed by available threads. It manages the order and priority of tasks awaiting execution, ensuring efficient thread utilization. Depending on the ThreadPool implementation, tasks may be queued in a FIFO (First-In-First-Out) manner or based on priority.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

- The ThreadPoolExecutor class from the java.util.concurrent package is commonly used to create and manage thread pools.

- The main interface that provides the essential functions for working with thread pools is the ExecutorService interface, also part of the java.util.concurrent package. It extends the Executor interface and provides methods for managing the lifecycle of thread pools, submitting tasks for execution, and controlling the execution of tasks asynchronously.

## 15. How to submit a task to ThreadPool?

- Create a ThreadPool: Instantiate a ThreadPoolExecutor or obtain an instance from Executors factory methods like newFixedThreadPool, newCachedThreadPool

- Define the Task: Create a task that implements the Runnable interface or Callable interface. Override the run() method for Runnable tasks or implement the call() method for Callable tasks

- Submit the Task: Use the submit() method of the ExecutorService interface to submit the task to the ThreadPool for execution

## 16. What is the advantage of ThreadPool?

- Reduced overhead: Avoids the cost of creating new threads for every task, improving performance

- Better resource management: Limits the number of concurrent threads, preventing resource exhaustion

- Improved responsiveness: Allows tasks to be executed concurrently, enhancing application responsiveness

## 17. Difference between shutdown() and shutdownNow() methods of executor
 
- shutdown(): Initiates an orderly shutdown of the ExecutorService. It allows previously submitted tasks to execute before terminating but does not accept new tasks. It returns immediately and allows currently executing tasks to complete.

- shutdownNow(): Attempts to stop all actively executing tasks and waiting tasks and returns a list of the tasks that were awaiting execution. It does not guarantee that all tasks will be stopped immediately, and it may not be able to cancel some tasks.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

### 1. Atomic Classes:
- AtomicInteger, AtomicBoolean provide thread-safe operations on a single variable without using synchronization. They ensure that operations are atomic, meaning they are performed as a single, indivisible unit of operation.

### 2. Types of Atomic Classes:
- AtomicInteger, AtomicLong: For integers and longs
- AtomicBoolean: For booleans
- AtomicReference: For object references

### 3. Code Example and methods:
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);
        int incrementedValue = atomicInt.incrementAndGet();
        System.out.println("Incremented value: " + incrementedValue);
        boolean updated = atomicInt.compareAndSet(1, 10);
        System.out.println("Compare and set result: " + updated);
        int currentValue = atomicInt.get();
        System.out.println("Current value: " + currentValue);
    }
}
```
- incrementAndGet(): Atomically increments the current value by one and returns the updated value.

- compareAndSet(expect, update): Atomically checks if the current value equals the expected value (expect). If true, sets the value to update and returns true; otherwise, returns false.

- get(): Gets the current value atomically.

### 4. When to use Atomic Classes:

- Performing thread-safe operations on a single variable without using explicit synchronization.
- Using in scenarios where simple operations (like incrementing counters, setting flags) need to be performed atomically across multiple threads.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

- Concurrent collections are thread-safe data structures provided by the java.util.concurrent package. These collections are designed to be used in concurrent environments without the need for external synchronization.

- ConcurrentHashMap: A thread-safe implementation of Map that allows concurrent access and updates without blocking.

- ConcurrentSkipListMap: A concurrent NavigableMap implementation based on skip-lists.

- ConcurrentLinkedQueue: A thread-safe FIFO (First-In-First-Out) queue implementation.

- ConcurrentLinkedDeque: A thread-safe deque (double-ended queue) implementation.

- CopyOnWriteArrayList: A thread-safe variant of ArrayList where all mutative operations (add, set, remove, etc.) are implemented by making a fresh copy of the underlying array.

- CopyOnWriteArraySet: A thread-safe variant of HashSet backed by a copy-on-write array.

## 20. What kind of locks do you know? What is the advantage of each lock?

### 1. Synchronized Locks

- Simple to use and understand. Implicitly provides mutual exclusion, ensuring only one thread executes the synchronized block/method at a time.

### 2. ReentrantLock

- More flexible than synchronized blocks. Supports features like interruptible lock acquisition, timeouts, and fairness policies.

### 3. ReadWriteLock

- Allows multiple threads to read a resource concurrently while exclusive write access is granted to only one thread at a time.

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.

- Future is a blocking way of multithreading. CompletableFuture is a non-blocking way of multithreading

- Main Methods of CompletableFuture: 

### 1. thenAccept()

### 2. supplyAsync()

### 3. thenCompose()

### 4. handle()

### 5. complete()

### 6. cancel()

## 22-25 questions are inside of the Coding/multithread folder