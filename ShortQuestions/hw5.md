# hw5

### \1. Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock

### \2. Write a thread-safe singleton class 

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        // private constructor
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

```



### \3. How to create a new thread(Please also consider Thread Pool approach)?

- Thread

  ```java
  public class MyThread extends Thread {
      public void run() {
          System.out.println("Thread is running");
      }
  
      public static void main(String[] args) {
          MyThread t1 = new MyThread();
          t1.start();
      }
  }
  
  ```

- Runnable

  ```java
  public class MyRunnable implements Runnable {
      public void run() {
          System.out.println("Thread is running");
      }
  
      public static void main(String[] args) {
          Thread t1 = new Thread(new MyRunnable());
          t1.start();
      }
  }
  
  ```

- Callable

  ```java
  import java.util.concurrent.Callable;
  import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;
  import java.util.concurrent.Future;
  
  public class MyCallable implements Callable<String> {
      public String call() throws Exception {
          return "Callable result";
      }
  
      public static void main(String[] args) {
          ExecutorService executor = Executors.newFixedThreadPool(1);
          MyCallable myCallable = new MyCallable();
          Future<String> future = executor.submit(myCallable);
  
          try {
              System.out.println(future.get());
          } catch (Exception e) {
              e.printStackTrace();
          }
  
          executor.shutdown();
      }
  }
  
  ```

  

- Thread Pool

  ```java
  import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;
  
  public class ThreadPoolExample {
      public static void main(String[] args) {
          ExecutorService executor = Executors.newFixedThreadPool(5);
          for (int i = 0; i < 10; i++) {
              executor.execute(new MyRunnable());
          }
          executor.shutdown();
      }
  }
  
  ```

  

### \4. Difference between Runnable and Callable?

- Runnable: Does not return a result and cannot throw checked exceptions.
- Callable: Returns a result and can throw checked exceptions.

### \5. What is the difference between t.start() and t.run()?

- `t.start()`: Starts a new thread of execution.
- `t.run()`: Executes the run method on the current thread, no new thread is created.

### \6. Which way of creating threads is better: Thread class or Runnable interface?

Implementing `Runnable` is generally better as it allows the class to extend another class and separates the task from the thread.

### \7. What are the thread statuses?

- **NEW**: A thread that has been created but not yet started
- **RUNNABLE**: A thread that is ready to run and is either running or waiting for CPU time.
- **BLOCKED**: A thread that is waiting to acquire a lock to enter a synchronized block/method.
- **WAITING**: A thread that is waiting indefinitely for another thread to perform a particular action (e.g., waiting on `Object.wait()`).
- **TIMED_WAITING**: A thread that is waiting for another thread to perform a particular action for up to a specified waiting time (e.g., `Thread.sleep(milliseconds)` or `Object.wait(timeout)`).
- **TERMINATED**: A thread that has exited either because it has completed its task or due to an unhandled exception.

### \8. Demondtrate deadlock and how to resolve it in Java code.

Resolution: Use a timeout, avoid nested locks, or use a deadlock detection mechanism.

```java
public class DeadlockExample {
    static class Resource {
        public synchronized void method1(Resource resource) {
            System.out.println("Thread 1: Locked resource 1");
            try { Thread.sleep(100); } catch (Exception e) {}
            resource.method2();
        }

        public synchronized void method2() {
            System.out.println("Thread 1: Locked resource 2");
        }
    }

    public static void main(String[] args) {
        final Resource resource1 = new Resource();
        final Resource resource2 = new Resource();

        Thread t1 = new Thread(() -> resource1.method1(resource2));
        Thread t2 = new Thread(() -> resource2.method1(resource1));

        t1.start();
        t2.start();
    }
}

```

### \9. How do threads communicate each other?

Using `wait()`, `notify()`, and `notifyAll()` methods

```java
public class ThreadCommunication {
    public static void main(String[] args) {
        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1 is running");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                lock.notify();
                System.out.println("Thread 2 is running");
            }
        });

        t1.start();
        t2.start();
    }
}

```



### \10. What’s the difference between class lock and object lock?

- **Class Lock**: Achieved by using a static synchronized method or synchronizing on the `.class` object.

- **Object Lock**: Achieved by synchronizing a non-static method or synchronizing on the `this` instance.

  

  **Locking Target**:

  - **Object Lock**: Locks a specific instance of the class.
  - **Class Lock**: Locks the entire class.

  **Applicable Scope**:

  - **Object Lock**: Used for synchronizing instance-level methods or code blocks.
  - **Class Lock**: Used for synchronizing class-level (static) methods or code blocks.

  **Impact Range**:

  - **Object Lock**: Affects only the specific instance that is locked.
  - **Class Lock**: Affects all instances of the class.

### \11. What is join() method?

The `join()` method in Java allows one thread to wait for the completion of another thread. It ensures that the current thread will not proceed until the thread on which `join` was called has finished executing.

```java
Thread t = new Thread(() -> {
    // Task
});
t.start();
t.join(); // Current thread waits for t to finish

```



### \12. what is yield() method

The `yield()` method in Java is a hint to the thread scheduler that the current thread is willing to yield its current use of the processor. The thread scheduler can then choose to allow other threads to execute.

```java
Thread.yield();

```



### \13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

- ThreadPool: A ThreadPool in Java manages a pool of worker threads. It minimizes the overhead of thread creation by reusing existing threads.
- Types of ThreadPools:
  - FixedThreadPool
  - CachedThreadPool
  - SingleThreadExecutor
  - ScheduledThreadPool
- TaskQueue is a queue that holds tasks waiting to be executed by the threads in the pool.

### \14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

- The `java.util.concurrent` library is used to create ThreadPools.
- The `ExecutorService` interface provides the main functions of a thread pool.

### \15. How to submit a task to ThreadPool?

```java
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> {
    // Task
});

```



### \16. What is the advantage of ThreadPool?

- Efficiently manages thread creation and destruction, reducing overhead.
- Improves performance by reusing threads for multiple tasks.
- Helps in managing concurrent execution of multiple tasks.

### \17. Difference between shutdown() and shutdownNow() methods of executor

- `shutdown()`: Initiates an orderly shutdown where previously submitted tasks are executed, but no new tasks will be accepted.
- `shutdownNow()`: Attempts to stop all actively executing tasks and halts the processing of waiting tasks, returning a list of the tasks that were awaiting execution.

### \18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic  classes and its main methods. when to use it?

- Atomic classes in Java provide a way to perform atomic operations on variables without using synchronization.

- Types of Atomic classes include `AtomicInteger`, `AtomicLong`, `AtomicReference`, `AtomicBoolean`

  ```java
  AtomicInteger atomicInt = new AtomicInteger(0);
  atomicInt.incrementAndGet(); // Atomically increments and returns the updated value
  atomicInt.compareAndSet(0, 5); // Atomically sets the value to 5 if the current value is 0
  
  ```

- Use them when you need to perform atomic operations on single variables without the overhead of synchronization.

### \19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

- Concurrent collections in Java provide thread-safe versions of collections for use in concurrent environments.
- ConcurrentHashMap , CopyOnWriteArrayList , ConcurrentLinkedQueue , ConcurrentSkipListMap

### \20. What kind of locks do you know? What is the advantage of each lock?

- **ReentrantLock**: Allows the same thread to acquire the lock multiple times. Provides more control over locking mechanisms (e.g., tryLock(), lockInterruptibly()).
- **ReadWriteLock**: Separates read and write locks, allowing multiple threads to read simultaneously while maintaining exclusive access for writing. Improves performance for read-heavy operations.
- **StampedLock**: Provides optimistic locking mechanism, along with read and write locks. Better performance in scenarios with more read operations.
- **Semaphore**: Manages a set of permits to allow a specific number of threads to access a resource. Useful for limiting resource usage.
- **CountDownLatch**: Allows one or more threads to wait until a set of operations being performed in other threads completes.
- **CyclicBarrier**: Allows a set of threads to all wait for each other to reach a common barrier point. Useful for coordinating multiple threads.

### \21. What is future and completableFuture? List some main methods of ComplertableFuture.

**Future**:

- `Future` is a part of the `java.util.concurrent` package. It represents the result of an asynchronous computation. Methods provided by `Future` allow you to check if the computation is complete, wait for its completion, and retrieve the result.
- Common methods:
  - `get()`: Waits if necessary for the computation to complete and then retrieves its result.
  - `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel the execution.
  - `isDone()`: Returns `true` if the computation is complete.
  - `isCancelled()`: Returns `true` if the task was cancelled before completion.

**CompletableFuture**:

- `CompletableFuture` is a part of the `java.util.concurrent` package and extends `Future` with additional methods for dealing with the results of asynchronous computations, allowing for complex asynchronous programming patterns (like chaining and combining futures).
- Main methods of CompletableFuture
  - `supplyAsync(Supplier<U> supplier)`: Runs a task asynchronously and returns a `CompletableFuture` with the task's result.
  - `thenApply(Function<? super T,? extends U> fn)`: Applies a function to the result of the computation when it completes.
  - `thenAccept(Consumer<? super T> action)`: Performs an action with the result of the computation when it completes.
  - `thenRun(Runnable action)`: Runs a given action when the computation completes.
  - `handle(BiFunction<? super T, Throwable, ? extends U> fn)`: Handles the result of the computation or any exception.
  - `exceptionally(Function<Throwable, ? extends T> fn)`: Provides a fallback result in case of an exception.
  - `join()`: Similar to `get()`, but does not throw checked exceptions.

### \22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

###  \23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

- \1. One solution use synchronized and wait notify 

- \2. One solution use ReentrantLock and await, signal

​	![image-20240626172531901](C:\Users\yuan\AppData\Roaming\Typora\typora-user-images\image-20240626172531901.png)

### \24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run  sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

![image-20240626172601289](C:\Users\yuan\AppData\Roaming\Typora\typora-user-images\image-20240626172601289.png)

### \25. completable future:

- \1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum  and product of two integers, and print the results.

- \2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
  - \1. Sign In to Developer.BestBuy.com
  
  - \2. Best Buy Developer API Documentation (bestbuyapis.github.io)
  
  - \3. 可以⽤fake api https://jsonplaceholder.typicode.com/
  
    
  
- \3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.
