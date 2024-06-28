 # HomeWork 5

 ## Yirun Wang

 ### 2. Write a thread-safe singleton class
```
public class Singleton{
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if(instance == null) {
            sychronized(this) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### 3. How to create a new thread(Please also consider ThreadPool approach)?
1. extends Thread Class
```
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread using extends thread");
    }
}
Thread t = new MyThread();
t.start();
```

2. Implements Runnable
```
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Start new thread using Runnable");
    }
}
Thread t2 = new Thread(new MyRunnable());
```

3. Implements Callable
```
public class MyCallable implements Callable<String>{
    @Override 
    public String call() throws Exception {
        return "Start new thread using Callable";
    }
}

4. ThreadPool
```
public class ThreadPoolExample{
    public static void main(String) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println("Executing task in: " + Thread.currentThread().getName());
        }

        executor.submit(task);
        executor.shutdown();
    }
}
```
### 4. Difference between Runnable and Callable?
- Runnable is a functional interface with a single method run(), and it has no return. Can be used with Thread, Executor and ExecutorService.
- Callable is a generic interface with a single method call(). It has return and can throw checked exceptions. Can be used with ExecutorService to submit and obtain Future objects.
```

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task = () -> {
            System.out.println("Callable Task executed by: " + Thread.currentThread().getName());
            return "Task result";
        };

        Future<String> future = executor.submit(task);

        try {
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
```

### 5. What is the difference between t.start() and t.run()?
- t.start() starts a new thread to execute the task. When you call start(), a new thread of execution is created, and the run() method of the Thread object (or the Runnable it encapsulates) is invoked in this new thread.
- This method allows the Java Virtual Machine (JVM) to perform necessary thread initialization and scheduling. The start() method does not block; it returns immediately after starting the thread, which then runs asynchronously.

- t.run() execute the task in the current thread, without creating a new thread. When you call run() directly, it does not start a new thread. Instead, it runs the run() method in the current thread, just like a regular method call.


### 6. Which way of creating threads is better: Thread class or Runnable interface?

- By extending Thread class, you can't extend other classes. But with Runnable interface, you can still extend another class if needed.
- The Runnable class contains only the code run() that defines the task to be performed, while the Thread class handles thread management. This separation leads to cleaner, more modular code.
- ExecutorService, work with Runnable and Callable rather than Thread objects. Implementing Runnable allows your task to be used with various thread-handling utilities provided by the java.util.concurrent package, such as ExecutorService, making your code more versatile and future-proof.
```
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable is running: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            MyRunnable myRunnable = new MyRunnable();
            executor.submit(myRunnable);
        }

        executor.shutdown();
    }
}
```

### 7. What are the thread statuses?
1. NEW
A thread that has been created but has not yet started.
```
Thread t = new Thread(new MyRunnable());
```
2. RUNNABLE
A thread that is ready to run and is either running or waiting for the CPU to become available.
```
t.start();
```
3. BLOCKED
A thread that is blocked waiting for a monitor lock (due to synchronized block/method).
```
synchronized (someObject) {
    // t1 holds the lock
}
```
4. WAITING
A thread that is waiting indefinitely for another thread to perform a particular action.
```
//A thread calls wait() on an object and waits until another thread calls notify() or notifyAll() on that object.
synchronized (someObject) {
    someObject.wait();
    // t is in WAITING state
}
```
5. TIMED_WAITING
A thread that is waiting for another thread to perform a particular action for up to a specified waiting time.
```
Thread.sleep(1000);
```
6. TERMINATED
A thread that has exited after its run method has completed or terminated due to an uncaught exception.

### 8. Demondtrate dead lock and how to resolve it in Java code.
Thread t1 holds the lock on resource1 and waits for resource2.
Thread t2 holds the lock on resource2 and waits for resource1.
Both threads are waiting for each other to release the locks, resulting in a deadlock.
```
public class DeadlockDemo {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

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
We can adjust the order so that there is no circular await.
Use tryLock with timeouts to avoid indefinite waiting.
```
public class TimeoutAvoidance {
    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 1: locked lock1");
                        Thread.sleep(50);
                        if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 1: locked lock2");
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 2: locked lock2");
                        Thread.sleep(50);
                        if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 2: locked lock1");
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
```
### 9. How do threads communicate each other?
- wait()
    Causes the current thread to wait until another thread calls notify() or notifyAll() on the same object.
- notify()
    Wakes up a single thread that is waiting on the object's monitor.
- notifyAll()
    Wakes up all threads that are waiting on the object's monitor.

### 10. What’s the difference between class lock and object lock?
- Object Lock
An object lock is associated with an instance of a class. When a thread acquires an object lock, it gains exclusive access to the synchronized instance methods of that particular object. Other threads cannot call any synchronized instance methods on the same object until the lock is released.
Applies to a specific instance of a class.
Used when you need to synchronize access to instance methods and fields.
Prevents multiple threads from modifying the state of a specific object concurrently.
```
public class ObjectLockExample {
    public synchronized void instanceMethod() {
        System.out.println("Instance method called by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simulating some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ObjectLockExample example = new ObjectLockExample();
        
        Thread t1 = new Thread(example::instanceMethod, "Thread-1");
        Thread t2 = new Thread(example::instanceMethod, "Thread-2");
        
        t1.start();
        t2.start();
    }
}
```
- Class Lock
A class lock is associated with the Class object representing a class. When a thread acquires a class lock, it gains exclusive access to all synchronized static methods of that class. Other threads cannot call any synchronized static methods of the same class until the lock is released.
Applies to the entire class, affecting all instances and static methods.
Used when you need to synchronize access to static methods and static fields.
Prevents multiple threads from modifying the state of the class-level data concurrently.
```
public class ClassLockExample {
    public static synchronized void staticMethod() {
        System.out.println("Static method called by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simulating some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(ClassLockExample::staticMethod, "Thread-1");
        Thread t2 = new Thread(ClassLockExample::staticMethod, "Thread-2");
        
        t1.start();
        t2.start();
    }
}
```
### 11. What is join() method?
join() is used to pause the execution of the current thread until the thread on which join() was called has finished executing. This method provides a way for one thread to wait for the completion of another thread.
```
public class JoinExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 is starting...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 is finished.");
        });

        thread1.start();

        try {
            thread1.join(); // Wait for thread1 to finish
            System.out.println("Thread 1 has finished, continuing with main thread.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finished.");
    }
}
```

### 12. what is yield() method?
yield() is used to hint to the thread scheduler that the current thread is willing to yield its current use of the processor. This means that the current thread pauses its execution to allow other threads of the same priority to execute. However, there is no guarantee that the thread scheduler will actually pause the current thread or that it will give another thread a chance to run.
```
public class YieldExample {
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Producer thread: " + i);
                Thread.yield(); // Hint to the scheduler to yield the processor
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Consumer thread: " + i);
                Thread.yield(); // Hint to the scheduler to yield the processor
            }
        });

        producer.start();
        consumer.start();
    }
}
```

### 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A ThreadPool is a collection of pre-initialized reusable threads that can be used to execute tasks concurrently. Instead of creating and destroying threads for every task, a thread pool manages a set of worker threads that can be reused to perform multiple tasks.
1. Fixed Thread Pool
    A fixed number of threads are created and reused.
    If all threads are busy, new tasks are queued until a thread becomes available.
    `ExecutorService executor = Executors.newFixedThreadPool(5);`
2. Cached Thread Pool
    Creates new threads as needed but reuses previously constructed threads when available.
    Suitable for executing many short-lived asynchronous tasks.
    `ExecutorService executor = Executors.newCachedThreadPool();`
3. Single Thread Executor
    A single worker thread executes tasks sequentially.
    Ensures tasks are executed in the order they are submitted.
    `ExecutorService executor = Executors.newSingleThreadExecutor();`
4. Schedule Thread Pool
    A thread pool that can schedule tasks to run after a given delay or to execute periodically.
    `ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);`


The TaskQueue in a thread pool is a queue that holds tasks waiting to be executed by the pool's threads. When a task is submitted to the thread pool, it is placed in the task queue if no threads are available. As soon as a thread becomes available, it retrieves a task from the task queue and executes it.
- LinkedBlockingQueue: Often used for fixed thread pools. It can grow indefinitely, accommodating as many tasks as needed.
- SynchronousQueue: Used for cached thread pools. Each insertion operation must wait for a corresponding removal operation.
- DelayedWorkQueue: Used for scheduled thread pools. It supports delayed and periodic task execution.
```
import java.util.concurrent.*;

public class SynchronousQueueExample {
    public static void main(String[] args) {
        // Create a thread pool with SynchronousQueue
        ExecutorService executor = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            60L,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>()
        );

        // Submit tasks to the thread pool
        for (int i = 1; i <= 5; i++) {
            Runnable task = new Task(i);
            executor.submit(task);
        }

        // Shutdown the executor
        executor.shutdown();
    }

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task " + taskId + " is completed on " + Thread.currentThread().getName());
        }
    }
}
```

### 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
- Library: java.util.concurrent
This package contains utility classes and interfaces for concurrent programming in Java.
- Interface: ExecutorService
The ExecutorService interface extends the Executor interface and provides methods to manage the termination and tracking of progress of asynchronous tasks.

### 15. How to submit a task to ThreadPool?
- The submit() method is part of the ExecutorService interface. It allows you to submit a task for execution and returns a Future object, which can be used to retrieve the result of the task or check if the task has completed.

- The execute() method is part of the Executor interface, which ExecutorService extends. It allows you to submit a Runnable task for execution. This method does not return a Future and thus does not allow you to get the result of the task or check its completion status.

### 16. What is the advantage of ThreadPool?
Using thread pools generally offers better performance and resource management compared to creating single threads directly. Thread pools control the number of concurrent tasks, reduce the overhead of thread creation and destruction, and improve performance. 
Moreover, thread pools allow task queuing for pending execution, automatically manage thread lifecycles, and provide more flexible error handling mechanisms. However, in some simple scenarios, using a single thread might be more straightforward.

### 17. Difference between shutdown() and shutdownNow() methods of executor
shutdown()
- Initiates an orderly shutdown of the executor service.
- Allows currently executing tasks to complete.
- Does not accept any new tasks, but tasks that are in the queue will still be executed.
- The method itself does not block, but you can use methods like awaitTermination() to wait for the completion of the shutdown process.
shutdownNow()
- Attempts to stop all actively executing tasks and halts the processing of waiting tasks.
- Attempts to interrupt all actively executing tasks.
- Does not execute tasks that are in the queue and returns a list of these tasks.
- The method itself does not block, but you can use methods like awaitTermination() to wait for the completion of the shutdown process.

### 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes in Java are part of the java.util.concurrent.atomic package and provide a way to perform atomic operations on single variables without using synchronization. These classes ensure that operations on variables are thread-safe and can be performed atomically, meaning they are performed as a single, indivisible operation.
- AtomicInetger
```
public class AtomicIntegerExample {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        // Increment and get the value
        System.out.println("Incremented Value: " + atomicInt.incrementAndGet());

        // Get the current value
        System.out.println("Current Value: " + atomicInt.get());

        // Compare and set
        boolean updated = atomicInt.compareAndSet(1, 10);
        System.out.println("Was value updated? " + updated);
        System.out.println("New Value: " + atomicInt.get());
    }
}
```
- AtomicBoolean
```
public class AtomicBooleanExample {
    public static void main(String[] args) {
        AtomicBoolean atomicBool = new AtomicBoolean(false);

        // Set to true
        atomicBool.set(true);

        // Get the current value
        System.out.println("Current Value: " + atomicBool.get());

        // Compare and set
        boolean updated = atomicBool.compareAndSet(true, false);
        System.out.println("Was value updated? " + updated);
        System.out.println("New Value: " + atomicBool.get());
    }
}
```
- AtomicLong
```
public class AtomicLongExample {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0L);

        // Increment and get the value
        System.out.println("Incremented Value: " + atomicLong.incrementAndGet());

        // Get the current value
        System.out.println("Current Value: " + atomicLong.get());

        // Compare and set
        boolean updated = atomicLong.compareAndSet(1L, 10L);
        System.out.println("Was value updated? " + updated);
        System.out.println("New Value: " + atomicLong.get());
    }
}
```

### 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections in Java are part of the java.util.concurrent package and are designed to handle multiple threads accessing and modifying them simultaneously without causing inconsistent state or data corruption. 
- ConcurrentHashMap
A thread-safe implementation of the HashMap that allows concurrent read and write operations without the need for explicit synchronization.
Uses segments or internal locks to allow multiple threads to read and write concurrently.
- ConcurrentLinkedQueue
An unbounded thread-safe queue based on linked nodes.
Implements a non-blocking algorithm using CAS (Compare-And-Swap) for concurrent access.
- CopyOnWriteArrayList
A thread-safe variant of ArrayList in which all mutative operations (e.g., add, set) are implemented by making a fresh copy of the underlying array.
Ideal for use cases where read operations are more frequent than write operations.
- LinkedBlockingQueue, ArrayBlockingQueue
Provides thread-safe operations for adding, removing, and inspecting elements.
- ConcurrentSkipListMap
A scalable concurrent NavigableMap implementation based on a skip list.
Provides expected average log(n) time cost for the containsKey, get, put, and remove operations and their variants.

### 20. What kind of locks do you know? What is the advantage of each lock?
- ReentrantLock
A thread can re-acquire the lock it already holds.
Supports more sophisticated features like fairness, timed lock attempts, and interruptible lock waits.
```
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```
- ReadWriteLock
Allows multiple readers or one writer at a time, improving performance in read-heavy scenarios.
```
ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
Lock readLock = readWriteLock.readLock();
Lock writeLock = readWriteLock.writeLock();
```
- Condition Variables
Associated with Lock instances.
condition.await()： Causes the current thread to wait until it is signalled or interrupted.
condition.signal()： Wakes up one waiting thread.
condition.signalAll()： Wakes up all waiting threads.
```
class TaskQueue{
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();
    private CopyOnWriteArrayList<String> lsit = new CopyOnWriteArrayLis<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}
```

- StampedLock
Optimistic Read Locking: Allows for optimistic reads, which can be faster than traditional read locks.
```
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private final StampedLock stampedLock = new StampedLock();
    private int sharedData = 0;

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();

        // Start a writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                example.writeData(i);
                try {
                    Thread.sleep(100); // Simulate write time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start reader threads
        Runnable readerTask = () -> {
            for (int i = 0; i < 5; i++) {
                example.optimisticReadData();
                try {
                    Thread.sleep(100); // Simulate read time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Thread reader1 = new Thread(readerTask);
        Thread reader2 = new Thread(readerTask);

        writer.start();
        reader1.start();
        reader2.start();
    }

    // Method to read data using optimistic read
    public void optimisticReadData() {
        long stamp = stampedLock.tryOptimisticRead();
        int currentData = sharedData; // Read the shared data
        if (!stampedLock.validate(stamp)) { // Validate the read
            stamp = stampedLock.readLock();
            try {
                currentData = sharedData; // Re-read the shared data
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " reads: " + currentData);
    }

    // Method to write data
    public void writeData(int data) {
        long stamp = stampedLock.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + " writes: " + data);
            sharedData = data;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
}
```

### 21. What is future and completableFuture? List some main methods of ComplertableFuture.
Future represents the result of an asynchronous computation. Provides methods to check if the computation is complete, wait for its completion, and retrieve the result.
- Methods like get() block until the computation is complete.
- No support for chaining: You can't easily chain multiple asynchronous tasks together.
- No support for handling exceptions: Limited to get() throwing exceptions if the computation failed.

get(): get result. Block until the computation is complete.
get(long timeout, TimeUnit unit): get result but only wait limited time.
cancel(boolean mayInterruptIfRunning): cancel current.
isDone(): check if the current computation is done.

CompletableFuture supports non-blocking operations and allows chaining of multiple asynchronous tasks. Part of the java.util.concurrent package since Java 8.
- Supports non-blocking methods.
- Supports chaining of asynchronous tasks.
- Provides methods to handle exceptions in a fluent style.
- Allows manual completion of the future.

1. Creating CompletableFuture
    CompletableFuture.supplyAsync(Supplier<U> supplier) : Executes the given task asynchronously and returns the result.
    CompletableFuture.runAsync(Runnable runnable) : Executes the given task asynchronously, without returning a value.
2. Result Processing and Transformation
    CompletableFuture.thenApply(Function<T, U> fn) : Processes the return value and transforms it into another type
    CompletableFuture.thenAccept(Consumer<T> action) : Processes the return value without returning a new CompletableFuture
    CompletableFuture.thenRun(Runnable action) : Ignores the return value and runs a runnable
3. Combination and Linking
    CompletableFuture.thenCompose(Function<T, CompletionStage<U>> fn) : Links another CompletableFuture and passes the result to the next task
    CompletableFuture.thenCombine(CompletionStage<U> other, BiFunction<T, U, V> fn) : Combines the results of two CompletableFutures and returns a new CompletableFuture
4. Exception Handling
    CompletableFuture.exceptionally(Function<Throwable, T> fn) : Handles exceptions and returns an alternative value
    CompletableFuture.handle(BiFunction<T, Throwable, U> fn) : Handles exceptions while allowing access to the result value (if any)
