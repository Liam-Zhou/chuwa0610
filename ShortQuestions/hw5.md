# 1. Write a thread-safe singleton class 
```
public class Singleton {

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialize your instance variables here
    }

    // Static inner helper class responsible for holding the singleton instance
    private static class SingletonHelper {
        // The static initializer is thread-safe and ensures that the instance is created only once.
        private static final Singleton INSTANCE = new Singleton();
    }

    // Public method to provide access to the singleton instance
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create multiple threads that attempt to get the Singleton instance
        Runnable task = () -> {
            Singleton singleton = Singleton.getInstance();
            singleton.showMessage();
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
```

# 2. How to create a new thread(Please also consider Thread Pool approach)?
- Direct Creation of Threads
    - Using the Thread Class
        ```
        public class DirectThreadExample {
            public static void main(String[] args) {
                // Create a new thread using a lambda expression
                Thread thread = new Thread(() -> {
                    System.out.println("Hello from thread " + Thread.currentThread().getName());
                });
                // Start the thread
                thread.start();
                // Main thread continues to run
                System.out.println("Hello from main thread " + Thread.currentThread().getName());
            }
        }
        ```
    - Using the Runnable Interface
        ```
        public class RunnableExample {
            public static void main(String[] args) {
                // Create a Runnable instance
                Runnable task = () -> {
                    System.out.println("Hello from Runnable thread " + Thread.currentThread().getName());
                };
        
                // Create a new thread with the Runnable instance
                Thread thread = new Thread(task);
        
                // Start the thread
                thread.start();
        
                // Main thread continues to run
                System.out.println("Hello from main thread " + Thread.currentThread().getName());
            }
        }
        ```
- Using Thread Pool
    - Using Executors.newFixedThreadPool
        ```
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        public class ThreadPoolExample {
            public static void main(String[] args) {
                // Create a thread pool with 3 threads
                ExecutorService executorService = Executors.newFixedThreadPool(3);
        
                // Define a task
                Runnable task = () -> {
                    System.out.println("Task executed by " + Thread.currentThread().getName());
                };
        
                // Submit tasks to the thread pool
                for (int i = 0; i < 5; i++) {
                    executorService.submit(task);
                }
        
                // Shut down the executor service
                executorService.shutdown();
            }
        }
        ```
    - Using Executors.newCachedThreadPool
        ```
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        
        public class CachedThreadPoolExample {
            public static void main(String[] args) {
                // Create a cached thread pool
                ExecutorService executorService = Executors.newCachedThreadPool();
        
                // Define a task
                Runnable task = () -> {
                    System.out.println("Task executed by " + Thread.currentThread().getName());
                };
        
                // Submit tasks to the thread pool
                for (int i = 0; i < 5; i++) {
                    executorService.submit(task);
                }
        
                // Shut down the executor service
                executorService.shutdown();
            }
        }
        ```

# 3. Difference between Runnable and Callable?
- Method Signature:
    - Runnable has a single method run that does not return any result and cannot throw checked exceptions.
    - Callable has a single method call that returns a result and can throw checked exceptions.

- Return Type:
    - Runnable cannot return a result.
    - Callable can return a result of a specified type.

- Exception Handling:
    - Runnable's run method cannot throw checked exceptions.
    - Callable's call method can throw checked exceptions.

- Usage with Executors:
    - Both Runnable and Callable can be used with ExecutorService.
    - ExecutorService.submit(Runnable) returns a Future<?> which can be used to check if the task is complete.
    - ExecutorService.submit(Callable<V>) returns a Future<V> which can be used to retrieve the result.

# 4. What is the difference between t.start() and t.run()?
- **t.start()**
    - Purpose: Starts a new thread of execution.
    - Functionality: 
        - When you call start() on a Thread object, it initiates the execution of the thread. 
        - The Java Virtual Machine (JVM) calls the run() method of the Thread object (or the Runnable it wraps) in a new thread.
        - This means the code in the run() method is executed concurrently in a separate call stack.

- **t.run()**
    - Purpose: Executes the run() method in the current thread.
    - Functionality:
        - When you call run() directly on a Thread object, it does not start a new thread.
        - The run() method is executed in the current thread’s call stack, just like a normal method call.

# 5. Which way of creating threads is better: Thread class or Runnable interface?
1. Separation of Task and Execution
    - Runnable Interface:
    Separation of Concerns: Implementing the Runnable interface separates the task from the thread that executes it. This means you can pass the same Runnable instance to multiple threads or executors, providing more flexibility and reuse.
    - Thread Class:
    Tightly Coupled: Extending the Thread class means the task is tightly coupled with the thread itself, limiting reusability.

2. Java's Single Inheritance Model
    - Runnable Interface:
    Multiple Inheritance: By implementing Runnable, your class can still extend another class, allowing for more flexible class designs.
    - Thread Class:
    Single Inheritance: Since Java only supports single inheritance, extending Thread means your class cannot extend any other class.

3. Flexibility with Executors
    - Runnable Interface:
    Compatibility with Executors: Runnable tasks can be easily submitted to an ExecutorService for execution, which is a more advanced and flexible way to manage threads.
    - Thread Class:
    Limited Use with Executors: While you can still use a Thread subclass with executors, it's less common and more awkward.

# 6. What are the thread statuses?
1. **New**: Thread created but not started.
2. **Runnable**: Thread ready to run or currently running.
3. **Blocked**: Thread blocked waiting for a monitor lock.
4. **Waiting**: Thread waiting indefinitely for another thread to perform an action.
5. **Timed Waiting**: Thread waiting for another thread to perform an action for a specified time.
6. **Terminated**: Thread has completed execution.

# 7. Demondtrate deadlock and how to resolve it in Java code.
Deadlock is a situation in which two or more threads are blocked forever, waiting for each other. This usually happens when two threads have a circular dependency on a pair of synchronized objects.
One common way to resolve deadlock is to impose a strict order on the locks and ensure that each thread acquires the locks in the same order. 

# 8. How do threads communicate each other?
In Java, threads can communicate with each other primarily using shared objects and certain methods from the Object class, such as wait(), notify(), and notifyAll(). These methods provide a mechanism for one thread to pause its execution until another thread notifies it to resume, allowing for coordinated actions between threads.  
Example:
- producer class
    ```
    import java.util.Queue;
    import java.util.LinkedList;
    
    class Producer implements Runnable {
        private final Queue<Integer> sharedQueue;
        private final int MAX_SIZE;
    
        public Producer(Queue<Integer> sharedQueue, int size) {
            this.sharedQueue = sharedQueue;
            this.MAX_SIZE = size;
        }
    
        @Override
        public void run() {
            int value = 0;
            while (true) {
                synchronized (sharedQueue) {
                    while (sharedQueue.size() == MAX_SIZE) {
                        try {
                            System.out.println("Queue is full, producer thread waiting for consumer to consume.");
                            sharedQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Produced: " + value);
                    sharedQueue.add(value++);
                    sharedQueue.notifyAll();
                }
            }
        }
    }
    ```
- consumer class 
    ```
    import java.util.Queue;
    class Consumer implements Runnable {
        private final Queue<Integer> sharedQueue;
    
        public Consumer(Queue<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }
    
        @Override
        public void run() {
            while (true) {
                synchronized (sharedQueue) {
                    while (sharedQueue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty, consumer thread waiting for producer to produce.");
                            sharedQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = sharedQueue.poll();
                    System.out.println("Consumed: " + value);
                    sharedQueue.notifyAll();
                }
            }
        }
    }
    ```

# 9. What’s the difference between class lock and object lock?
- Object Lock
    - Definition: An object lock is associated with an instance of a class. When a thread holds an object lock, other threads are blocked from executing synchronized methods or blocks that require that same object's lock.
    - Scope: The lock is specific to the particular instance of the object.
    - Usage: Synchronized instance methods or synchronized blocks on the instance.
    ```
    public class ObjectLockExample {
        public synchronized void instanceMethod() {
            // This method is synchronized on the instance (object lock)
            System.out.println("Instance method synchronized on object lock");
        }
    
        public void anotherInstanceMethod() {
            synchronized (this) {
                // This block is synchronized on the instance (object lock)
                System.out.println("Synchronized block on object lock");
            }
        }
    }
    ```
- Class Lock
    - Definition: A class lock is associated with the Class object of a class. When a thread holds a class lock, other threads are blocked from executing static synchronized methods or static synchronized blocks of that class.
    - Scope: The lock is specific to the Class object itself and is shared among all instances of that class.
    - Usage: Synchronized static methods or synchronized blocks on the Class object.
    ```
    public class ClassLockExample {
        public static synchronized void staticMethod() {
            // This method is synchronized on the Class object (class lock)
            System.out.println("Static method synchronized on class lock");
        }
    
        public void anotherStaticMethod() {
            synchronized (ClassLockExample.class) {
                // This block is synchronized on the Class object (class lock)
                System.out.println("Synchronized block on class lock");
            }
        }
    }
    ```

# 10. What is join() method?
The join() method in Java is used to pause the execution of the current thread until the thread on which join() was called has finished its execution. This is particularly useful when you want to ensure that one thread completes its task before another thread proceeds.
```
public class JoinExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread t1 is starting.");
                Thread.sleep(2000); // Simulating work with sleep
                System.out.println("Thread t1 is finishing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread t2 is starting.");
                Thread.sleep(3000); // Simulating work with sleep
                System.out.println("Thread t2 is finishing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            System.out.println("Main thread waiting for t1 to finish.");
            t1.join(); // Wait for t1 to finish
            System.out.println("Main thread resumed after t1 finished.");

            System.out.println("Main thread waiting for t2 to finish.");
            t2.join(); // Wait for t2 to finish
            System.out.println("Main thread resumed after t2 finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finishing.");
    }
}
```

# 11. what is yield() method?
The yield() method in Java is a static method of the Thread class that is used to hint the thread scheduler that the current thread is willing to yield its current use of the processor. This can allow other threads of the same or higher priority to execute.
Example:
```
public class YieldExample {
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Producer thread running: " + i);
                Thread.yield(); // Hint to yield the processor
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Consumer thread running: " + i);
                Thread.yield(); // Hint to yield the processor
            }
        });

        producer.start();
        consumer.start();
    }
}
```

# 12. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
- A ThreadPool in Java is a pool of worker threads that are managed by a framework, allowing for the efficient reuse of threads. Instead of creating a new thread for every task, tasks are submitted to the pool, and worker threads from the pool execute these tasks. This helps to limit the overhead associated with thread creation and management, and it can improve the performance of multithreaded applications by reusing a fixed number of threads.
Java's java.util.concurrent.Executors class provides several types of thread pools, including FixedThreadPool, CachedThreadPool, SingleThreadExecutor, ScheduledThreadPool.

- The TaskQueue, also known as the work queue, is a blocking queue used to hold tasks before they are executed by the worker threads in the pool.
    - Unbounded Queue (e.g., LinkedBlockingQueue):
        - If the queue is unbounded, the pool can accumulate an unlimited number of tasks, which can lead to high memory usage.
        - Worker threads process tasks in the order they are submitted.
    - Bounded Queue (e.g., ArrayBlockingQueue):
        - Limits the number of tasks that can be queued.
        - If the queue is full, new tasks will be rejected according to the rejection policy.

# 13. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
In Java, the primary library used to create and manage thread pools is the java.util.concurrent package, which provides a robust framework for handling concurrency. Within this package, the ExecutorService interface provides the main functions of a thread pool.
```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executorService.submit(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simulate task execution time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskNumber + " completed by " + Thread.currentThread().getName());
            });
        }

        // Shut down the executor service
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
```

# 14. How to submit a task to ThreadPool?
To submit a task to a thread pool in Java, you can use the submit() or execute() methods provided by the ExecutorService interface. The submit() method is more versatile as it returns a Future object representing the result of the computation, allowing you to check the status of the task or retrieve its result when it is done. The execute() method, on the other hand, is a void method that simply starts the task.

```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit a Runnable task
        Future<?> future = executorService.submit(() -> {
            System.out.println("Executing task by " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulate task execution time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task completed by " + Thread.currentThread().getName());
        });

        // Shut down the executor service
        executorService.shutdown();
    }
}
```

# 15. What is the advantage of ThreadPool?
1. Improved Performance and Resource Management
Reduced Overhead: Creating and destroying threads can be resource-intensive and time-consuming. A thread pool reuses a fixed number of threads for multiple tasks, reducing the overhead associated with thread creation and destruction.
Efficient Resource Utilization: By controlling the number of concurrent threads, a thread pool can prevent resource exhaustion and ensure that system resources (CPU, memory) are used efficiently.
2. Better System Stability
Prevent Resource Exhaustion: By limiting the number of threads, a thread pool helps avoid scenarios where an excessive number of threads are created, which can lead to resource exhaustion and system crashes.
Predictable Resource Usage: The fixed number of threads in a pool allows for more predictable resource usage, which is important for maintaining system stability, especially in high-load situations.
3. Simplified Thread Management
Ease of Use: Thread pools abstract the complexities of thread lifecycle management, such as thread creation, scheduling, and termination, making it easier for developers to work with multithreading.
Task Submission: Tasks can be easily submitted to the pool for execution, and the pool takes care of managing the threads needed to execute these tasks.
4. Enhanced Application Responsiveness
Reduced Latency: With pre-existing threads available for immediate use, tasks can start execution more quickly compared to creating a new thread each time.
Smooth Scaling: Thread pools can handle varying workloads smoothly by reusing existing threads and managing task queues effectively.
5. Built-in Support for Task Scheduling
Scheduled Tasks: The ScheduledThreadPoolExecutor allows for scheduling tasks to run after a given delay or periodically, providing a robust solution for recurring task execution without additional overhead.
6. Support for Different Task Types
Flexible Task Submission: Thread pools can handle both short-lived and long-lived tasks efficiently. The ExecutorService interface supports submitting Runnable and Callable tasks, providing flexibility in handling different types of tasks.
7. Concurrency Control and Rejection Policies
Rejection Policies: Thread pools can be configured with different rejection policies to handle scenarios where the task queue is full. For example, you can use policies like AbortPolicy, CallerRunsPolicy, DiscardPolicy, and DiscardOldestPolicy.
Concurrency Control: Thread pools allow for fine-grained control over the concurrency level of your application by adjusting the core and maximum pool sizes.

# 16. Difference between shutdown() and shutdownNow() methods of executor
- Task Handling:
    - shutdown(): Allows currently executing tasks to complete and processes tasks submitted before the shutdown. No new tasks are accepted.
    - shutdownNow(): Attempts to stop all actively executing tasks and returns a list of tasks that were waiting to be executed but were not started.
- Interruption:
    - shutdown(): Does not attempt to interrupt actively executing tasks.
    - shutdownNow(): Attempts to interrupt actively executing tasks by calling Thread.interrupt() on the threads executing the tasks.
- Return Value:
    - shutdown(): Does not return any value.
    - shutdownNow(): Returns a list of tasks that were submitted but not executed.
- Use Cases:
    - shutdown(): Use when you want to stop accepting new tasks but allow already submitted tasks to complete.
    - shutdownNow(): Use when you need to stop all tasks immediately, both executing and waiting tasks.

# 17. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes in Java are part of the java.util.concurrent.atomic package and provide a way to perform atomic operations on single variables without using synchronization. These classes are designed to support lock-free thread-safe programming on single variables. They are especially useful in scenarios where you need to update a variable safely in a concurrent environment without the overhead of synchronization.

The most commonly used atomic classes in Java include:
- AtomicInteger
- AtomicLong
- AtomicBoolean
- AtomicReference
- AtomicStampedReference
- AtomicMarkableReference
- AtomicIntegerArray
- AtomicLongArray
- AtomicReferenceArray  

Main Methods:
- get(): Gets the current value.
- set(int newValue): Sets to the given value.
- lazySet(int newValue): Eventually sets to the given value.
- getAndSet(int newValue): Atomically sets to the given value and returns the old value.
- compareAndSet(int expect, int update): Atomically sets the value to the given updated value if the current value is equal to the expected value.
- getAndIncrement(): Atomically increments by one and returns the old value.
    - incrementAndGet(): Atomically increments by one and returns the new value.
    
Example
```
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        atomicInteger.set(10);
        System.out.println("Initial value: " + atomicInteger.get());

        int oldValue = atomicInteger.getAndIncrement();
        System.out.println("Value after getAndIncrement(): " + atomicInteger.get() + ", old value: " + oldValue);

        int newValue = atomicInteger.incrementAndGet();
        System.out.println("Value after incrementAndGet(): " + atomicInteger.get() + ", new value: " + newValue);
    }
}
```

Use Cases:
- Concurrency Without Synchronization:
When you need to perform atomic operations on single variables without the overhead of synchronization.
- High Contention:
When multiple threads frequently update a shared variable and you want to avoid the overhead and complexity of locking mechanisms.
Lock-Free Algorithms:
When implementing non-blocking, lock-free algorithms that require atomic operations to ensure data consistency without using locks.
- Performance-Critical Applications:
When you need high performance and low latency in multi-threaded environments, and the overhead of traditional locking mechanisms is unacceptable.

# 18. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections in Java are data structures that are designed to be used safely in concurrent (multi-threaded) environments. These collections handle synchronization internally to ensure thread safety, allowing multiple threads to access and modify them concurrently without causing data corruption or inconsistencies.

- ConcurrentHashMap
    - Description: A thread-safe variant of HashMap that supports full concurrency of retrievals and adjustable expected concurrency for updates.
    - Use Case: Suitable for scenarios where you need a high-performance, thread-safe map with frequent updates.

- CopyOnWriteArrayList
    - Description: A thread-safe variant of ArrayList in which all mutative operations (e.g., add, set, remove) are implemented by making a fresh copy of the underlying array.
    - Use Case: Ideal for lists that are frequently read but infrequently modified.

- CopyOnWriteArraySet
    - Description: A thread-safe variant of HashSet that uses a CopyOnWriteArrayList for all of its internal operations.
    - Use Case: Best suited for sets that are frequently read but infrequently modified.

- ConcurrentLinkedQueue
    - Description: An unbounded thread-safe queue based on linked nodes. This queue orders elements FIFO (first-in-first-out).
    - Use Case: Suitable for applications where you need a thread-safe, non-blocking queue.

- LinkedBlockingQueue
    - Description: A bounded or unbounded blocking queue based on linked nodes. It optionally blocks when inserting or retrieving elements.
    - Use Case: Useful for implementing producer-consumer queues where blocking behavior is desired.

# 19. What kind of locks do you know? What is the advantage of each lock?
- Intrinsic Locks (Synchronized)
    - Description: The simplest form of lock, intrinsic locks are built into the Java language and are associated with every object. They are also known as monitor locks.
    - Usage: synchronized keyword.
    - Advantages:
        - Simplicity: Easy to use and understand.
        - Reentrant: A thread that holds the lock can re-enter any block of code synchronized on the same lock.
    - Disadvantages:
        - Blocking: Threads that are waiting for the lock are blocked.
        - Limited Features: No support for features like try-lock or timed-lock.

- ReentrantLock
    - Description: A reentrant lock that offers the same basic behavior and semantics as the implicit monitor lock accessed using synchronized methods and statements, but with extended capabilities.
    - Usage: java.util.concurrent.locks.ReentrantLock.
    - Advantages:
        - Fairness: Can be configured to use a fair locking policy.
        - Try Lock: Can attempt to acquire the lock without blocking.
        - Timed Lock: Can attempt to acquire the lock with a timeout.
        - Interruptibility: Can be interrupted while waiting for the lock.
    - Disadvantages:
        - Complexity: More complex to use compared to intrinsic locks.

- ReadWriteLock
    - Description: A lock that allows multiple threads to read a resource concurrently but only one thread to write to it at a time.
    - Usage: java.util.concurrent.locks.ReadWriteLock and ReentrantReadWriteLock.
    - Advantages:
        - Read/Write Separation: Improves performance by allowing concurrent reads.
        - Fairness: Can be configured to use a fair locking policy.
    - Disadvantages:
        - Complexity: More complex to use than simple locks.
        - Potential Starvation: Writers can starve if read operations are frequent.

# 20. What is future and completableFuture? List some main methods of ComplertableFuture?
In Java, Future and CompletableFuture are used for asynchronous programming and to represent the result of an asynchronous computation.
- Future
A Future represents the result of an asynchronous computation. Methods are provided to check if the computation is complete, to wait for its completion, and to retrieve the result of the computation. Future does not provide mechanisms for combining multiple futures or handling exceptions in a non-blocking manner.
Key Methods of Future
    - get(): Waits for the computation to complete and then retrieves its result.
    - get(long timeout, TimeUnit unit): Waits for at most the given time for the computation to complete and then retrieves its result.
    - cancel(boolean mayInterruptIfRunning): Attempts to cancel execution of the task.
    - isCancelled(): Returns true if the task was cancelled before it completed normally.
    - isDone(): Returns true if the task completed.

- CompletableFuture
CompletableFuture is a more powerful and flexible implementation of Future. It implements the Future and CompletionStage interfaces and provides a rich set of methods for asynchronous programming, including the ability to combine multiple futures and handle exceptions in a non-blocking manner.
Key Methods of CompletableFuture
    - completedFuture(U value): Returns a new CompletableFuture that is already completed with the given value.
    - thenApply(Function<? super T,? extends U> fn): Returns a new CompletableFuture that, when this future completes normally, is executed with this future's result as the argument to the supplied function.
    - thenAccept(Consumer<? super T> action): Returns a new CompletableFuture that, when this future completes normally, is executed with this future's result as the argument to the supplied action.
    - thenRun(Runnable action): Returns a new CompletableFuture that, when this future completes normally, is executed with the supplied action.
    - thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn): Returns a new CompletableFuture that, when both this and the other given CompletionStage complete normally, is executed with the two results as arguments to the supplied function.
    - exceptionally(Function<Throwable,? extends T> fn): Returns a new CompletableFuture that, when this future completes exceptionally, is executed with this future's exception as the argument to the supplied function.

# 21. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. 1. One solution use synchronized and wait notify， 2. One solution use ReentrantLock and await, signal.
- Solution 1:
    ```
    class OddEvenPrinter {
        private static final Object lock = new Object();
        private static int count = 1;
        private static final int MAX_COUNT = 10;
    
        public static void main(String[] args) {
            Thread oddThread = new Thread(new OddPrinter(), "Thread-0");
            Thread evenThread = new Thread(new EvenPrinter(), "Thread-1");
    
            oddThread.start();
            evenThread.start();
        }
    
        static class OddPrinter implements Runnable {
            @Override
            public void run() {
                synchronized (lock) {
                    while (count <= MAX_COUNT) {
                        if (count % 2 != 0) { // Odd number
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                            lock.notify(); // Notify waiting thread (EvenPrinter)
                        } else {
                            try {
                                lock.wait(); // Wait for notification from EvenPrinter
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        }
    
        static class EvenPrinter implements Runnable {
            @Override
            public void run() {
                synchronized (lock) {
                    while (count <= MAX_COUNT) {
                        if (count % 2 == 0) { // Even number
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                            lock.notify(); // Notify waiting thread (OddPrinter)
                        } else {
                            try {
                                lock.wait(); // Wait for notification from OddPrinter
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        }
    }
    ```
- solution 2:
    ```
    import java.util.concurrent.locks.Condition;
    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReentrantLock;
    
    class OddEvenPrinter {
        private static final Lock lock = new ReentrantLock();
        private static final Condition oddCondition = lock.newCondition();
        private static final Condition evenCondition = lock.newCondition();
        private static int count = 1;
        private static final int MAX_COUNT = 10;
        private static boolean isOddTurn = true;
    
        public static void main(String[] args) {
            Thread oddThread = new Thread(new OddPrinter(), "OddThread");
            Thread evenThread = new Thread(new EvenPrinter(), "EvenThread");
    
            oddThread.start();
            evenThread.start();
        }
    
        static class OddPrinter implements Runnable {
            @Override
            public void run() {
                while (count <= MAX_COUNT) {
                    lock.lock();
                    try {
                        if (isOddTurn) {
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                            isOddTurn = false;
                            evenCondition.signal();
                        } else {
                            try {
                                oddCondition.await();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    
        static class EvenPrinter implements Runnable {
            @Override
            public void run() {
                while (count <= MAX_COUNT) {
                    lock.lock();
                    try {
                        if (!isOddTurn) {
                            System.out.println(Thread.currentThread().getName() + ": " + count);
                            count++;
                            isOddTurn = true;
                            oddCondition.signal();
                        } else {
                            try {
                                evenCondition.await();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
    ```


# 22. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run  sequence is random. 
```
public class MultiThreadOutput {
    public static void main(String[] args) {
        // Create and start the threads
        Thread thread1 = new Thread(new NumberPrinter(1, 10), "Thread-0");
        Thread thread2 = new Thread(new NumberPrinter(11, 20), "Thread-2");
        Thread thread3 = new Thread(new NumberPrinter(21, 30), "Thread-1");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Runnable class to print a range of numbers
    static class NumberPrinter implements Runnable {
        private final int start;
        private final int end;

        public NumberPrinter(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    // Sleep to simulate work and make the output interleaved
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}
```








