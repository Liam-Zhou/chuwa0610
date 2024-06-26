# MingHao Lee Hw5 Multi-Threading

## 1. Read the article: 
### [Class-Level Lock vs Object-Level Lock](https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock)

## 2. Write a thread-safe singleton class.
 - ## Eager Initialization
 ```Java
     public class Singleton {
      private static final Singleton INSTANCE = new Singleton();
  
      private Singleton() {
          // Initialization code, if needed
      }
  
      public static Singleton getInstance() {
          return INSTANCE;
      }
  }
 ```
 - ## Lazy Initialization with Double-Checked Locking
```Java
   public class Singleton {
       private static volatile Singleton instance;
   
       private Singleton() {
           // Initialization code, if needed
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


## 3. How to create a new thread? (Please also consider the Thread Pool approach) ? 
 - ## Example
```Java
   class threadCreate implements Runnable{
    private final int id;

    public threadCreate(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("this is thread create" + id);
    }
}


public class threadDemo{
    public static void main(String[] args) {

        // Define the number of threads in the pool
        int numberOfThreads = 5;

        // Create a fixed thread pool with the specified number of threads
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        // Submit tasks to the executor
        for (int i = 1; i <= numberOfTasks; i++) {
            threadCreate task = new threadCreate(i);
            executor.execute(task);
        }

        // Initiate an orderly shutdown
        executor.shutdown();
        try {
            // Wait for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Executor did not terminate");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
        
```
## 4. Difference between Runnable and Callable?
 - ## Runnable
    - A functional interface with a single method `call()`.
    - `run()` does nor return any calue and cannot throw exception.
 - ## Callable
    - A functional interface with a single method `call()`.
    - `call()` can return a value and also checked exceptions.

## 5.What is the difference between t.start() and t.run()?
 - `t.start()` and `t.run()` could both run excute the program, whereas only git `t.run()` will just run method in the current thread but not create a new thread instead of when u use `t.start()` will start a new thread and call the run method so that it could 
    excute multi thread sinmountanlsy.

 - Small example
   ```Java
      public class ThreadExample extends Thread {
       private String threadName;
   
       ThreadExample(String name) {
           threadName = name;
       }
   
       @Override
       public void run() {
           for (int i = 1; i <= 5; i++) {
               System.out.println(threadName + " " + i);
           }
       }
   
       public static void main(String[] args) {
           ThreadExample thread1 = new ThreadExample("Thread-1");
           ThreadExample thread2 = new ThreadExample("Thread-2");
   
           System.out.println("Using t.run():");
           thread1.run();
           thread2.run();
   
           // Expected output:
           // Thread-1 1
           // Thread-1 2
           // Thread-1 3
           // Thread-1 4
           // Thread-1 5
           // Thread-2 1
           // Thread-2 2
           // Thread-2 3
           // Thread-2 4
           // Thread-2 5
   
           System.out.println("\nUsing t.start():");
           thread1 = new ThreadExample("Thread-1");
           thread2 = new ThreadExample("Thread-2");
           thread1.start();
           thread2.start();
   
           // Expected output (order may vary):
           // Thread-1 1
           // Thread-2 1
           // Thread-1 2
           // Thread-2 2
           // Thread-1 3
           // Thread-2 3
           // Thread-1 4
           // Thread-2 4
           // Thread-1 5
           // Thread-2 5
       }
   }

   ```
 
## 6. Which way of creating threads is better: Thread class or Runnable interface?

- In my opinion, there is no universally "better" approach for creating threads; it depends on the specific use case. The key difference between the two methods is that if you extend the Thread class, you can only extend one class due to Java's single inheritance model. In contrast, implementing the Runnable interface allows your class to implement multiple interfaces and provide more flexibility in development. This approach is generally preferred as it promotes better design practices and code reusability.

- Extending the Thread class:
    - Limits the ability to extend other classes.
    - Can be used when you want to override other Thread class methods.

- Implementing the Runnable interface:
    - Allows the class to extend other classes.
    - Promotes separation of thread management and task execution.
    - Generally preferred due to greater flexibility and adherence to better design principles.


## 7. What are the thread statuses?
- In Java, threads can be in various states throughout their lifecycle. The Thread.State enum defines the possible states 
  of a thread, which can be retrieved using the getState() method of the Thread class. The states are:

  - NEW
    - A thread that has been created but has not yet started.
  - RUNNABLE
    - A thread that is ready to run and waiting for the CPU time.
    - It includes threads that are currently running as well as threads that are waiting in the queue to be assigned a            processor.
  - BLOCKED
    - A thread that is blocked waiting for a monitor lock.
    - This happens when a thread tries to enter a synchronized block or method, but another thread already holds the lock.
  - WAITING
    - A thread that is waiting indefinitely for another thread to perform a particular action.
    - This state is entered when a thread waits using `Object.wait()`, `Thread.join()`, or `LockSupport.park()`.
  - TIMED_WAITING
    - A thread that is waiting for another thread to perform a particular action for a specified waiting time.
    - This state is entered when a thread waits using `Thread.sleep()`, `Object.wait(long)`, `Thread.join(long)`, 
      `LockSupport.parkNanos()`, or `LockSupport.parkUntil()`.
  - TERMINATED
    - A thread that has exited.
    - This state is entered when the run method completes or the thread is stopped.


## 8. Demonstrate deadlock and how to resolve it in Java code.
 - Deadlock is a situation in multithreading where two or more threads are blocked forever, waiting for each other to        release a lock. This can happen when multiple threads need the same locks but obtain them in different order. Here's      how you can demonstrate a deadlock in Java and how to resolve it.

 - ## Deadlock Example
   ```Java
       public class DeadlockExample {
       public static void main(String[] args) {
           final Object resource1 = "Resource1";
           final Object resource2 = "Resource2";
   
           // Thread 1 tries to lock resource1 then resource2
           Thread t1 = new Thread(() -> {
               synchronized (resource1) {
                   System.out.println("Thread 1: locked resource 1");
   
                   try { Thread.sleep(50); } catch (InterruptedException e) {}
   
                   synchronized (resource2) {
                       System.out.println("Thread 1: locked resource 2");
                   }
               }
           });
   
           // Thread 2 tries to lock resource2 then resource1
           Thread t2 = new Thread(() -> {
               synchronized (resource2) {
                   System.out.println("Thread 2: locked resource 2");
   
                   try { Thread.sleep(50); } catch (InterruptedException e) {}
   
                   synchronized (resource1) {
                       System.out.println("Thread 2: locked resource 1");
                   }
               }
           });
   
           t1.start();
           t2.start();
       }
   }
   ```
   - ## Delock Resolving Example
   - Avoid Nested Locks: Avoid having locks inside other locks. Instead, acquire all required locks at once.
   - Lock Ordering: Ensure that all threads acquire the locks in the same order.
   - Using `tryLock()` with Timeout: Use ReentrantLock with tryLock() and a timeout to avoid waiting indefinitely.

   ```Java
       public class DeadlockResolved {
       public static void main(String[] args) {
           final Object resource1 = "Resource1";
           final Object resource2 = "Resource2";
   
           // Thread 1 tries to lock resource1 then resource2
           Thread t1 = new Thread(() -> {
               synchronized (resource1) {
                   System.out.println("Thread 1: locked resource 1");
   
                   try { Thread.sleep(50); } catch (InterruptedException e) {}
   
                   synchronized (resource2) {
                       System.out.println("Thread 1: locked resource 2");
                   }
               }
           });
   
           // Thread 2 also locks resource1 then resource2 in the same order
           Thread t2 = new Thread(() -> {
               synchronized (resource1) {
                   System.out.println("Thread 2: locked resource 1");
   
                   try { Thread.sleep(50); } catch (InterruptedException e) {}
   
                   synchronized (resource2) {
                       System.out.println("Thread 2: locked resource 2");
                   }
               }
           });
   
           t1.start();
           t2.start();
       }
   }

   ```



## 9. How do threads communicate with each other?
- ## Using `wait()`, `notify()`, and `notifyAll()` Methods
- The `wait()`, `notify()`, and `notifyAll()` methods are part of the Object class and are used to facilitate communication between threads that share a common monitor (lock).

   - `wait()`: Causes the current thread to wait until another thread invokes the notify() or notifyAll() methods on the                   same object.
   - `notify()`: Wakes up a single thread that is waiting on the object's monitor.
   - `notifyAll()`: Wakes up all threads that are waiting on the object's monitor.
 
   ```Java
      import java.util.LinkedList;
      import java.util.Queue;
      
      class ProducerConsumer {
          private final Queue<Integer> queue = new LinkedList<>();
          private final int MAX_CAPACITY = 5;
      
          public void produce() throws InterruptedException {
              int value = 0;
              while (true) {
                  synchronized (this) {
                      while (queue.size() == MAX_CAPACITY) {
                          wait();
                      }
                      queue.add(value);
                      System.out.println("Produced " + value);
                      value++;
                      notify(); // Notify consumer
                      Thread.sleep(1000); // Simulate time taken to produce
                  }
              }
          }
      
          public void consume() throws InterruptedException {
              while (true) {
                  synchronized (this) {
                      while (queue.isEmpty()) {
                          wait();
                      }
                      int value = queue.poll();
                      System.out.println("Consumed " + value);
                      notify(); // Notify producer
                      Thread.sleep(1000); // Simulate time taken to consume
                  }
              }
          }
      
          public static void main(String[] args) {
              ProducerConsumer pc = new ProducerConsumer();
      
              Thread producerThread = new Thread(() -> {
                  try {
                      pc.produce();
                  } catch (InterruptedException e) {
                      Thread.currentThread().interrupt();
                  }
              });
      
              Thread consumerThread = new Thread(() -> {
                  try {
                      pc.consume();
                  } catch (InterruptedException e) {
                      Thread.currentThread().interrupt();
                  }
              });
      
              producerThread.start();
              consumerThread.start();
          }
      }

   ```
   

## 10. What’s the difference between class lock and object lock?
- ## Object Lock
- An object lock, also known as an instance lock, is tied to a specific instance of a class. When a method or a block of code is synchronized on an object lock, only the thread that holds the lock for that particular object can execute the synchronized code. Other threads that need the same lock are blocked until the lock is released.

- ## Class Lock
- A class lock is associated with the class itself, rather than any particular instance. When a method or a block of code is synchronized on a class lock, only the thread that holds the lock for that class can execute the synchronized code. Other threads that need the same lock are blocked until the lock is released.

- Class locks are typically used to protect static methods or static variables.

- ## Example
- illustrate both object locks and class locks in a single example

```Java
    public class MixedLockExample {
    public synchronized void instanceMethod() {
        System.out.println("Instance method execution: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static synchronized void staticMethod() {
        System.out.println("Static method execution: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        MixedLockExample example = new MixedLockExample();

        Runnable instanceTask = () -> example.instanceMethod();
        Runnable staticTask = () -> MixedLockExample.staticMethod();

        Thread t1 = new Thread(instanceTask, "Thread-1");
        Thread t2 = new Thread(instanceTask, "Thread-2");
        Thread t3 = new Thread(staticTask, "Thread-3");
        Thread t4 = new Thread(staticTask, "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

```

## 11. What is join() method?
 - In offical documentation it shows us for Waits for this thread to terminate. So pretty much means that if you have one    thread, but you want to wait for another to completed, that waht join() method will be use here. 

## 12. What is yield() method?
- The `yield()` method is a static method in the Thread class in Java that is used to suggest to the thread scheduler that the current thread is willing to yield its current use of the CPU. In other words, it indicates that the current thread is willing to let other threads run and might be put back in the runnable state. However, the decision to actually yield the CPU and switch to another thread is ultimately made by the thread scheduler and is platform-dependent.



## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool 
- A ThreadPool is a collection of pre-initialized threads that are ready to perform tasks. Using a thread pool can help improve the 
  performance of a program by reusing existing threads instead of creating new ones for each task, which can be resource-intensive. 
  Also allows for managing a pool of worker threads to execute tasks concurrently, thus optimizing the CPU usage and improving the 
  application's responsiveness.

- There are some types of ThreadPool 
  - Fixed Thread Pool: This type has a fixed number of threads. If a thread is busy, the task is put into a queue until a thread   
   becomes available.
    - Example: `Executors.newFixedThreadPool(int nThreads)` in Java.
  - Cached Thread Pool: This type creates new threads as needed but will reuse previously constructed threads when they are    
    available. Suitable for executing many short-lived tasks.
    - Example: `Executors.newCachedThreadPool()` in Java.
  - Single Thread Pool: This type has only one thread to execute tasks sequentially. It is useful for scenarios where tasks need to      be executed in a single-threaded environment.
    - Example: `Executors.newSingleThreadExecutor()` in Java.
  - Scheduled Thread Pool: This type is used to schedule tasks to run after a given delay or to execute periodically.
    - Example: `Executors.newScheduledThreadPool(int corePoolSize)` in Java.
  - Work Stealing Pool: A type of thread pool that maintains multiple queues (one per worker thread) to reduce contention and 
    enhance performance.
    - Example: `Executors.newWorkStealingPool()` in Java 8.

  
- TaskQueue in ThreadPool
  - FIFO Order: Usually, tasks are added to the end of the queue and executed in a first-in-first-out (FIFO) order.
  - Blocking/Non-blocking: Depending on the implementation, the task queue can be blocking (threads wait if the queue is empty) or 
   non-blocking (using mechanisms like CAS to avoid waiting).
  - Capacity: Some task queues have a fixed capacity, which can help in managing resources and avoiding overload. If the queue is 
   full, additional tasks may be rejected or handled according to a specified policy.


## 14. Which library is used to create ThreadPool? Which interface provides the main functions of thread-pool?
 - In Java, the `java.util.concurrent` library provides the classes and interfaces necessary to create and manage thread pools. This library includes the `Executors` class, which contains factory methods for creating different types of thread pools, such as `FixedThreadPool`, `CachedThreadPool`, and `SingleThreadExecutor`.
 - he primary interface that provides the main functions of a thread pool in Java is the `ExecutorService` interface. This interface extends the `Executor` interface and provides methods for managing the lifecycle of both asynchronous tasks and the executor itself. Some key methods of ExecutorService include `submit()`, `invokeAll()`, `invokeAny()`, `shutdown()`, and `awaitTermination()`. The Executors class can be used to create instances of ExecutorService with different configurations.
## 15. How to submit a task to ThreadPool?
- refer to the coding file  (solution is in com.chuwa.exercise.t08_multithreading.hw5_15)

## 16. What is the advantage of ThreadPool?
- Performance: Reduced overhead and efficient resource management.
- Scalability: Better throughput and controlled concurrency.
- Code Management: Simplified task submission and lifecycle management.
- Responsiveness: Improved application responsiveness.
- Resource Throttling: Preventing resource overload

## 17. Difference between shutdown() and shutdownNow() methods of executor.
- ## shutdown():
  - Orderly Shutdown: The `shutdown()` method initiates an orderly shutdown process. This means that it will stop accepting new tasks, but it will allow all previously submitted tasks to complete execution.
  - No Immediate Interruption: Tasks that are currently being executed or tasks that are in the queue will be allowed to finish.
  - Graceful Completion: This method is useful when you want to ensure that all tasks are completed gracefully before the executor is terminated.

- ## shutdownNow():
  - Immediate Shutdown: The `shutdownNow()` method attempts to stop all actively executing tasks and halts the processing of waiting    tasks.
  - Interrupts Running Tasks: It returns a list of tasks that were awaiting execution. Tasks that are currently running are  interrupted by calling `Thread.interrupt()` on the threads.
  - Forceful Termination: This method is useful when you need to stop the executor immediately and do not require the completion of pending tasks.
## 18. What is Atomic classes? How many types of Atomic classes? Give me some code examples of Atomic classes and its main methods. When to use it?  
- Atomic classes in Java are part of the `java.util.concurrent.atomic` package and provide a way to perform thread-safe operations on single variables without using synchronization. They use low-level atomic hardware primitives (like compare-and-swap) to ensure that operations on these variables are performed atomically.

  
- Java provides several atomic classes for different types of variables:
  1. **AtomicBoolean**: Provides atomic operations on a `boolean` value.
  2. **AtomicInteger**: Provides atomic operations on an `int` value.
  3. **AtomicLong**: Provides atomic operations on a `long` value.
  4. **AtomicReference**: Provides atomic operations on an object reference.
  5. **AtomicIntegerArray**: Provides atomic operations on an array of `int` values.
  6. **AtomicLongArray**: Provides atomic operations on an array of `long` values.
  7. **AtomicReferenceArray**: Provides atomic operations on an array of object references.

- code example refer to coding file (solution is in com.chuwa.exercise.t08_multithreading.atomicTest)

- Concurrent Access to Single Variables

  - cIdeal for situations where multiple threads need to read and write a single variable without using synchronized blocks, ensuring thread safety with less overhead.

- Non-blocking Algorithms

  - Useful in implementing non-blocking algorithms, where traditional locking mechanisms would be too costly or could lead to deadlocks.

- Performance Critical Applications

  - Provide significant performance improvements over synchronized methods or blocks due to their lower overhead and better scalability.
 

## 19. What is the concurrent collections? Can you list some concurrent data structures (Thread-safe)?
## Concurrent Collections (Easy and Important)

- For various collections, we often deal with non-thread-safe collections.
Java provides corresponding thread-safe collections for us to use directly.

| Interface | Non-thread-safe          | Thread-safe                              |
|-----------|--------------------------|------------------------------------------|
| List      | ArrayList                | CopyOnWriteArrayList                     |
| Map       | HashMap                  | ConcurrentHashMap                        |
| Set       | HashSet / TreeSet        | CopyOnWriteArraySet                      |
| Queue     | ArrayDeque / LinkedList  | ArrayBlockingQueue / LinkedBlockingQueue |
| Deque     | ArrayDeque / LinkedList  | LinkedBlockingDeque                      |


## 20. What kind of locks do you know? What is the advantage of each lock?
- ## Synchronized Blocks and Methods
  - Advantages:
    - Simplicity: Easy to use and understand.
    - Intrinsic Locking: Automatically handles locking and unlocking
    - Reentrant: A thread can re-enter any synchronized code block it holds a lock on.


- ## ReentrantLock
  - Advantages:
    - Flexibility: Provides more control over lock acquisition and release.
    - Try Lock: Can attempt to acquire the lock without blocking indefinitely.
    - Fairness: Can be configured to ensure threads acquire locks in the order they requested them.
    - Condition Variables: Supports multiple wait-sets for more complex thread coordination.


- ## ReentrantLock
  - Advantages:
    - Read-Write Separation: Allows multiple threads to read concurrently while writing is exclusive.
    - Improved Performance: Reduces contention when reading is more frequent than writing
 
## 21. What is future and completableFuture? List some main methods of CompletableFuture.
 ## Future

 - `Future` is an interface provided by Java's `java.util.concurrent` package. It represents the result of an asynchronous computation. Provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result. Once a `Future` is created, it cannot be manually completed.

 ### Main Methods of Future:
 - `get()`: Waits if necessary for the computation to complete, and then retrieves its result.
 - `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel execution of the task.
 - `isDone()`: Returns `true` if the task is completed.
 - `isCancelled()`: Returns `true` if the task was cancelled.
 
 ### Future Example:
 ```Java
   import java.util.concurrent.*;
   public class FutureExample {
       public static void main(String[] args) throws ExecutionException, InterruptedException {
           ExecutorService executor = Executors.newFixedThreadPool(2);
           Future<Integer> future = executor.submit(() -> {
               // Simulate a long running task
               Thread.sleep(2000);
               return 42;
           });
   
           System.out.println("Future result: " + future.get()); // This will block until the result is available
           executor.shutdown();
       }
   }

 ```

 ## CompletableFuture
 
 - `CompletableFuture` is a class in `java.util.concurrent` that implements `Future` and `CompletionStage`. It represents a future result of an asynchronous computation, but unlike `Future`, it can be manually completed. It provides a rich API for constructing, combining, and executing asynchronous tasks. Supports non-blocking operations and chaining of actions. Can handle exceptions and provide default values.
 
 ### Main Methods of CompletableFuture:
 
   ### Completion Methods:
   - `complete(T value)`: Manually completes the future with a given result.
   - `completeExceptionally(Throwable ex)`: Completes the future with the given exception.
   
   ### Then Methods:
   - `thenApply(Function<? super T, ? extends U> fn)`: Applies a function to the result.
   - `thenAccept(Consumer<? super T> action)`: Consumes the result.
   - `thenRun(Runnable action)`: Runs an action after the future is complete.
   - `thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn)`: Combines the results of two stages.
   
   ### Exception Handling Methods:
   - `exceptionally(Function<Throwable, ? extends T> fn)`: Handles exceptions.
   - `handle(BiFunction<? super T, Throwable, ? extends U> fn)`: Handles the result or exception.
   
   ### Combining Methods:
   - `allOf(CompletableFuture<?>... cfs)`: Waits for all futures to complete.
   - `anyOf(CompletableFuture<?>... cfs)`: Waits for any one of the futures to complete.
   
   ### Other Utility Methods:
   - `join()`: Waits for the completion and returns the result.
   - `getNow(T valueIfAbsent)`: Returns the result if completed, or the given value if not.
   - `obtrudeValue(T value)`: Forcibly sets or resets the result of this future.
   - `obtrudeException(Throwable ex)`: Forcibly sets or resets the exception of this future.

### CompletableFuture Example: 
```Java
   import java.util.concurrent.CompletableFuture;
   public class CompletableFutureExample {
       public static void main(String[] args) {
           CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
               // Simulate a long running task
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               return 42;
           });
   
           future.thenApply(result -> result * 2)
                 .thenAccept(result -> System.out.println("Future result: " + result))
                 .exceptionally(ex -> {
                     System.out.println("Exception: " + ex.getMessage());
                     return null;
                 });
   
           future.join(); // Wait for the future to complete
       }
   }
```

## 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
 - refer to the coding homework file

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
 - Refer to the coding file (solution is in com.chuwa.exercise.t08_multithreading.codingHomework)

## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. 
- refer to the coding file: (solution is in com.chuwa.exercise.t08_multithreading.hw5_24) & (solution is in 
  com.chuwa.exercise.t08_multithreading.hw5_24_test)

## 25. completable future coding: 
 - Refer to the coding file (solution is in 
  com.chuwa.exercise.t08_multithreading.codingHomework)
