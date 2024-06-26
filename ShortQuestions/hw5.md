# Homework 5
## 2. 
```
public class Singleton {
    // Declare the instance as volatile to ensure every thread sees the most recently written value
    private static volatile Singleton instance;
    // Private constructor to prevent instantiation outside this class
    private Singleton() {
        // Optional: Protect against instantiation via reflection
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }
    
    // Public method to access the instance
    public static Singleton getInstance() {
        // First check without locking (for performance improvement)
        if (instance == null) {
            // Synchronize block to ensure only one thread can access this block at a time
            synchronized (Singleton.class) {
                // Double-check if instance is still null to avoid multiple instantiations
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    // Example method to demonstrate behavior of the class
    public void show() {
        System.out.println("Singleton instance hash: " + instance.hashCode());
    }
}

// Usage:
public class Main {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        
        obj1.show(); // Both objects should have the same hash code
        obj2.show();
    }
}

```
## 3. 
``` 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Creates a thread pool with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }

        executor.shutdown(); // Initiates a graceful shutdown
        while (!executor.isTerminated()) {
            // Wait until all threads finish
        }

        System.out.println("Finished all threads");
    }
}

class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

```
## 4. 
1. Return Value 
- Runnable: It does not return any result after its execution. The run() method has a void return type.

- Callable: It can return a result after its execution. The call() method returns a value of a specified type.

2. Exception Handling
- Runnable: It cannot throw checked exceptions. Any exceptions that might be thrown within the run() method must be handled inside the method itself.

- Callable: It can throw checked exceptions. This allows the call() method to propagate exceptions to the calling thread, where they can be handled appropriately.

3. Usage with ExecutorService
- Runnable: When submitted to an ExecutorService, tasks implementing Runnable can't return a result. If you submit a Runnable task, you'll receive a Future<?> with a null result upon completion.

- Callable: When submitted to an ExecutorService, tasks implementing Callable return a Future<T>, where T is the type of the return value defined by the Callable. This Future can be used to retrieve the result of the computation.

## 5.
Using t.start() is the correct way to start a new thread and execute its task concurrently. Calling t.run() directly does not start a new thread but rather executes the run() method synchronously on the current thread. 

## 6. 
In general, implementing the Runnable interface is considered a better practice for several reasons:

- It promotes better design by adhering to the principle of separation of concerns.
- It provides greater flexibility by allowing your class to extend other classes.
- It enhances reusability and manageability, especially when using thread pools.
- Therefore, unless you have a specific reason to extend the Thread class, it's usually recommended to implement the Runnable interface for creating threads in Java.

## 7.
1. NEW
   Description: A thread that has been created but has not yet started.
   State: The thread is in this state after the Thread object is instantiated but before start() is called.
2. RUNNABLE
   Description: A thread that is ready to run is moved to the RUNNABLE state.
   State: The thread is in this state after start() is called, but it may not be actually running. It is in the pool of threads that are ready to be picked up by the thread scheduler for execution.
3. BLOCKED
   Description: A thread that is blocked and waiting for a monitor lock.
   State: The thread is in this state when it is waiting for a lock to enter a synchronized block/method.
4. WAITING
   Description: A thread that is waiting indefinitely for another thread to perform a particular action.
   State: The thread is in this state when it calls Object.wait(), Thread.join() (without a timeout), or LockSupport.park().
5. TIMED_WAITING
   Description: A thread that is waiting for another thread to perform an action for up to a specified waiting time.
   State: The thread is in this state when it calls methods like Thread.sleep(long millis), Object.wait(long timeout), Thread.join(long millis), LockSupport.parkNanos(), or LockSupport.parkUntil().
6. TERMINATED
   Description: A thread that has exited.
   State: The thread is in this state when its run() method exits, either normally or due to an uncaught exception.

## 8.
```
public class DeadlockExample {
    public static void main(String[] args) {
        final Object resource1 = "Resource 1";
        final Object resource2 = "Resource 2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
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
Deadlocks can be resolved by:

- Avoiding nested locks: Simplify the design to ensure that a thread only acquires one lock at a time.
- Using lock ordering: Ensure that all threads acquire locks in a consistent, predefined order.

``` 
public class DeadlockResolution {
    public static void main(String[] args) {
        final Object resource1 = "Resource 1";
        final Object resource2 = "Resource 2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {  // Note the change: now both threads lock resource1 first
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
## 9. 
1. Using Shared Objects:
   Threads can communicate by reading and writing shared variables. Proper synchronization is necessary to ensure that the shared data is accessed in a thread-safe manner.
2. Using wait-notify Mechanism
   The wait(), notify(), and notifyAll() methods provided by the Object class are used to coordinate the execution of threads that share a common resource.

- wait(): Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object.
- notify(): Wakes up a single thread that is waiting on the object's monitor.
- notifyAll(): Wakes up all threads that are waiting on the object's monitor.

## 10.
1. Scope:

- Object Lock: Limits synchronization to a single instance. Other instances of the same class can be accessed concurrently.
- Class Lock: Limits synchronization across all instances of the class, affecting all threads trying to access static synchronized methods or blocks.
2. Usage:

- Object Lock: Used for instance methods or instance-specific synchronized blocks.
- Class Lock: Used for static methods or class-wide synchronized blocks.

## 11.
The join() method in Java is used to pause the execution of the current thread until the thread on which join() was called has finished its execution. This is particularly useful when you want one thread to wait for the completion of another thread before continuing its execution.

## 12.
The yield() method in Java is a static method of the Thread class that is used to hint to the thread scheduler that the current thread is willing to yield its current use of a CPU. Essentially, it suggests that if the system is heavily loaded, it may be a good time to let other threads of the same priority execute.
## 13.
A ThreadPool in Java is a pool of worker threads that are managed by the java.util.concurrent.ExecutorService interface. The main advantage of using a thread pool is to reuse existing threads to execute multiple tasks, rather than creating a new thread each time a task needs to be executed. This improves the performance and resource utilization of an application.

Java provides several built-in thread pool implementations through the Executors utility class. Here are the most commonly used types:

Fixed Thread Pool (Executors.newFixedThreadPool(n))

Description: A thread pool with a fixed number of threads. If all threads are busy, new tasks will wait in the queue until a thread becomes available.
Use Case: Suitable for scenarios where the number of concurrent threads is known and fixed.

Cached Thread Pool (Executors.newCachedThreadPool())

Description: A thread pool that creates new threads as needed, but reuses previously constructed threads when they are available. It can grow and shrink dynamically.
Use Case: Suitable for executing many short-lived asynchronous tasks.

Single Thread Executor (Executors.newSingleThreadExecutor())

Description: A thread pool with a single worker thread. Tasks are executed sequentially in the order they are submitted.
Use Case: Suitable for scenarios where tasks need to be executed sequentially.
Scheduled Thread Pool (Executors.newScheduledThreadPool(n))

Description: A thread pool that can schedule commands to run after a given delay or to execute periodically.
Use Case: Suitable for scheduling tasks that need to be executed after a certain delay or periodically.

The TaskQueue in a thread pool is typically an implementation of the BlockingQueue interface. It is used to hold tasks that are waiting to be executed by the worker threads in the pool. When a new task is submitted to the thread pool, it is placed in the task queue if no threads are available to execute it immediately.
## 14.
In Java, the primary library used to create and manage thread pools is the java.util.concurrent package. The key interface that provides the main functions for thread pools is the ExecutorService interface.
## 15.
To submit a task to a thread pool in Java, you use the ExecutorService interface provided by the java.util.concurrent package. The ExecutorService interface has several methods to submit tasks, such as execute(), submit(), invokeAll(), and invokeAny().
## 16.
1. Improved Performance
   Reduced Overhead: Creating a new thread for each task can be expensive due to the overhead associated with thread creation and destruction. Thread pools reuse existing threads for new tasks, thereby reducing the overhead and improving performance.
   Optimized Resource Usage: Thread pools can limit the number of active threads based on the system’s capacity, preventing resource exhaustion and ensuring that system resources are utilized efficiently.
2. Better Resource Management
   Controlled Concurrency: Thread pools allow you to control the maximum number of concurrent threads, preventing issues such as thread thrashing (where too many threads compete for limited resources) and ensuring that the application runs smoothly.
   Avoiding Resource Leaks: Properly managed thread pools help in avoiding resource leaks, as they ensure that all threads are properly terminated after use.
3. Simplified Programming Model
   Ease of Use: Using thread pools simplifies the task of managing multiple threads. The ExecutorService interface provides a higher-level abstraction for managing tasks, reducing the complexity of thread management.
   Task Submission: The ExecutorService interface provides methods for submitting tasks (execute(), submit()), which makes it easier to manage task execution and retrieval of results.
4. Enhanced Scalability
   Handling Burst Load: Thread pools can handle sudden bursts of load more gracefully by reusing existing threads, which helps in maintaining the performance of the application even under high load conditions.
   Scalable Architecture: Applications designed with thread pools are inherently more scalable, as the thread pool can grow and shrink dynamically based on the workload.
5. Improved Reliability and Stability
   Graceful Degradation: By limiting the number of threads, thread pools prevent the application from becoming unresponsive due to too many concurrent threads, thus enhancing the stability and reliability of the application.
   Graceful Shutdown: The ExecutorService interface provides methods for graceful shutdown (shutdown(), shutdownNow()), ensuring that all tasks are completed or stopped in an orderly manner.
6. Efficient Task Scheduling
   Scheduling Tasks: Thread pools, especially those created using ScheduledThreadPoolExecutor, allow for scheduling tasks to run after a delay or at fixed intervals, providing a flexible way to manage periodic tasks.
## 17.
shutdown()

Graceful Shutdown: The shutdown() method initiates an orderly shutdown of the ExecutorService. It stops accepting new tasks but allows all previously submitted tasks to complete execution.
No Interrupts: Tasks that are currently being executed will continue to run until they finish. Tasks that are waiting in the queue will be executed.
Blocking Behavior: This method returns immediately, and the shutdown process happens in the background. You can use awaitTermination() to block until all tasks have completed.

shutdownNow()

Immediate Shutdown: The shutdownNow() method attempts to stop all actively executing tasks and halts the processing of waiting tasks.
Task Interruption: It interrupts all running tasks by calling Thread.interrupt() on the threads executing them.
Return List of Waiting Tasks: This method returns a list of tasks that were submitted but never commenced execution. These tasks can be re-submitted or handled differently if needed.

## 18.
What are Atomic Classes?

Atomic classes in Java are part of the java.util.concurrent.atomic package, which provides a way to perform atomic operations on single variables without using synchronization. Atomic classes ensure that the operations are thread-safe without the overhead of synchronization, making them efficient for concurrent programming.

Types of Atomic Classes

Java provides several atomic classes, each designed to handle atomic operations on different types of variables:

AtomicInteger: For integer values.

AtomicLong: For long values.

AtomicBoolean: For boolean values.

AtomicReference: For object references.

AtomicStampedReference: For object references with version stamps.

AtomicMarkableReference: For object references with a boolean mark.

``` 
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void increment() {
        atomicInteger.incrementAndGet();
    }

    public void decrement() {
        atomicInteger.decrementAndGet();
    }

    public int getValue() {
        return atomicInteger.get();
    }

    public static void main(String[] args) {
        AtomicIntegerExample example = new AtomicIntegerExample();

        example.increment();
        System.out.println("After increment: " + example.getValue());

        example.decrement();
        System.out.println("After decrement: " + example.getValue());

        boolean updated = example.atomicInteger.compareAndSet(0, 10);
        System.out.println("Compare and set (0 to 10): " + updated);
        System.out.println("Current value: " + example.getValue());
    }
}

```
## 19.
Concurrent collections in Java are part of the java.util.concurrent package and are designed to be thread-safe. These collections provide high-performance, thread-safe alternatives to the standard collections found in the java.util package. They help manage concurrent access to data structures without the need for external synchronization, making it easier to write concurrent programs.

Key Concurrent Collections
Here are some of the commonly used concurrent data structures (thread-safe) provided by the java.util.concurrent package:

ConcurrentHashMap

A thread-safe variant of HashMap that allows concurrent read and write operations without locking the entire map. It achieves thread-safety through finer-grained locking (using segments or internal locks).

CopyOnWriteArrayList

A thread-safe variant of ArrayList in which all mutative operations (such as add, set, and remove) are implemented by making a fresh copy of the underlying array. It is well-suited for applications where the array is rarely modified, but frequently read.

CopyOnWriteArraySet

A thread-safe variant of HashSet that is backed by a CopyOnWriteArrayList. It provides similar benefits as CopyOnWriteArrayList.
## 20.
1. Intrinsic Locks (Synchronized Blocks/Methods)
   Advantages:
   Simplicity: Intrinsic locks (also known as monitor locks) are easy to use with the synchronized keyword in Java.
   Automatic Lock Management: They automatically release the lock when the block or method exits, either normally or by throwing an exception.
2. ReentrantLock
   A ReentrantLock is a lock with the same basic behavior and semantics as the implicit monitor lock accessed using synchronized methods and statements, but with extended capabilities.

Advantages:
Flexibility: ReentrantLock provides more flexibility and features compared to intrinsic locks.
Fairness: It can be configured to use a fairness policy.
Try Locking: Provides tryLock() methods that allow for non-blocking attempts to acquire the lock.
Interruptible Locking: Allows a thread to be interrupted while waiting for the lock.
Condition Variables: Supports multiple Condition objects for more complex wait/notify mechanisms.

3. ReadWriteLock
   A ReadWriteLock maintains a pair of associated locks, one for read-only operations and one for write operations. The read lock may be held simultaneously by multiple reader threads as long as there are no writers. The write lock is exclusive.

Advantages:
Read-Write Separation: Allows multiple readers to access the resource simultaneously while restricting write access.
Improved Performance: Reduces contention by allowing concurrent reads.

4. StampedLock
   A StampedLock is a capability-based lock with three modes for controlling read/write access: Writing, Reading, and Optimistic Reading.

Advantages:
Optimistic Read Mode: Provides an optimistic read lock which can be validated before performing critical read operations, improving performance by avoiding unnecessary synchronization.
Efficient Read-Write Separation: Similar to ReadWriteLock but with better performance characteristics in some scenarios due to less overhead in optimistic reads.

5. Lock-Free and Wait-Free Algorithms
   These algorithms ensure that at least one thread makes progress in a finite number of steps (lock-free) or guarantee that every thread will complete its operation in a finite number of steps (wait-free).

Advantages:
High Throughput: Suitable for highly concurrent systems due to reduced contention and no need for locking mechanisms.
Scalability: Improves scalability in multicore systems by avoiding locks.
Example:
A typical example would be the use of Atomic classes, which provide lock-free thread-safe operations on single variables.
## 21.
Future represents the result of an asynchronous computation. It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result. However, Future has some limitations:

It cannot be manually completed.
It cannot handle multiple stages or steps of asynchronous tasks easily.
It lacks methods for chaining multiple tasks together.

CompletableFuture is an extension of Future that provides a more powerful and flexible framework for asynchronous programming. It allows you to manually complete the future and to chain multiple asynchronous tasks together in a more readable and maintainable way.

- Creating a CompletableFuture

CompletableFuture<T> supplyAsync(Supplier<T> supplier): Runs a task asynchronously and returns a CompletableFuture.
CompletableFuture<Void> runAsync(Runnable runnable): Runs a task asynchronously that does not return a result.
- Completing a CompletableFuture

complete(T value): Manually completes the CompletableFuture with a given value.
completeExceptionally(Throwable ex): Manually completes the CompletableFuture with an exception.
- Chaining Methods

thenApply(Function<? super T,? extends U> fn): Returns a new CompletableFuture that, when this future completes normally, is executed with this future's result as the argument to the supplied function.
thenAccept(Consumer<? super T> action): Returns a new CompletableFuture that, when this future completes normally, is executed with this future's result as the argument to the supplied action.
thenRun(Runnable action): Returns a new CompletableFuture that, when this future completes normally, is executed.

- Handling Exceptions
  exceptionally(Function<Throwable,? extends T> fn): Returns a new CompletableFuture that is completed when this CompletableFuture completes, with the result of the given function if this CompletableFuture completed exceptionally.
  handle(BiFunction<? super T, Throwable, ? extends U> fn): Returns a new CompletableFuture that is completed with the result of the given function of this CompletableFuture's result and exception.
## 22.
``` 
package com.chuwa.exercise.t08_multithreading;

public class PrintNumber1 {
    private static int n = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber());
        Thread t2 = new Thread(() -> printNumber());
        Thread t3 = new Thread(() -> printNumber());

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void printNumber() {
        int count = 10;
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrintNumber1.class.notifyAll();
    }
}
```

``` 
package com.chuwa.exercise.t08_multithreading;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeOrNotTest {

    @Test
    public void testGlobalCount() {
        new Thread(() -> Counter.setGlobalCount(1)).start();
        new Thread(() -> Counter.setGlobalCount(2)).start();
        new Thread(() -> Counter.setGlobalCount(3)).start();

        System.out.println(Counter.getGlobalCount());
    }

    @Test
    public void testCount() throws InterruptedException {

        Counter counter = new Counter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCount() throws InterruptedException {

        SyncronizedCounter counter = new SyncronizedCounter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCountWithTwoLocks() throws InterruptedException {

        SyncronizedCounterWithTwoLocks counter = new SyncronizedCounterWithTwoLocks();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testSyncronizedCountMethod() throws InterruptedException {

        SyncronizedCounterMethod counter = new SyncronizedCounterMethod();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    @Test
    public void testAtomicCount() throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }
}

class Counter {

    private Integer count = 0;
    private static Integer globalCount = 0;

    public static Integer getGlobalCount() {
        return globalCount;
    }

    public static void setGlobalCount(Integer globalCount) {
        Counter.globalCount = globalCount;
    }

    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count++;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class SyncronizedCounter {

    private Integer count = 0;

    final Object locker = new Object();

    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker) {
                count++;
            }
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker) {
                count--;
            }
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}


class SyncronizedCounterWithTwoLocks {

    private Integer count = 0;


    final Object locker1 = new Object();
    final Object locker2 = new Object();


    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker1) {
                count++;
            }
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            synchronized (locker2) {
                count--;
            }
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class SyncronizedCounterMethod {

    private Integer count = 0;


    public synchronized int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            count++;
        }
        return count;
    }

    public synchronized int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // 这两个方法必须用同一个锁
            count--;
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}

class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count.incrementAndGet();
        }
        return count;
    }

    public AtomicInteger decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count.decrementAndGet();
        }
        return count;
    }

    public AtomicInteger getCount() {
        return count;
    }
}
```
## 23.
``` 
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

public class OddEvenPrinter {
    private static final int MAX = 10;
    private int number = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();

        Thread t1 = new Thread(printer::printOdd, "Odd Thread");
        Thread t2 = new Thread(printer::printEven, "Even Thread");

        t1.start();
        t2.start();
    }

    private void printOdd() {
        synchronized (lock) {
            while (number < MAX) {
                while (number % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                lock.notify();
            }
        }
    }

    private void printEven() {
        synchronized (lock) {
            while (number < MAX) {
                while (number % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                lock.notify();
            }
        }
    }
}
```

```
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterReentrantLock {
    private static final int MAX = 10;
    private int number = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();

    public static void main(String[] args) {
        OddEvenPrinterReentrantLock printer = new OddEvenPrinterReentrantLock();

        Thread t1 = new Thread(printer::printOdd, "Odd Thread");
        Thread t2 = new Thread(printer::printEven, "Even Thread");

        t1.start();
        t2.start();
    }

    private void printOdd() {
        lock.lock();
        try {
            while (number < MAX) {
                while (number % 2 == 0) {
                    oddCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                evenCondition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private void printEven() {
        lock.lock();
        try {
            while (number < MAX) {
                while (number % 2 != 0) {
                    evenCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + " " + number);
                number++;
                oddCondition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}

```
## 24.
``` 
package com.chuwa.tutorial.t08_multithreading.c05_waitNotify;

public class RandomSequencePrinter {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printRange(1, 10), "Thread_1");
        Thread t2 = new Thread(() -> printRange(11, 20), "Thread_2");
        Thread t3 = new Thread(() -> printRange(21, 22), "Thread_3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                // Adding a small delay to simulate some work
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

```
## 25.
1. 
``` 
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // Asynchronously calculate the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            int sum = a + b;
            System.out.println("Sum: " + sum);
            return sum;
        });

        // Asynchronously calculate the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            int product = a * b;
            System.out.println("Product: " + product);
            return product;
        });

        // Wait for both computations to complete
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumFuture, productFuture);

        try {
            // Block and wait for both futures to complete
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```
2. 
``` 
public class MergedData {
    private Object products;
    private Object reviews;
    private Object inventory;

    // Getters and Setters

    public Object getProducts() {
        return products;
    }

    public void setProducts(Object products) {
        this.products = products;
    }

    public Object getReviews() {
        return reviews;
    }

    public void setReviews(Object reviews) {
        this.reviews = reviews;
    }

    public Object getInventory() {
        return inventory;
    }

    public void setInventory(Object inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "MergedData{" +
                "products=" + products +
                ", reviews=" + reviews +
                ", inventory=" + inventory +
                '}';
    }
}

```

``` 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiService {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static CompletableFuture<Object> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response, Object.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String productsUrl = "https://jsonplaceholder.typicode.com/posts";
        String reviewsUrl = "https://jsonplaceholder.typicode.com/comments";
        String inventoryUrl = "https://jsonplaceholder.typicode.com/users";

        CompletableFuture<Object> productsFuture = fetchData(productsUrl);
        CompletableFuture<Object> reviewsFuture = fetchData(reviewsUrl);
        CompletableFuture<Object> inventoryFuture = fetchData(inventoryUrl);

        CompletableFuture<MergedData> mergedDataFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                .thenApply(voidVar -> {
                    MergedData mergedData = new MergedData();
                    try {
                        mergedData.setProducts(productsFuture.get());
                        mergedData.setReviews(reviewsFuture.get());
                        mergedData.setInventory(inventoryFuture.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    return mergedData;
                });

        MergedData mergedData = mergedDataFuture.get();
        System.out.println(mergedData);
    }
}

```

3. 

``` 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiService {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(ApiService.class.getName());

    public static CompletableFuture<Object> fetchData(String url, Object defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response, Object.class);
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Failed to parse response from " + url, e);
                        return defaultValue;
                    }
                })
                .exceptionally(e -> {
                    logger.log(Level.SEVERE, "Failed to fetch data from " + url, e);
                    return defaultValue;
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String productsUrl = "https://jsonplaceholder.typicode.com/posts";
        String reviewsUrl = "https://jsonplaceholder.typicode.com/comments";
        String inventoryUrl = "https://jsonplaceholder.typicode.com/users";

        // Default values to return in case of an exception
        Object defaultProducts = "Default Products";
        Object defaultReviews = "Default Reviews";
        Object defaultInventory = "Default Inventory";

        CompletableFuture<Object> productsFuture = fetchData(productsUrl, defaultProducts);
        CompletableFuture<Object> reviewsFuture = fetchData(reviewsUrl, defaultReviews);
        CompletableFuture<Object> inventoryFuture = fetchData(inventoryUrl, defaultInventory);

        CompletableFuture<MergedData> mergedDataFuture = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                .thenApply(voidVar -> {
                    MergedData mergedData = new MergedData();
                    try {
                        mergedData.setProducts(productsFuture.get());
                        mergedData.setReviews(reviewsFuture.get());
                        mergedData.setInventory(inventoryFuture.get());
                    } catch (InterruptedException | ExecutionException e) {
                        logger.log(Level.SEVERE, "Failed to combine data", e);
                        throw new RuntimeException(e);
                    }
                    return mergedData;
                });

        MergedData mergedData = mergedDataFuture.get();
        System.out.println(mergedData);
    }
}
```
