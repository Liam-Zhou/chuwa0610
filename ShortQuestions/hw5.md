1. Read: [link](https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock)

|             | class lock                                                                    | object lock                                                            |
|-------------|-------------------------------------------------------------------------------|------------------------------------------------------------------------|
| scope       | class-level                                                                   | instance-level                                                         |
| Granularity | only 1 lock, shared across all instances                                      | each instance has own lock                                             |
| Impact      | other sync methods are blocked                                                | other sync instance methods on the same object are blocked             |
| Code        | method() {</br>  &nbsp;&nbsp;&nbsp;&nbsp;public synchornized(**Class.class**) | method() {</br>  &nbsp;&nbsp;&nbsp;&nbsp;public synchornized(**this**) |

2. Write a thread-safe singleton class
     ```
     public class Singleton {
         private static volatile Singleton instance;
         private Singleton() {
         }
         
         public Singleton getSingleton(){
             if (instance == null) {
                 synchronized(this) {
                     instance = new Singleton();
                 }
             }
             return this.singleton;
         }
     }
     ```
3. How to create a new thread(Please also consider Thread Pool approach)?
   - extends class Thread
   - implements Runnable
   - using lambda
   - implements Callable with executorService
   ```
   // extends Thread
   Thread t1 = new Thread(new Thread() {
           @Override
           public void run() {
               System.out.println("Creating from Thread");
           }
       });
    t1.start();
    }
   
   // implements Runnable 
   Thread r1 = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("Creating from Runnable");
           }
       });
    r1.start();
   
   // using lambda
   Thread l1 = new Thread(()->{
           System.out.println("Creating from lambda");
       });
    l1.start();
   
   // implements Callable
   ExecutorService excutorService = Executors.newFixedThreadPool(4);
   Callable<String> callable = () -> {
       Thread.sleep(1000);
       System.out.println("Creating from Callable");
       return "Hello World";
   };
   Future<String> future = excutorService.submit(callable);
   // String str = future.get()
   
   Runnable runnable = () -> {
           System.out.println("from runnable");
       };
   Future<?> ans1 = executor.submit(runnable);
   // ans1.get()
   ...
   ```   

4. Difference between Runnable and Callable?

|                    | runnable                                           | Callable                         |
|--------------------|:---------------------------------------------------|----------------------------------|
| Return Type        | Void                                               | return a **V**                   |
| Exception Handling | cannot throw checked Exception                     | can throw checked exception      |
| With Executors     | return Future<?>, only check if complete of cancel | return Future<V>, can get result |


5. What is the difference between t.start() and t.run()?
   - t.start(): start a new thread to execute the task
   - t.run(): execute the task in the current thread
   

6. Which way of creating threads is better: Thread class or Runnable interface?
    - Runnable is better
      - when extends Thread, no change to extending any other classes
      - better separation of concerns

7. What are the thread statuses?
   - NEW
     - start() has not been called on the thread instance
   - RUNNABLE
     - running
     - waiting for the turn to be executed on the CPU (yield())
   - BLOCKED
     - waiting for a block
   - WAITING
     - Object.wait()
     - Thread.join()
     - LockSupport.park()
   - TIME_WAITING
     - waiting method with time
   - TERMINATED
     - run() method is completed

8. Demonstrate deadlock and how to resolve it in Java code.
    - deadlock
      - when two or more threads are blocked forever, each waiting on a resource that the other thread holds
        - Example
        ```
        lock1
        lock2
        Thread t1 = ...
        // require lock1 first and then require lock2
        
        Thread t2 = ...
        // require lock2 first and then require lock1 
        ```
    - How to avoid
      - Avoidance of Circular Wait
        - threads always require locks in the same order to prevent circular dependencies
      - Using tryLock with ReentrantLock
      
        
9. How do threads communicate each other?
    - Shared Objects and Variables
    - Locks and Conditions
      - Locks: wait(), notify(), notifyAll()
      - Conditions: await(), signal(), signalAll()
      
10. What’s the difference between class lock and object lock?
    - They have different scope
      - Object Lock: lock is on the instance of the class, different instance of the same class do not interfere with each other.
      - Class Lock:  lock is on the **Class** level. All instances of the class share the same class lock.
    - They have difference usage
      - Object Lock: Used for synchronizing the instance methods and instance-specific blocks.
      - Class Lock: used for synchronizing static methods and class-specific blocks.
    - Concurrency
      - Object Lock: Multiple threads can concurrently access synchronized methods on different instances of the class.
      - Class Lock: Only one thread can access synchronized static methods or blocks at a time for the entire class.
11. What is join() method? 
    - The **join()** in Java is used to pause the execution of the current thread until the thread on which join() was called has finishing executing.
    
12. what is yield() method?
    - **yield()** caused the currently executing thread to temporarily pause and allow other thread of the same priority to execute.
    - doesn't release the lock.
13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
    - ThreadPool
      - is a managed group of pre-initialized thread that are available to perform tasks concurrently.
    - Types:
      - Fixed Thread Pool: 
        - Fixed numbers of threads: 
        - Executors.newFixedThreadPool(int nThread);
      - Cached Thread Pool: 
        - dynamically adjust the number of threads in the pool based on the workload, idle threads for specific time will be terminated and remove from the pool.
        - Executors.newCachedThreadPool()
      - Scheduled Thread Pool
        - scheduling tasks to execute after a certain delay or periodically at fixed intervals
        - Executors.newScheduledThreadPool(int corePoolSize)
      - Single Thread Pool
        - one thread, executes tasks sequentially in the order they are submitted
        - Executors.newSingleThreadExecutor()
    - Task Queue
      - In thread pool, a task queue is used to hold tasks that are waiting to be executed the threads in the pool. When a task is submitted to the thread pool and all threads are busy, the task is placed in the task queue.
      

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
    - library: java.util.concurrent.*;
    - Interface: ExecutorService
    
15. How to submit a task to ThreadPool? 
    - Using **ExecutorService**, which is the primary interface for managing and controlling thread pools.
    - Using submit() to submit **Runnable** or **Callable**
    ```
    import java.util.concurrent.*;
    ExecutorService executor = Executors.newFixedThreadPool(10);
    executor.submit(new RunnableTask());
    ```
    
16. What is the advantage of ThreadPool? 
    - better performance: reducing overhead of thread creation and destruction
    - task queue: allow task queuing for pending execution, automatically manage thread lifecycles
    - provide error handling mechanism: **RejectedExecutionHandler**
    - close the thread pool using shutdown functions
    
17. Difference between shutdown() and shutdownNow() methods of executor 
    - used to terminate the executor, but behave differently in terms of how they handle currently executing and pending tasks.
    - Executing tasks:
      - shutdown(): orderly shutdown of the executor, no new tasks will be accepted. active tasks will keep executing.
      - shotdownNow(): attempts to stop all actively executing tasks
    - Pending tasks
      - shutdown(): pending tasks are allowed to execute
      - shutdownNow(): will return a list of pending tasks
    - use case:
      - shutdown(): when you want to ensure that all submitted tasks are completed before executor terminates
      - shutdownNow(): when you want to stop all tasks immediately, including pending tasks.
      
18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
     classes and its main methods. when to use it?
    - Atomic class:
      - provide a way to perform atomic operations on single variable without using explicit synchronization.
    - Types of atomic classes
      - AtomicBoolean
      - AtomicInteger
      - AtomicLong
      - AtomicReference<V> :for a reference(an object)
      - AtomicIntegerArray
      - AtomicLongArray
      - AtomicReferenceArray<E>
      - AtomicMarkableReference<V>: For an object reference with an associate boolean mark
      - AtomicStampedReference<V>: for an object reference with an integer stamp
    
    - Main method
      - int addAndGet(int delta) => count = count + delta
      - int incrementAndGet() => ++count
      - int decrementAndGet()
      - int getAndIncrement() => count++
      - int getAndDecrement()
      - int getAndSet(int newValue): set oldValue = newValue, return oldValue
    - code
    ```
    AtomicInteger atomicInteger = new AtomicInteger(0);

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Runnable task = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println(atomicInteger.getAndIncrement());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    executorService.submit(task);
    executorService.submit(task);
    executorService.submit(task);

    executorService.shutdown();
    
    ----
    System.out.println(atomicInteger.addAndGet(100));
    System.out.println(atomicInteger.getAndIncrement());
    System.out.println(atomicInteger.incrementAndGet());
    System.out.println(atomicInteger.getAndAdd(20));
    
    ```

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
    - classes in java.util.concurrent package and designed to handle concurrency and provide thread-safe operations on collections without adding external synchronization .
    - Data Structures
      - CopyOnWriteArrayList
      - CopyOnWriteArraySet
      - ConcurrentHashMap
      - ArrayBlockingQueue
      - LinkedBlockingQueue
    
20. What kind of locks do you know? What is the advantage of each lock? 
    - Locks
      - Flexibility: Provide function lock(), tryLock(), lockInterruptibly(), unlock
    - ReentrantLock
      - a thread can acquire the same lock multiple times without causing a deadlock, useful fo recursive algorithm
      - supports multiple Condition
    - Condition
      - Allows multiple condition object per lock.
    - ReadWriteLock
      - separate read and write lock
      - for read heavy workloads

21. What is future and completableFuture? List some main methods of ComplertableFuture. 
    - Future
      - represent a future result of an async computation, provides ways to check if the computation is complete, wait, get the result
      - get() will block
      - or while loop to check isDone()
      - Function
        - get()
        - get(long timeout, TimeUnit unit)
        - cancel(boolean b)
        - isCancelled()
        - isDone()
    - CompletableFuture
      - an async programming tool introduce in Java8 and implements Future, providing powerful async capabilities. 
      - supports chaining operations , non-block async operations
      - support callback
      - Functions
        ```
        - creating
            - CompletableFuture.supplyAsync(Supplier<U> supplier): execute task async and return the result
            - CompletableFuture.runAsync(Runnable runnable): execute task async,  no return value.
        - result process and transform
          - CompletableFuture.thenApply(Function<T,U> fn): process the return value and return a result
          - CompletableFuture.thenAccept(Consumer<T> action): process the return value, no return
          - CompletableFuture.thenRun(Runnable action): neither need the value of the computation,nor want the return
        - Combination and linking
          - CompletableFuture.thenCompose(Function<T,CompletionStage<U>> fn): links another CompletableFuture and passes the result of the next task
          - CompletableFuture.thenCombine(CompletionStage<U> other, BiFunction<T,U,R> fn ): combines the result of two CompletableFuture and return CompletableFuture
          - thenCompose and thenApply is close but thencompose is like map, thenapply is like flatMap in stream
        - Exception handling
          - CompletableFuture.exceptionally(): handles exceptions and return an alternative value
          - CompletableFuture.handle(): handle exception and allow 
        - Others:
          - void allOf()
          - T join(): could use with Stream
          - CompletableFuture<U> handle(fn)
        ```
        
22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
    [code](../Coding/hw5)

23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in
     com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
    1.  One solution use synchronized and wait notify,mew12ty654t6
    2.  One solution use ReentrantLock and await, signal
    
    [code](../Coding/hw5/23.java)
    
24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
    sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)   
    [code](../Coding/hw5/24.java)


25. completable future:
    1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum
        and product of two integers, and print the results.   
        [code](../Coding/hw5/251.java)

    2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products,
        reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched
        data for further processing. (需要找public api去模拟，)
        1. Sign In to Developer.BestBuy.com 
        2. Best Buy Developer API Documentation (bestbuyapis.github.io)
        3. 可以用fake api https://jsonplaceholder.typicode.com/
        ```
        private static final HttpClient httpClient = HttpClient.newHttpClient();

        public static CompletableFuture<JSONObject> getPost(Object id) {
    
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + id ))
                    .build();
            System.out.println("getPost " + Thread.currentThread().getName());
    
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(JSONObject::new);
        }
    
        public static CompletableFuture<JSONArray> getReviews(int postId) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+postId+"/comments"))
                    .header("Authorization", "Bearer YOUR_API_KEY")
                    .build();
            System.out.println("getReview " + Thread.currentThread().getName());
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(JSONArray::new);

        }
        
        public static CompletableFuture<Integer> getInventory(int postId) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+postId+"/comments"))
                    .header("Authorization", "Bearer YOUR_API_KEY")
                    .build();
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(JSONArray::new)
                    .thenApply(JSONArray::length);
        }
        ```
    3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API
        call, return a default value and log the exception information.
       ```
        private static final HttpClient httpClient = HttpClient.newHttpClient();

        public static CompletableFuture<JSONObject> getPost(Object id) {
    
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + id ))
                    .build();
            System.out.println("getPost " + Thread.currentThread().getName());
    
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(JSONObject::new)
                    .exceptionally(ex -> {
                        System.err.println("Error in getPost: " + ex.getMessage());
                        return new JSONObject(); // Return default value
                    });
        }
    
        public static CompletableFuture<JSONArray> getReviews(int postId) {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+postId+"/comments"))
            .header("Authorization", "Bearer YOUR_API_KEY")
            .build();
            System.out.println("getReview " + Thread.currentThread().getName());
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(JSONArray::new)
            .exceptionally(ex -> {
            System.err.println("Error in getReviews: " + ex.getMessage());
            return new JSONArray(); // Return default value
            });
    
        }
    
        public static CompletableFuture<Integer> getInventory(int postId) {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/"+postId+"/comments"))
            .header("Authorization", "Bearer YOUR_API_KEY")
            .build();
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(JSONArray::new)
            .thenApply(JSONArray::length)
            .exceptionally(ex -> {
            System.err.println("Error in getInventory: " + ex.getMessage());
            return 0; // Return default value
            });
        }


        
       ```