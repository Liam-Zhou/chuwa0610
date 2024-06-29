## 2. Write a thread-safe singleton class
```java
public class Singleton {
    private static volatile Singleton INSTANCE;
    private Singleton() {}
    // 更简单，但由于方法级别的同步开销较大，效率较低。
    public static synchronized Singleon getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
    // 双重检查锁定更高效。适用于性能关键，尤其是方法被频繁调用的情况。
    public static Singleon getINSTANCE() {
        if (INSTANCE == null) {
            synchronized(this) {
                INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }
}
```


## 3. How to create a new thread(Please also consider Thread Pool approach)?
#### Extends Thread class
```java
class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Runner1: "+i);
        }
    }
}
public class Test {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();
    }
}
```
#### Implements Runnable interface 
```java
class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Runner1: "+i);
        }
    }
}
public class Test {
    public static void main(String[] args) {
        Thread2 t2 = new Thread2();
        t2.start();
    }
}
```
#### Implements Callable
```java
import java.util.concurrent.Callable;

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Start new thread using Callable";
    }
}

public class Test {
    public static void main(String[] args) {
        MyCallable cThread = new MyCallable();
        FutureTask<String> task = new FutureTask<>(cThread);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println(task.get()); // 打印Callable的返回值
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

```

#### Thread Pool
```java
public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread from ExecutorService is running...");
                }
            });
        }

        executor.shutdown();
    }
}
```

#### Lambda Expression
```java
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Lambda Runnable is running..."));
        thread.start();
    }
}

```


## 4. Difference between Runnable and Callable?

- Runnable has a run() method which does **not allow returning** something. 
- Callable has a call() method which allows **returning a value** of computation or task. 



## 5. What is the difference between t.start() and t.run()?

- t.start(): start a new thread to execute the method `run()`
- t.run(): just executes the method of the object in the current thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?
Runnable, because the thread still could `implements` other interfaces which is very flexible and extendable.

One class could only `extends` one class, so if we use `extends Thread`, the thread can not `extends` other class, 
which is very limited.

## 7. What are the thread status?
- **NEW** - create a thread but not start the execution.
- **RUNNABLE** - either running or ready for execution in thread pool to wait for resource allocation.
- **BLOCKED** - waiting to acquire a monitor lock to enter or re-enter an synchronized block/method.
- **WAITING** - waiting for some other thread's action, like interruption or notification
- **TIMED_WAITING** - waiting for some other thread to perform a specific action for a **specified** period.
- **TERMINATED** - thread completed its execution.

## 8. Demonstrate deadlock and how to resolve it in Java code.

```java
public class DeadlockDemo {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(100); }
                catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");

                try { Thread.sleep(100); }
                catch (InterruptedException e) {}

                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

// resolve deadlock: access resources with the same order
public class DeadlockResolvedDemo {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(100); }
                catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: locked resource 1");

                try { Thread.sleep(100); }
                catch (InterruptedException e) {}

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

Use wait() and notify() method.
```java
public class OddEventPrinter {
   private static final Object monitor = new Object();
   private static int value = 1;

   public static void main(String[] args) {
      PrintRunnable runnable = new PrintRunnable();
      new Thread(runnable).start(); // t0
      new Thread(runnable).start(); // t1
   }

   static class PrintRunnable implements Runnable {
      @Override
      public void run(){
         synchronized(monitor) {
            while(value <= 10) {
               System.out.println(Thread.currentThread().getName() + ": " + value++);
               monitor.notifyAll();
               try {
                  monitor.wait();
               } catch (InterruptedException e){
                  e.printStackTrace();
               }
            }
         }
      }
   }
}
```

## 10. What’s the difference between class lock and object lock?

- Scope: 
  - An object lock is associated with an instance of a class. When a thread acquires an object lock, it can only access synchronized methods or blocks that are associated with that particular object instance.
  - A class lock is associated with the Class object corresponding to a given class. When a thread acquires a class lock, it can access static synchronized methods or blocks that are associated with the class itself, rather than any particular instance.
- Usage: 
  - Object locks are used when you need to synchronize non-static methods or blocks to ensure that only one thread can access a method or block of code on a specific instance of the class at a time.
  - Class locks are used when you need to synchronize static methods or blocks to ensure that only one thread can access a static method or block of code on a class level at a time.
- Synchronization: 
  - When you use the synchronized keyword on a non-static method or synchronize on this, you are using an object lock.
  - When you use the synchronized keyword on a static method or synchronize on the Class object, you are using a class lock.
  
## 11. What is join() method?

The join() method in Java is used to pause the execution of the current thread until the thread on which join() was called has finished executing.
This is particularly useful when you want to ensure that a thread has completed its task before the main program or another thread continues.

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class TestJoin {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.start();
        try {
            t1.join(); // t1 will complete before t2 starts
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        t2.start();
        try {
            t2.join(); // t2 will complete before t3 starts
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        t3.start();
        try {
            t3.join(); // main thread waits for t3 to complete
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("All threads have finished executing");
    }
}
```

## 12. what is yield() method?

- yield() method is used to pause current thread and give the processor a hint that it can switch to executing other threads of the same priority.
- yield() is useful in scenarios where threads with similar priorities need to share the CPU resources fairly, but its usage should be handled with caution as it depends on the JVM and operating system's scheduler implementation.
```java
// t1 and t2 will execute alternately
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            Thread.yield(); // Hint to the thread scheduler
        }
    }
}

public class TestYield {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();
    }
}
```
## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

#### What is ThreadPool?
A ThreadPool in Java is a pool of worker threads that efficiently manage and execute tasks. It is a part of the java.util.concurrent package and helps in managing a large number of tasks by reusing a fixed number of threads, thus reducing the overhead of creating and destroying threads. This approach leads to better performance and resource utilization.

#### How many types of ThreadPool?
FixedThreadPool:
- A fixed number of threads are created and reused.
- Ideal for a known and constant number of tasks.

CachedThreadPool:
- Creates new threads as needed but reuses previously created threads when available. 
- Suitable for applications that launch many short-lived tasks.

SingleThreadExecutor:
- A single worker thread to execute tasks sequentially. 
- Useful when tasks need to be executed in order.

ScheduledThreadPool:

- A fixed-size pool that supports periodic and delayed task execution. 
- Useful for scheduled tasks.

#### What is the TaskQueue in ThreadPool?
The TaskQueue in a ThreadPool is a queue that holds tasks before they are executed by the threads in the pool. When a task is submitted to a thread pool, it is placed in the task queue, and the pool's worker threads retrieve tasks from this queue and execute them. The type of task queue used can affect the behavior and performance of the thread pool. 

There are a few common types of task queues:

LinkedBlockingQueue:
- An optionally-bounded blocking queue. 
- Used in FixedThreadPool and SingleThreadExecutor.

SynchronousQueue:
- A blocking queue where each insert operation must wait for a corresponding remove operation by another thread, and 
vice versa.
- Used in CachedThreadPool.

DelayedQueue:
- A blocking queue of delayed elements, where an element can only be taken when its delay has expired.
- Used in ScheduledThreadPool.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

#### Which Library is used to create ThreadPool?
In Java, the ThreadPoolExecutor class from the java.util.concurrent package is commonly used to create and manage thread pools. It provides a flexible and powerful way to manage threads for executing asynchronous tasks.

#### Which Interface provide main functions of thread-pool?
The main interface that provides the essential functions for a thread pool is the ExecutorService interface. This interface extends Executor and provides methods to manage and control the execution of tasks asynchronously.

## 15. How to submit a task to ThreadPool?

- Create a ThreadPool
- Define the Task
- Submit the Task
- Shutdown the Executor

```java
import java.util.concurrent.*;
// Define the Task
class MyTask implements Runnable {
    private int id;
    MyTask(int id) {
        this.id = id;
    }
    @Override
    public void run() {
      // Code to execute asynchronously in the thread pool
      System.out.println("taskId: "+ id+ ", Task executed by thread: " + Thread.currentThread().getName());
    }
}
public class ThreadPoolExample {

    public static void main(String[] args) {
        // Create a ThreadPool：创建一个固定大小为5的线程池 
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交任务给线程池执行
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            // Submit the Task
            executor.submit(new MyTask(taskId));
        }

        // Shutdown the Executor: 关闭线程池
        executor.shutdown();

        // 等待所有任务完成，最多等待5秒
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 处理中断异常
            e.printStackTrace();
        } finally {
            // 如果线程池还未关闭，强制关闭
            if (!executor.isTerminated()) {
                System.err.println("Cancel non-finished tasks");
                executor.shutdownNow();
            }
        }
    }
}


```
## 16. What is the advantage of ThreadPool?

- Improved Performance: reusing threads instead of creating new ones for each task. This reuse reduces the overhead 
  of thread creation.
- Resource Management: Thread pools manage the number of threads used for executing tasks. They allow you to control the maximum number of threads running concurrently, preventing resource exhaustion (like running out of memory or excessive CPU usage) that could occur if threads were created without limit.
- Concurrency Limitation: By limiting the number of concurrent threads, thread pools help avoid situations where too many tasks are executed simultaneously, which could degrade overall system performance due to excessive context switching or contention for resources. 
- Task Queueing: Tasks submitted to a thread pool are typically queued up if all threads are busy. This queuing mechanism ensures that tasks are executed in the order they are received, or based on priority, which helps in managing workload efficiently.

## 17. Difference between shutdown() and shutdownNow() methods of executor
- Behavior:
  - `shutdown()`: Initiates an orderly shutdown of the ExecutorService.
  - `shutdownNow()`: Attempts to stop the ExecutorService immediately.
- Effect:
  - `shutdown()`: The executor service will no longer accept new tasks for execution. However, it will continue to execute
     any tasks that have already been submitted before the shutdown was initiated.
  - `shutdownNow()`: Prevents the executor service from accepting new tasks and attempts to cancel the execution of tasks that have not yet started or are currently executing.
- Returns: 
  - `shutdown()`: After calling shutdown(), subsequent calls to isShutdown() will return true, and tasks may 
    continue to run until all tasks have completed.
  - `shutdownNow()`: Returns a list of tasks that were awaiting execution and were not run.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

#### 1. Atomic Classes:
The atomic classes provide a lock-free and thread-safe environment or programming on a single variable. It also supports atomic operations.

#### 2. Types of Atomic Classes:
- AtomicInteger: Provides atomic operations on an int variable. 
- AtomicLong: Provides atomic operations on a long variable. 
- AtomicBoolean: Provides atomic operations on a boolean variable. 
- AtomicReference: Provides atomic operations on a generic reference variable. 
- AtomicIntegerArray: Provides atomic operations on an array of int variables. 
- AtomicLongArray: Provides atomic operations on an array of long variables. 
- AtomicReferenceArray: Provides atomic operations on an array of generic reference variables.

### 3. Code Example and methods:
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

  public static void main(String[] args) {
    AtomicInteger atomicInt = new AtomicInteger(0);

    // Increment and get the updated value atomically
    int incrementedValue = atomicInt.incrementAndGet();
    System.out.println("Incremented value: " + incrementedValue);

    // Get the current value
    int currentValue = atomicInt.get();
    System.out.println("Current value: " + currentValue);

    // Compare and set operation
    int expectedValue = 1;
    int newValue = 10;
    boolean updated = atomicInt.compareAndSet(expectedValue, newValue);
    if (updated) {
      System.out.println("Value updated successfully. New value: " + atomicInt.get());
    } else {
      System.out.println("Value was not updated. Current value: " + atomicInt.get());
    }
  }
}

```
- incrementAndGet(): Atomically increments the current value by one and returns the updated value.
- get(): Returns the current value of the AtomicInteger.
- compareAndSet(expectedValue, newValue): Atomically compares the current value with expectedValue. If they are equal,
  it updates the value to newValue and returns true; otherwise, it returns false.

### 4. When to use Atomic Classes:
Atomic classes are useful in scenarios where shared variables are accessed and updated by multiple threads concurrently. They provide thread-safe operations without the need for explicit synchronization using synchronized blocks or methods. 

Here are common scenarios where atomic classes are beneficial:

- Counters and accumulators: Use AtomicInteger or AtomicLong for counters or accumulators that are updated by 
multiple threads.
- Flags and status indicators: Use AtomicBoolean for flags or status indicators that control the flow of execution 
  across threads.
- Compare-and-set operations: Use compareAndSet methods for atomic updates based on a comparison, which can be useful 
  in lock-free algorithms and concurrent data structures.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
#### What is the concurrent collections?
Concurrent collections in Java are data structures that are designed to be safely used in concurrent (multi-threaded) environments without requiring external synchronization. They are part of the java.util.concurrent package and provide thread-safe implementations of common data structures, allowing multiple threads to access and modify them concurrently without explicit synchronization.

####  Can you list some concurrent data structure (Thread-safe)
- ConcurrentHashMap: A hash table supporting full concurrency of retrievals and adjustable expected concurrency for 
  updates. It provides thread-safe operations without the need for synchronization.
- ConcurrentLinkedQueue: A thread-safe variant of LinkedList in which all of the operations are designed to be 
  executed atomically. 
- ConcurrentLinkedDeque: A concurrent variant of LinkedList in which elements can be added or removed from both ends 
  concurrently. 
- ConcurrentSkipListMap: A concurrent, sorted map implementation based on a skip list. It provides expected average 
  log(n) time cost for the containsKey, put, and remove operations and their variants.
- CopyOnWriteArrayList: A thread-safe variant of ArrayList in which all mutative operations (add, set, remove, etc.) 
  are implemented by making a fresh copy of the underlying array. It is suitable for applications where traversal operations are more frequent than modification operations.

## 20. What kind of locks do you know? What is the advantage of each lock?

1. Synchronized Blocks and Methods:
   - Simple to use and understand. 
   - Ensures exclusive access to shared resources by acquiring an intrinsic lock (monitor) associated with the object. 
   - Provides mutual exclusion, preventing concurrent execution of synchronized blocks/methods by multiple threads.
2. ReentrantLock:
   - More flexible than synchronized blocks/methods, offering advanced features like interruptible lock acquisition, 
   timed lock attempts, and support for fair locking.
   - Allows finer-grained control over locking behavior, such as specifying lock acquisition order.
   - Supports condition variables (Condition interface) for enabling threads to wait for a specific condition to be 
     satisfied.
3. ReadWriteLock (ReentrantReadWriteLock):
   - Separates locks for read and write operations, allowing multiple threads to read simultaneously (shared access) 
   while ensuring exclusive access for writing.
   - Can improve concurrency in scenarios where reads are more frequent than writes, as multiple threads can access 
     data concurrently for reading.
4. StampedLock:
   - Supports optimistic locking in addition to read and write locking modes.
   - Optimistic reads do not block writers, potentially improving throughput in read-heavy scenarios where writes are 
     infrequent.
   - Provides methods for converting between different lock modes (optimistic, read, write).
5. Semaphore:
   - Controls access to a shared resource through a set of permits.
   - Can be used to limit the number of concurrent threads accessing a resource, beyond mutual exclusion.
   - Supports both blocking and non-blocking semaphore operations (acquire() and release()).
6. CountDownLatch:
   - Allows one or more threads to wait until a set of operations being performed in other threads completes.
   - Useful for coordinating the startup or shutdown of multiple threads, ensuring that certain operations do not 
     proceed until all necessary prerequisites are met.
7. CyclicBarrier:
   - Facilitates synchronization among a fixed number of threads, where each thread waits at a barrier until all 
   threads have reached it.
   - Useful for tasks that are divided into phases, ensuring all threads complete one phase before proceeding to the 
     next.


## 21. What is future and completableFuture? List some main methods of ComplertableFuture.

- Future is a blocking way of multithreading. CompletableFuture is a non-blocking way of multithreading

#### Main Methods of CompletableFuture:
- supplyAsync()
- thenAccept()
- thenCompose()
- handle()
- complete()
- cancel()
