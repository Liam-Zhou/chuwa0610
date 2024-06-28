2. Write a thread-safe singleton class

   1. Eager Initialization

      ```java
      class Singleton{
        private static final Singleton instance = new Singleton();
        
        private Singleton() {};
        
        public synchronized getInstance(){
          return instance;
        }
      }
      ```

   2. Lazy Initialization with Double-Checked Locking

      ```java
      public class Singleton {
          // Volatile keyword ensures visibility of changes to variables across threads
          private static volatile Singleton instance;
        	private static final Object lock = new Object();
      
          // Private constructor to prevent instantiation
          private Singleton() {}
      
          // Provide a public method to get the instance with double-checked locking
          public static Singleton getInstance() {
              if (instance == null) {  // First check (no locking)
                  synchronized (Singleton.class) {
                      if (instance == null) {  // Second check (with locking)
                          instance = new Singleton();
                      }
                  }
              }
              return instance;
          }
      }
      ```

3. How to create a new thread (Please also consider ThreadPool approach)?

   1. Extending `Thread` Class

   - This approach is more straightforward but limits your ability to extend another class.

     ```java
     class MyThread extends Thread {
         @Override
         public void run() {
             System.out.println("Thread is running");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             MyThread myThread = new MyThread();
             myThread.start();
         }
     }
     ```

   2. Implementing the `Runnable` Interface

   - This approach is similar to the first one but it supports multiple inheritance.

     ```java
     class MyRunnable implements Runnable {
         @Override
         public void run() {
             System.out.println("Thread is running");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             MyRunnable myRunnable = new MyRunnable();
             Thread thread = new Thread(myRunnable); // Creating a new Thread through myRunnable
             thread.start();
         }
     }
     ```

   3. Implementing `Callable` Interface

   - The `Callable` interface in Java is similar to `Runnable`, but it can return a result and throw a checked exception. `Callable` is useful when you need the thread to return a value or throw an exception.

   - It requires an `ExecutorService` or another mechanism to manage the execution of the `Callable` tasks in separate threads.

     - Thread doesn't have a constructor expecting a `Callable` argument, so we typically use `ExecutorService` to excute `Callable` tasks.

     ```java
     class MyCallable implements Callable<String> {
         @Override
         public String call() throws Exception {
             // Simulate some work
             Thread.sleep(1000);
             return "Thread completed!";
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             // Create a Callable object
             MyCallable myCallable = new MyCallable();
             
             // Use an ExecutorService to manage the thread
             ExecutorService executorService = Executors.newSingleThreadExecutor();
             
             // Submit the Callable to the ExecutorService and get a Future object
             Future<String> future = executorService.submit(myCallable);
             
             try {
                 // Get the result of the Callable (this will block until the Callable is done)
                 String result = future.get();
                 System.out.println(result);
             } catch (InterruptedException | ExecutionException e) {
                 e.printStackTrace();
             }
             
             // Shut down the ExecutorService
             executorService.shutdown();
         }
     }
     ```

   4. ThreadPool

      ```java
      public class CachedThreadPoolExample {
          public static void main(String[] args) {
              // Create a cached thread pool
              ExecutorService executorService = Executors.newCachedThreadPool();
      
              // Submit tasks to the thread pool
              for (int i = 0; i < 10; i++) {
                  executorService.submit(() -> {
                      System.out.println(Thread.currentThread().getName() + " is executing a task");
                      try {
                          Thread.sleep(1000); // Simulate some work
                      } catch (InterruptedException e) {
                          Thread.currentThread().interrupt();
                      }
                  });
              }
      
              // Shut down the thread pool
              executorService.shutdown();
              try {
                  // Wait for all tasks to complete before continuing
                  if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                      executorService.shutdownNow(); // Force shutdown if tasks are not completed
                  }
              } catch (InterruptedException e) {
                  executorService.shutdownNow();
              }
      
              System.out.println("All tasks are completed");
          }
      }
      ```

4. Difference between Runnable and Callable?

   - runnable has no return value; You can use Thread to excute a runnable.

   - callable has return value; Thread class doesn't support callable.

5. What is the difference between t.start() and t.run()?

   - t.start starts a new thread to excute the task(run())
   - t.run() excute the task in the current thread.

6. Which way of creating threads is better: Thread class or Runnable interface?

   - If you don't need multi-inheritance, Thread class is simpler.
   - If you need multi-inheritance, you can only use Runnable interface.

7. What are the thread states?

   - **NEW**: When a thread is created but not yet started.
   - **RUNNABLE**: When a thread is ready to run and is waiting for CPU time.
   - **BLOCKED**: When a thread is blocked waiting for a monitor lock to enter a synchronized block/method.
   - **WAITING**: When a thread is waiting indefinitely for another thread to perform a particular action (e.g., `Object.wait()`).
   - **TIMED_WAITING**: When a thread is waiting for another thread to perform an action for a specified waiting time (e.g., `Thread.sleep(millis)`).
   - **TERMINATED**: When a thread has completed its execution.

8. Demondtrate deadlock and how to resolve it in Java code.

   ```java
   public class Main {
       private static final Object lock1 = new Object();
       private static final Object lock2 = new Object();
   
       public static void main(String[] args) {
           Thread thread1 = new Thread(() -> {
               synchronized (lock1) {
                   try {
                       System.out.println(Thread.currentThread().getName() + "Acquired lock1");
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   synchronized (lock2) {
                       System.out.println(Thread.currentThread().getName() + "Acquired lock2");
                   }
               }
           });
   
           Thread thread2 = new Thread(() -> {
               synchronized (lock2) {
                   try {
                       System.out.println(Thread.currentThread().getName() + " Acquired lock2");
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   synchronized (lock1) {
                       System.out.println(Thread.currentThread().getName() + " Acquired lock1");
                   }
               }
           });
   
           thread1.start();
           thread2.start();
       }
   }
   ```

   - How to solve: 

     - Lock Ordering

       - Establish a global order in which all threads must acquire locks. This **prevents circular wait** conditions, which are a common cause of deadlocks. In this example: to acquire resource2, you must acquire resource1 first.

       ```java
       
       public class Main {
           private static final Object lock1 = new Object();
           private static final Object lock2 = new Object();
       
           public static void main(String[] args) {
               Thread thread1 = new Thread(() -> {
                   synchronized (lock1) {
                       try {
                           System.out.println(Thread.currentThread().getName() + "Acquired lock1");
                           Thread.sleep(50);
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                       synchronized (lock2) {
                           System.out.println(Thread.currentThread().getName() + "Acquired lock2");
                       }
                   }
               });
       
               Thread thread2 = new Thread(() -> {
                   synchronized (lock1) { // reorder: acquire lock1 first.
                       try {
                           System.out.println(Thread.currentThread().getName() + " Acquired lock1");
                           Thread.sleep(50);
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                       synchronized (lock2) {
                           System.out.println(Thread.currentThread().getName() + " Acquired lock2");
                       }
                   }
               });
       
               thread1.start();
               thread2.start();
           }
       }
       ```

       

9. How do threads communicate each other?

   In Java, `wait()` and `notify()` are methods used for inter-thread communication. They are part of the `Object` class, meaning every object in Java can be used as a monitor that can call these methods

   - **`wait()`**: This method causes the current thread to release the lock it holds on an object and enter the waiting state. The thread remains in the waiting state until another thread calls `notify()` or `notifyAll()` on the same object.

   - **`notify()`**: This method wakes up a single thread that is waiting on the object's monitor. If multiple threads are waiting, one is chosen to be awakened.

   - **`notifyAll()`**: This method wakes up all the threads that are waiting on the object's monitor.

     ```java
     // Consumer - Producer 
     import java.util.LinkedList;
     import java.util.Queue;
     
     class SharedResource {
         private final Queue<Integer> queue = new LinkedList<>();
         private final int MAX_SIZE = 5;
     
         public synchronized void produce(int value) throws InterruptedException {
             while (queue.size() == MAX_SIZE) {
                 wait(); // Wait until there is space in the queue
             }
             queue.add(value);
             System.out.println("Produced: " + value);
             notify(); // Notify the consumer that new data is available
         }
     
         public synchronized void consume() throws InterruptedException {
             while (queue.isEmpty()) {
                 wait(); // Wait until there is data to consume
             }
             int value = queue.poll();
             System.out.println("Consumed: " + value);
             notify(); // Notify the producer that space is available in the queue
         }
     }
     
     class Producer implements Runnable {
         private final SharedResource resource;
     
         public Producer(SharedResource resource) {
             this.resource = resource;
         }
     
         @Override
         public void run() {
             int value = 0;
             while (true) {
                 try {
                     resource.produce(value++);
                     Thread.sleep(100); // Simulate time taken to produce
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
     
     class Consumer implements Runnable {
         private final SharedResource resource;
     
         public Consumer(SharedResource resource) {
             this.resource = resource;
         }
     
         @Override
         public void run() {
             while (true) {
                 try {
                     resource.consume();
                     Thread.sleep(150); // Simulate time taken to consume
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             SharedResource resource = new SharedResource();
             Thread producerThread = new Thread(new Producer(resource));
             Thread consumerThread = new Thread(new Consumer(resource));
     
             producerThread.start();
             consumerThread.start();
         }
     }
     ```

10. What’s the difference between class lock and object lock?

    **Scope of Lock**:

    - **Object Lock**: Acquired on a specific instance of the class. Different instances have different locks.
    - **Class Lock**: Acquired on the `Class` object. There is only one class lock per class, shared among all instances.

    **Synchronized Methods**:

    - **Object Lock**: Used for synchronized instance methods.
    - **Class Lock**: Used for synchronized static methods.

    **Concurrency**:

    - **Object Lock**: Multiple threads can execute synchronized instance methods on different instances simultaneously.
    - **Class Lock**: Only one thread can execute synchronized static methods at a time, regardless of the instance.

11. What's `join()` method?

    The `join` method allows one thread to wait for the completion of another thread. This is useful when you need to ensure that a thread has finished its execution before continuing with the next steps in the main thread or another thread.

    ```java
    public class Main {
        public static void main(String[] args) {
            Thread thread = new Thread(() -> {
                System.out.println("Thread is running");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread completed");
            });
    
            thread.start();
    
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println("Main thread finished after child thread");
        }
    }
    ```

12. What's `yeild()` method?

    - **Purpose**: The primary purpose of `yield()` is to allow other threads of the same or higher priority to execute. It is a way to improve the responsiveness of a system, especially in multi-threaded applications.
    - **Behavior**: When a thread calls `yield()`, it moves from the running state to the runnable state, and the thread scheduler then decides which thread to run next. There is no guarantee that the current thread will stop executing immediately or that it will resume execution at a specific time.
    - **Static Method**: Since `yield()` is a static method of the `Thread` class, it always affects the currently executing thread.
    - **Platform Dependent**: The behavior of `yield()` can be platform-dependent. Some JVM implementations might treat it more strictly than others.
    - **Not a Reliable Mechanism**: `yield()` should not be used for synchronization or to control thread execution timing as its behavior is not consistent across different JVMs.

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

    - Thread Pool is a collection of fixed number of threads. These threads remain active and are ready to excute asynchronous callbacks on behalf of the applications. The thread pool can efficiently manage the number of threads and the overhead of thread creation and destruction is minimized.
    - Types:
      - `FixedThreadPool`: The number of threads is fixed and can't change.
      - `CachedThreadPool`: A cached thread pool creates new threads as needed but reuses previously constructed threads when they are available. If a thread has been idle for 60 seconds, it is terminated and removed from the pool.
      - `SingleThreadExecutor`: A single thread executor uses a single worker thread to execute tasks sequentially. If the single thread terminates due to a failure during execution, a new one will take its place.
    - The `TaskQueue` in a ThreadPool is a data structure used to store tasks that are submitted for execution but are not yet picked up by any worker thread. It acts as a holding area where tasks wait until a thread becomes available to execute them.

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

    - `java.util.concurrent`
    - The `ExecutorService` interface provides the main functionalities of a thread pool, including methods for submitting tasks and managing the thread pool's lifecycle.

    ```java
    import java.util.concurrent.ArrayBlockingQueue;
    import java.util.concurrent.ThreadPoolExecutor;
    import java.util.concurrent.TimeUnit;
    
    public class CustomThreadPoolExample {
        public static void main(String[] args) {
            int corePoolSize = 2;
            int maximumPoolSize = 4;
            long keepAliveTime = 10;
            TimeUnit unit = TimeUnit.SECONDS;
            ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(10);
    
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                taskQueue
            );
    
            for (int i = 0; i < 15; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000); // Simulate task execution time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
    
            executor.shutdown();
        }
    }
    
    ```

15. How to submit a task to ThreadPool?

    - To submit a task to a ThreadPool in Java, you use the `submit()` or `execute()` method of the `ExecutorService` or `ThreadPoolExecutor` interface. 

    - `ExcutorService`

      You can submit a task using the submit() method, which returns a Future object representing the pending result of the task. Alternatively, you can use the execute() method, which does not return a result.

      ```java
      import java.util.concurrent.ExecutorService;
      import java.util.concurrent.Executors;
      import java.util.concurrent.Future;
      
      public class ThreadPoolExample {
          public static void main(String[] args) {
              // Create a fixed thread pool with 5 threads
              ExecutorService executorService = Executors.newFixedThreadPool(5);
      
              // Submit a Runnable task
              executorService.execute(() -> {
                  System.out.println("Task executed by " + Thread.currentThread().getName());
              });
      
              // Submit a Callable task
              Future<String> future = executorService.submit(() -> {
                  System.out.println("Callable task executed by " + Thread.currentThread().getName());
                  return "Task result";
              });
      
              try {
                  // Get the result of the Callable task
                  String result = future.get();
                  System.out.println("Result: " + result);
              } catch (Exception e) {
                  e.printStackTrace();
              }
      
              // Shut down the executor service
              executorService.shutdown();
          }
      }
      ```

    - `ThreadPoolExcutor`

      If you need more control over the thread pool, you can create an instance of `ThreadPoolExecutor` directly and configure it with specific parameters such as core pool size, maximum pool size, keep-alive time, and task queue.

      ```java
      import java.util.concurrent.ArrayBlockingQueue;
      import java.util.concurrent.ThreadPoolExecutor;
      import java.util.concurrent.TimeUnit;
      import java.util.concurrent.Future;
      
      public class CustomThreadPoolExample {
          public static void main(String[] args) {
              // Create a ThreadPoolExecutor with custom settings
              int corePoolSize = 2;
              int maximumPoolSize = 4;
              long keepAliveTime = 10;
              TimeUnit unit = TimeUnit.SECONDS;
              ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(10);
      
              ThreadPoolExecutor executor = new ThreadPoolExecutor(
                  corePoolSize,
                  maximumPoolSize,
                  keepAliveTime,
                  unit,
                  taskQueue
              );
      
              // Submit a Runnable task
              executor.execute(() -> {
                  System.out.println("Runnable task executed by " + Thread.currentThread().getName());
              });
      
              // Submit a Callable task
              Future<String> future = executor.submit(() -> {
                  System.out.println("Callable task executed by " + Thread.currentThread().getName());
                  return "Task result";
              });
      
              try {
                  // Get the result of the Callable task
                  String result = future.get();
                  System.out.println("Result: " + result);
              } catch (Exception e) {
                  e.printStackTrace();
              }
      
              // Shut down the executor
              executor.shutdown();
          }
      }
      ```

16. What is the advantage of ThreadPool?

    Using a thread pool typically offers better performance and resource management compared to creating individual threads directly. Thread pools can **control the number of concurrent tasks**, **reduce the overhead of creating and destroying threads**, and improve performance. Additionally, thread pools can **queue tasks waiting to be executed**, automatically manage the lifecycle of threads, and provide more flexible error handling mechanisms. However, in some simple scenarios, using a single thread might be simpler.

17. Difference between shutdown() and shutdownNow() methods of executor?

    - **Task Completion**:
      - `shutdown()`: Allows currently executing and queued tasks to complete.
      - `shutdownNow()`: Attempts to stop all executing tasks immediately and cancels waiting tasks.
    - **Task Interruption**:
      - `shutdown()`: Does not interrupt running tasks.
      - `shutdownNow()`: Interrupts all running tasks.
    - **Return Value**:
      - `shutdown()`: Returns immediately without any return value.
      - `shutdownNow()`: Returns a list of tasks that were awaiting execution.

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

    - These classes provide a way to perform atomic operations on single variables without using explicit synchronization. They ensure that operations on the variable are performed atomically, meaning they are indivisible and appear to be instantaneous, providing thread-safety and reducing the overhead of using synchronized blocks or methods.

    - Types:

      - **`AtomicBoolean`**: Provides an atomic boolean variable.
      - **`AtomicInteger`**: Provides an atomic integer variable.
      - **`AtomicLong`**: Provides an atomic long variable.
      - **`AtomicReference`<V>**: Provides an atomic reference variable that can hold any object reference.
      - **`AtomicIntegerArray`**: Provides an atomic array of integers.
      - **`AtomicLongArray`**: Provides an atomic array of long integers.
      - **`AtomicReferenceArray<E>`**: Provides an atomic array of object references.

    - examples

      ```java
      public class AtomicBooleanExample {
          private AtomicBoolean flag = new AtomicBoolean(true);
      
          public void toggleFlag() {
              boolean previous = flag.getAndSet(!flag.get());
              System.out.println("Flag changed from " + previous + " to " + flag.get());
          }
      
          public static void main(String[] args) {
              AtomicBooleanExample example = new AtomicBooleanExample();
              example.toggleFlag();
              example.toggleFlag();
          }
      }
      ```

      ```java
      public class AtomicIntegerExample {
          private AtomicInteger count = new AtomicInteger(0);
      
          public void increment() {
              int newValue = count.incrementAndGet();
              System.out.println("New count: " + newValue);
          }
      
          public void addValue(int value) {
              int newValue = count.addAndGet(value);
              System.out.println("New count after adding " + value + ": " + newValue);
          }
      
          public static void main(String[] args) {
              AtomicIntegerExample example = new AtomicIntegerExample();
              example.increment();
              example.addValue(5);
          }
      }
      ```

      ```java
      public class AtomicLongExample {
          private AtomicLong count = new AtomicLong(0);
      
          public void increment() {
              long newValue = count.incrementAndGet();
              System.out.println("New count: " + newValue);
          }
      
          public void addValue(long value) {
              long newValue = count.addAndGet(value);
              System.out.println("New count after adding " + value + ": " + newValue);
          }
      
          public static void main(String[] args) {
              AtomicLongExample example = new AtomicLongExample();
              example.increment();
              example.addValue(10L);
          }
      }
      ```

      ```java
      import java.util.concurrent.atomic.AtomicReference;
      
      public class AtomicReferenceExample {
          private AtomicReference<String> atomicString = new AtomicReference<>("initial");
      
          public void updateString(String newString) {
              String previous = atomicString.getAndSet(newString);
              System.out.println("String changed from " + previous + " to " + newString);
          }
      
          public static void main(String[] args) {
              AtomicReferenceExample example = new AtomicReferenceExample();
              example.updateString("updated");
          }
      }
      ```

    - **When to use**

      - Atomic classes are particularly useful in scenarios where you need to perform simple atomic operations on single variables without the overhead and complexity of synchronization. Here are some common use cases:
        1. **Counters and Sequence Generators**: When you need a thread-safe counter or sequence generator.
        2. **Flags and State Management**: For managing boolean flags or simple state transitions.
        3. **Concurrent Collections**: When implementing custom concurrent collections where atomic operations on individual elements are required.
        4. **Performance-Critical Code**: In performance-sensitive applications where reducing synchronization overhead is important.

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

    `java.util.concurrent` package is designed to handle concurrent access by multiple threads in a thread-safe manner. These collections are optimized to minimize contention and provide better performance in multithreaded environments compared to synchronized collections.

| interface | non-thread-safe         | thread-safe                              |
| --------- | ----------------------- | ---------------------------------------- |
| List      | ArrayList               | CopyOnWriteArrayList                     |
| Map       | HashMap                 | ConcurrentHashMap                        |
| Set       | HashSet / TreeSet       | CopyOnWriteArraySet                      |
| Queue     | ArrayDeque / LinkedList | ArrayBlockingQueue / LinkedBlockingQueue |
| Deque     | ArrayDeque / LinkedList | LinkedBlockingDeque                      |

20. What kind of locks do you know? What is the advantage of each lock?

    1. ReentrantLock: `ReentrantLock` is a flexible and powerful lock implementation provided to replace the `sychronized` locks.

       - **Reentrancy**: A thread can acquire the same lock multiple times without causing a deadlock.

       - **Lock Fairness**: Can be configured to be fair, giving preference to the longest-waiting thread.

       - **Interruptible Lock Acquisition**: Allows a thread to be interrupted while waiting to acquire the lock.

       - **Condition Variables**: Provides support for multiple `Condition` objects, enabling more complex synchronization scenarios.

    2. ReadWriteLock: : `ReadWriteLock` allows multiple threads to read a shared resource concurrently while still ensuring exclusive access for writing.

       - **Improved Read Access**: Multiple readers can acquire the read lock simultaneously, improving performance for read-heavy workloads. 

       - **Exclusive Write Access**: Only one writer can acquire the write lock, ensuring data integrity during write operations.

    3. StampedLock: `StampedLock` is a more sophisticated lock introduced in Java 8 that provides an alternative to `ReentrantReadWriteLock` with better performance in some scenarios.

       - **Optimistic Reading**: Provides an optimistic read lock that is cheaper than a regular read lock and can be validated to ensure data consistency.
       - **Reduced Overhead**: Can improve performance by reducing the overhead associated with acquiring and releasing locks.

21. What is future and completableFuture? List some main methods of CompletableFuture.
    - **Future**: 
      - It represents the result of an asynchronous computation and provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result. The result can only be retrieved when the computation has completed; otherwise, the `get` method will block.
      - **Key methods**
        - **`boolean cancel(boolean mayInterruptIfRunning)`**: Attempts to cancel the execution of the task.
        - **`boolean isCancelled()`**: Returns `true` if the task was cancelled before it completed normally.
        - **`boolean isDone()`**: Returns `true` if the task completed.
        - **`V get()`**: Waits if necessary for the computation to complete, and then retrieves its result.
        - **`V get(long timeout, TimeUnit unit)`**: Waits if necessary for at most the given time for the computation to complete, and then retrieves its result.
    - **Completable Future**: 
      - It is an implementation of the `Future` interface with additional capabilities to perform actions upon completion. `CompletableFuture` can be used to build complex asynchronous pipelines and handle completion stages. 
      - **Key Features** :
        - It allows manual completion of the future.
        - It supports combining multiple futures and performing dependent actions.
        - It supports both blocking and non-blocking calls.
        - It provides methods for chaining dependent actions.
      - **Key Methods**:
        1. **Completion and Factory Methods**:
           - `static CompletableFuture<Void> runAsync(Runnable runnable)`: Runs a task asynchronously.
           - `static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)`: Returns a new `CompletableFuture` that is asynchronously completed by a task running in the ForkJoinPool.
           - `static <U> CompletableFuture<U> completedFuture(U value)`: Returns a new `CompletableFuture` that is already completed with the given value.
        2. **Combining Futures**:
           - `thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)`: Combines two futures and performs an action when both complete.
           - `thenCompose(Function<? super T,? extends CompletionStage<U>> fn)`: Returns a new `CompletionStage` that, when this stage completes normally, is executed with this stage's result as the argument.
        3. **Handling Results**:
           - `thenApply(Function<? super T,? extends U> fn)`: Returns a new `CompletionStage` that, when this stage completes normally, is executed with this stage's result as the argument.
           - `thenAccept(Consumer<? super T> action)`: Returns a new `CompletionStage` that, when this stage completes normally, is executed with this stage's result as the argument, but the result is not returned.
           - `handle(BiFunction<? super T, Throwable, ? extends U> fn)`: Returns a new `CompletionStage` that, when this stage completes, is executed with this stage's result and exception as arguments.
        4. **Exception Handling**:
           - `exceptionally(Function<Throwable, ? extends T> fn)`: Returns a new `CompletionStage` that, when this stage completes exceptionally, is executed with this stage's exception as the argument.
        5. **Completion Methods**:
           - `complete(T value)`: If not already completed, sets the value returned by `get()` and related methods to the given value.
           - `completeExceptionally(Throwable ex)`: If not already completed, causes invocations of `get()` and related methods to throw the given exception.
        6. **Other Useful Methods**:
           - `allOf(CompletableFuture<?>... cfs)`: Returns a new `CompletableFuture` that is completed when all of the given `CompletableFutures` complete.
           - `anyOf(CompletableFuture<?>... cfs)`: Returns a new `CompletableFuture` that is completed when any of the given `CompletableFutures` complete.

23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

    - `syrchronized` with `wait()` and `notify()`

      ```java
      public class OddEvenPrinter {
          private static final Object monitor = new Object();
          private static int value = 1;
      
          public static void main(String[] args) {
              PrintRunnable runnable = new PrintRunnable();
              new Thread(runnable, "Thread-1").start();
              new Thread(runnable, "Thread-2").start();
          }
      
          static class PrintRunnable implements Runnable {
              @Override
              public void run() {
                  while (value <= 10) {
                      synchronized (monitor) {
                          System.out.println(Thread.currentThread().getName() + ": " + value++);
                          monitor.notify();
                          if (value <= 10) {
                              try {
                                  monitor.wait();
                              } catch (InterruptedException e) {
                                  Thread.currentThread().interrupt();
                                  e.printStackTrace();
                              }
                          } else {
                              monitor.notify(); // notify the waiting thread to exit
                          }
                      }
                  }
              }
          }
      }
      
      ```

    - `ReentrantLock` with `signal()` and `await()`

      ```java
      public class OddEvenPrinter {
          private static final Lock lock = new ReentrantLock();
          private static final Condition condition = lock.newCondition();
          private static int value = 1;
      
          public static void main(String[] args) {
              PrintRunnable runnable = new PrintRunnable();
              new Thread(runnable, "Thread-1").start();
              new Thread(runnable, "Thread-2").start();
          }
      
          static class PrintRunnable implements Runnable {
              @Override
              public void run() {
                  while (value <= 10) {
                      lock.lock();
                      try {
                          System.out.println(Thread.currentThread().getName() + ": " + value++);
                          condition.signal();
                          if (value <= 10) {
                              try {
                                  condition.await();
                              } catch (InterruptedException e) {
                                  Thread.currentThread().interrupt();
                                  e.printStackTrace();
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

24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. 

    ```java
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

25. completable future:

    1. Homework1

       ```java
       import java.util.concurrent.CompletableFuture;
       import java.util.concurrent.ExecutionException;
       
       public class CompletableFutureExample {
       
           public static void main(String[] args) {
               int num1 = 5;
               int num2 = 10;
       
               CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> num1 + num2);
               CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> num1 * num2);
       
               sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
               productFuture.thenAccept(product -> System.out.println("Product: " + product));
       
               CompletableFuture<Void> allOf = CompletableFuture.allOf(sumFuture, productFuture);
               try {
                   allOf.get();
               } catch (InterruptedException | ExecutionException e) {
                   e.printStackTrace();
               }
           }
       }
       ```

    2. Homework 2

       ```java
       import java.net.URI;
       import java.net.http.HttpClient;
       import java.net.http.HttpRequest;
       import java.net.http.HttpResponse;
       import java.util.concurrent.CompletableFuture;
       import java.util.concurrent.ExecutionException;
       
       public class OnlineStore {
       
           public static void main(String[] args) {
               HttpClient client = HttpClient.newHttpClient();
       
               // Fetch data from APIs asynchronously
               CompletableFuture<String> productFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts/1");
               CompletableFuture<String> reviewFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments?postId=1");
               CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/albums/1");
       
               // Combine the results
               CompletableFuture<String> combinedFuture = productFuture.thenCombine(reviewFuture, (productData, reviewData) -> {
                   return "Product Data: " + productData + ", Review Data: " + reviewData;
               }).thenCombine(inventoryFuture, (partialResult, inventoryData) -> {
                   return partialResult + ", Inventory Data: " + inventoryData;
               });
       
               combinedFuture.thenAccept(combinedData -> {
                   System.out.println("Combined Data: " + combinedData);
                   processCombinedData(combinedData);
               });
       
               try {
                   combinedFuture.get(); // Block until all futures complete
               } catch (InterruptedException | ExecutionException e) {
                   e.printStackTrace();
               }
           }
       
           private static CompletableFuture<String> fetchData(HttpClient client, String url) {
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .build();
       
               return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                       .thenApply(HttpResponse::body);
           }
       
           private static void processCombinedData(String combinedData) {
               System.out.println("Processing Combined Data: " + combinedData);
           }
       }
       
       ```

    3. Homework3

       ```java
       import java.net.URI;
       import java.net.http.HttpClient;
       import java.net.http.HttpRequest;
       import java.net.http.HttpResponse;
       import java.util.concurrent.CompletableFuture;
       import java.util.concurrent.ExecutionException;
       import java.util.logging.Level;
       import java.util.logging.Logger;
       
       public class OnlineStore {
       
           private static final Logger logger = Logger.getLogger(OnlineStore.class.getName());
       
           public static void main(String[] args) {
               HttpClient client = HttpClient.newHttpClient();
       
               CompletableFuture<String> productFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts/1", "Default Product Data");
               CompletableFuture<String> reviewFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments?postId=1", "Default Review Data");
               CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/albums/1", "Default Inventory Data");
       
               CompletableFuture<String> combinedFuture = productFuture.thenCombine(reviewFuture, (productData, reviewData) -> {
                   return "Product Data: " + productData + ", Review Data: " + reviewData;
               }).thenCombine(inventoryFuture, (partialResult, inventoryData) -> {
                   return partialResult + ", Inventory Data: " + inventoryData;
               });
       
               combinedFuture.thenAccept(combinedData -> {
                   System.out.println("Combined Data: " + combinedData);
                   processCombinedData(combinedData);
               });
       
               try {
                   combinedFuture.get(); // Block until all futures complete
               } catch (InterruptedException | ExecutionException e) {
                   e.printStackTrace();
               }
           }
       
           private static CompletableFuture<String> fetchData(HttpClient client, String url, String defaultValue) {
               HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .build();
       
               return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                       .thenApply(HttpResponse::body)
                       .exceptionally(e -> {
                           logger.log(Level.SEVERE, "Exception occurred while fetching data from: " + url, e);
                           return defaultValue;
                       });
           }
       
           private static void processCombinedData(String combinedData) {
               System.out.println("Processing Combined Data: " + combinedData);
           }
       }
       
       ```

       