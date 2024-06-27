## 1.  Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock
## 2.  Write a thread-safe singleton class
- cehck Coding/SingletonClass

## 3.  How to create a new thread(Please also consider Thread Pool approach)?
- Extend thread class
```java
public class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running.");
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();  // Start the thread
    }
}
```
- implement runnable interface
```java
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running.");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();  // Start the thread
    }
}
```

## 4.  Difference between Runnable and Callable?
- Runnable don't not return value but Callable does.
- Runnable can't throw exception but Callable can (with in).

## 5.  What is the difference between t.start() and t.run()?
- t.start() create a new thread and run that task on the newly created thread
- t.run() run the task on current thread
- 
## 6.  Which way of creating threads is better: Thread class or Runnable interface?
- Runnable interface is better, because when we extend Thread class our new class cannot extend any other class, but with Runnable interface it is do able.
- Runnable interface can be reused and shared across multiple threads, it can pass to different thread without change.


## 7.  What are the thread statuses?
- new -> Thread has been created but not yet started.
```java
Thread t = new Thread(new RunnableTask());
```
- start -> Thread has been start but maybe still waiting for cpu to run it.
```java
t.start();
```

- blocked -> A thread that is blocked waiting for a monitor lock.
```java
synchronized(lock) {
    
}
```

- waiting -> A thread that is waiting for another thread to complete task
```java
synchronized(lock) {
    lock.wait(); 
}
```


## 8.  Demondtrate deadlock and how to resolve it in Java code.
- check Coding/deadlock for the example 
- Avoid Circular Wait: Ensure that all threads acquire locks in a consistent global order
- Timeouts: Use lock attempts with timeouts to prevent indefinite waiting
- Deadlock Detection: Implement a mechanism to detect deadlocks and take corrective actions


## 9.  How do threads communicate each other?
- synchronized block to let only one thread to access  the data.
- wait() wait for the other thread to be processed . Must be called within a synchronized context.
- notify() Wakes up one single thread that is waiting on the object's monitor. Must be called within a synchronized context.
- notifyAll() Wakes up all threads that are waiting on the object's monitor. Must be called within a synchronized context.

## 10.  What’s the difference between class lock and object lock?
- class lock is type of lock that use static key word and lock the entire class itself rather than any individual instance of the class.
- object lock is type of lock that lock the part of the instance or block methods in the class.


## 11.  What is join() method?
- join() method is used when we want to wait for the thread to finish it is task.


## 12.  what is yield() method
- The yield() method in Java is a part of the Thread class and plays a role in the thread scheduling mechanism. It is used to suggest that the current thread is willing to yield its current use of a processor but it is not guaranteed to do so.

## 13.  What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- There are 4 different type of ThreadPool
- Fixed Thread pool -> run some number of thread
- Cached Thread pool -> A pool that creates new threads as needed but reuses previously created threads when available.
- Single Thread pool -> only one thread at the time
- Scheduled Thread pool -> A pool that can schedule commands to run after a given delay or periodically.
- TaskQueue -> queue where tasks are held before they are executed by the threads in the ThreadPool.
```java

// 3 thread here 
ExecutorService executor = Executors.newFixedThreadPool(3);

// 10 task but it will process 3 each time meaing that always 3 or less thread runing at same time
for (int i = 0; i < 10; i++) {
    Runnable worker = new WorkerThread("" + i);
    executor.execute(worker); // Task submission
}
        
```
## 14.  Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- The primary library used to create and manage thread pools in Java is the java.util.concurrent package.
- The ExecutorService interface provides the main functionalities for managing and interacting with thread pools.


## 15.  How to submit a task to ThreadPool?
- execute(Runnable task): Submits a Runnable task for execution but does not return a result.
- submit(Runnable task): Submits a Runnable task for execution and returns a Future<?> representing the task.


## 16.  What is the advantage of ThreadPool?
- Thread pool has many different advantages like :
  - Reduced Overhead : Creating and destroying a thread is an expensive operation.
  - Better Resource Utilization:Thread pools manage a limited number of threads, preventing the system from being overwhelmed by too many concurrent threads.
  - Reduced Latency: By maintaining a pool of pre-initialized threads, thread pools can immediately execute incoming tasks without the delay of creating new threads.
  - Task Queueing: When all threads in the pool are busy, additional tasks are queued, ensuring that no task is lost and each task is executed as soon as a thread becomes available.


## 17.  Difference between shutdown() and shutdownNow() methods of executor
- shutdown(): Completes all submitted tasks, both running and queued.
- shutdownNow(): Attempts to stop all running tasks and discards those in the queue.

## 18.  What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
##      classes and its main methods. when to use it?
- Atomic classes in Java provide a way to perform thread-safe operations on single variables without using synchronization.
- Check Coding/AtomicTest/


## 19.  What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- Concurrent collections in Java are specialized data structures designed to handle concurrent access in a multi-threaded environment.
- ConcurrentHashMap, ConcurrentLinkedQueue, CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentSkipListMap.

## 20.  What kind of locks do you know? What is the advantage of each lock?
- Intrinsic Locks -> these are the simplest form of locking provided by the Java language. They are implemented using the synchronized keyword.
```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```
- ReentrantLock ->  Part of java.util.concurrent.locks, ReentrantLock provides the same basic behavior as intrinsic locks but with more flexibility.
```java

public class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
```
- ReadWriteLock -> ReadWriteLock is an interface that provides a pair of locks for reading and writing.

```java
public class Counter {
    private int count = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void increment() {
        rwLock.writeLock().lock();
        try {
            count++;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCount() {
        rwLock.readLock().lock();
        try {
            return count;
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
```

## 21.  What is future and completableFuture? List some main methods of ComplertableFuture.
- The Future interface represents the result of an asynchronous computation.It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result of the computation.
  - get(): Waits if necessary for the computation to complete and then retrieves its result.
  - cancel(boolean mayInterruptIfRunning): Attempts to cancel execution of the task
  - isDone(): Returns true if the task completed.
- CompletableFuture is a more advanced and flexible version of Future. It can be manually completed and used to build complex asynchronous pipelines. CompletableFuture supports a non-blocking programming model, which means that it can handle computations asynchronously without blocking the calling thread.
  - completedFuture(T value): Returns a new CompletableFuture that is already completed with the given value.
  - runAsync(Runnable runnable): Returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool() after it runs the given action.
  - supplyAsync(Supplier<U> supplier): Returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool() with the value obtained by calling the given Supplier.
  - allOf(CompletableFuture<?>... cfs): Returns a new CompletableFuture that is completed when all of the given CompletableFutures complete.
  - anyOf(CompletableFuture<?>... cfs): Returns a new CompletableFuture that is completed when any of the given CompletableFutures complete.
  
## 22.  Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
  - check Coding/ThreadTest

## 23.  Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
 
### 1.  One solution use synchronized and wait notify
- check Coding/ThreadTest/DoubleThreadTestWithLock
### 2.  One solution use ReentrantLock and await, signal
- - check Coding/ThreadTest/DoubleThreadTestWithReentrantLock

## 24.  create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
- check Coding/ThreadTest/ThreeThreadTest

## 25.  completable future:
### 1.  Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
### 2.  Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
 - check ThreadTest CompletableFurueAPITest and CompletableFutureTest

