## 2. Write a thread-safe singleton class
```
public class Singleton {

   // Static volatile variable
   private static volatile Singleton instance;

   // Private constructor
   private Singleton() {}

   // Static synchronized getInstance method
   public static Singleton getInstance() {
      if(instance == null) {
         synchronized(this) {
            if(instance == null){
               instance = new Singleton();
            }
         }
      }
      return instance;
   }
}
```

## 3. How to create a new thread(Please also consider Thread Pool approach)?
- Extends Thread class
```
public class MyThread extends Thread {
   @Override
   public void run() {
      System.out.println("Starting a new thread...");
   }
}
```
- Implements Runnable
```
public class myRunnable implements Runnable {
   @Override
   public void run() {
      System.out.println("Starting a new thread...");
   }
}
```
- Implements Callable
```
public class MyCallable implements Callable<String> {
   @Override
   public String call() throws Exception {
      Thread.sleep(2000);
      return "Starting a new thread...";
   }
}
```
- Lambda Expression
```
Thread thread = new Thread(() -> {
   System.out.println("Starting a new thread...");
})
```
- Thread Pool
```
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit();
```

## 4. Difference between Runnable and Callable?
Runnable has a run() method and does not return anything. Callable has a call() method and returns a value. Callable allows use to specify the type of result that will be returned by the call() method. When a callable is submitted to an ExecutorService, it returns a Future object that can be used to retrieve the result of the computation. run() method in Runnable interface cannot throw a checked exception, call() method in Callable interface can throw an exception.

## 5. What is the difference betweent.start() and t.run()?
`t.start()` starts a new thread and `run()` method is executed on the newly created thread. `t.run()` cannot create a new thread, and the `run()` executes the task in the current thread.

## 6. Which way of creating threads is better: Thread class or Runnable interface?
Creating threads by implementing Runnable interface is preferred. Because when extending the Thread class, we are not forced to overriding any of its method. However, we have to override the run() method of Runnable interface.

Creating a class that implements Runnable interface and passing it to the Thread class utilizes composition instead of inheritance, which is more flexiable.

We cannot extend any other class if we extend the Thread class.
Runnable interface can also be used as lambda expression.

## 7. What are the thread status?
- **NEW** - a newly created thread that has not yet started the execution.
- **RUNNABLE** - either running or ready for execution but it's waiting for resource allocation.
- **BLOCKED** - waiting to acquire a monitor lock to enter or re-enter an synchronized block/method.
- **WAITING** - waiting for some other thread to perform a particular action without any time limit.
- **TIMED_WAITING** - waiting for some other thread to perform a specific action for a specified period.
- **TERMINATED** - has completed its execution.

## 8. Demonstrate deadlock and how to resolve it in Java code.
Deadlock:
```
public class DeadLock {
   public static void main(String[] args) throws InterruptedException {
      Thread[] ts = new Thread[] {
               new Thread(() -> {
                  while(true) {
                     Counter.add(2);
                  }
               }),
               new Thread(() -> {
                  while(true) {
                     Counter.dec(2);
                  }
               }),
      };
      for (Thread t : ts) {
         t.start();
      }

      for (Thread t : ts) {
         t.join();
      }

      System.out.println("main end");
   }
}

/**
* Locker
*/
class Counter {
   public static final Object LOCK_A = new Object();
   public static final Object LOCK_B = new Object();
   private static int value = 0;
   private static int another = 0;

   public static void add (int m) {
      synchronized (LOCK_A) {
            value += m; // A
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
            synchronized (LOCK_B) {
               another += m;
            }
      }

      System.out.println("value is: " + value + ", another is: " + another);
   }

   public static void dec(int m) {
      synchronized (LOCK_B) {
            another -= m; //B
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
            synchronized (LOCK_A) {
               value -= m;
            }
      }

      System.out.println("value is: " + value + ", another is: " + another);
   }
}
```
In order to resolve deadlock, we need to make sure lock order for all threads are the same:
```
public static void dec(int m) {
      // Change LOCK_B to LOCK_A 
      synchronized (LOCK_A) {
            another -= m; //B
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
            synchronized (LOCK_B) {
               value -= m;
            }
      }

      System.out.println("value is: " + value + ", another is: " + another);
   }
```

## 9. How do threads communicate with each other?
Threads can communicate with each other by using wait and notify method.
```
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
Class lock is used when we want to prevent multiple threads to enter the synchronized block in any of the available instances on runtime. It should always be used to make static data thread safe. Only one class's class object lock is available
```
public class ClassLock {
   public void lock() {
      synchronized (ClassLock.class){
         // ...
      }
   }
}
```
Object lock can be used when we want non-static method or non-static block of code be accessed by only one thread. It should always be used to make non-static data thread safe. Every object the class may have their own lock.
```
public class ObjectLock {
   public void lock() {
      synchronized(this) {
         // ...
      }
   }
}
```

## 11. What is join() method?
`join()` method is called by main thread. After calling the `join()` method, main thread will stop and wait for the other thread to complete its task. Main thread will continue after the other thread finished its task.

## 12. What is yield() method?
When a thread calls `yield()` method, it gives hint to the thread scheduler that it is ready to pause its execution. The thread scheduler is free to ignore this hint. After a thread executes the `yield()` method, the thread scheduler checks if there is any thread with the same or higher priority as this thread. If there are other threads with same or higher priority, it will move the current thread to Ready/Runnable state and give the processor to another thread. However, which thread gets the execution chance cannot be determined. If there are any of those threads, the current thread will keep executing.
```
public class ThreadYield {
   public static void main(String[] args) {
      Runnable r = () -> {
         int counter = 0;
         while (count < 2) {
            System.out.println(Thread.currentThread().getName());
            counter++;
            Thread.yield();
         }
      }
      new Thread(r).start();
      new Thread(r).start();
   }
}
```

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
The Thread Pool pattern helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits.  
When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks. The pattern allows us to control the number of threads the application creates and their life cycle. We’re also able to schedule tasks’ execution and keep incoming tasks in a queue.  
- **FixedThreadPool** - has a fixed number of threads that are created when the thread pool is initialized. Once a thread is created, it remains in the pool until the thread pool is shut down. If a new task is submitted and all the threads are busy, the task is added to a queue and waits for an available thread.
```
public class FixedThreadPoolEx {
   public static void main(String[] args){
      ExecutorService executor = Executors.newFixedThreadPool(5);
      for(int i = 0; i < 10; i++){
         executor.execute(() -> {
            System.out.println("Task is running.");
         });
      }
      executor.shutdown();
   }
}
```
- **SingleThreadExecutor** - has only one thread in the pool. It executes tasks sequentially, one at a time. If a task is submitted to the thread pool and the thread is busy, the task is added to a queue and waits for the thread to become available.
```
public class SingleThreadExecutorEx {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Task is running.");
            });
        }
        executor.shutdown();
    }
}
```
- **CachedThreadPool** - creates threads as needed and reuses them when they become available. It doesn’t have a fixed number of threads and can create new threads as needed. However, if a thread is idle for a certain period of time, it is terminated and removed from the pool.
```
public class CachedThreadPoolEx {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("Task is running.");
            });
        }
        executor.shutdown();
    }
}
```

- **ScheduledThreadPool** - is similar to the Fixed Thread Pool, but it is designed for executing tasks at a specific time or repeatedly at a fixed interval. You can use it to schedule tasks to run at a certain time or to repeat at a certain interval.
```
public class ScheduledThreadPoolExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        executor.schedule(() -> {
            System.out.println("Task is running after 5 seconds.");
        }, 5, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            System.out.println("Task is running repeatedly every 2 seconds.");
        }, 0, 2, TimeUnit.SECONDS);

        executor.shutdown();
    }

}
```
**TaskQueue** is used to store the new incoming tasks when all threads in thread pool are busy.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
Thread pools are managed through the `java.util.concurrent` package. The primary classes used to create and manage thread pools in Java are part of the `Executor` framework.  
The interface that provides main functions of thread pool is the `ExecutorService` interface. This interface extends from the `Executor` interface and offers a wide range of features to manage the lifecycle of tasks and thread pools.

## 15. How to submit a task to ThreadPool?
We can submit a task to ThreadPool by using `submit()` method of ExecutorService.
```
public class Main {
   public static void main(String[] args) {
      ExecutorService es = new Executors.newFixedThreadPool(4);
      for (int i = 0; i < 6; i++){
         es.submit(new Task(" " + i));
      }
      es.shutdown();
   }
}
class Task implements Runnable {
   private final String name;

   public Task(String name) {
      this.name = name;
   }

   @Override
   public void run() {
      System.out.println("Start task " + name);
      try {
          Thread.sleep(1000);
      } catch (InterruptedException e){
         e.printStackTrace();
      }
   }
}
```

## 16. What is the advantage of ThreadPool?
Thread Pool helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits. It also allows us to control the number of threads the application creates and their life cycle. We’re also able to schedule tasks’ execution and keep incoming tasks in a queue.

## 17. Difference between shutdown() and shutdownNow() methods of executor.
`shutdown()` method will allow previously submitted tasks to execute before terminating. `shutdownNow()` method prevents waiting tasks from starting and attempts to stop currently executing tasks.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes are part of `java.util.concurrent.atomic` package. They provides a set of classes that support lock-free and thread-safe programming on single variable. These classes encapsulate variables that are to be modified atomically, meaning that each operation that modifies the variable is performed as a single, uninterruptible action. 
1. **Basic Type Atomic Classes**
These classes provide atomic operations for basic data types:
- `AtomicBoolean`: Supports atomic operations on a `boolean` value.
- `AtomicInteger`: Supports atomic operations on an `int` value.
- `AtomicLong`: Supports atomic operations on a `long` value.
2. **Array Type Atomic Classes**
These classes extend atomic operations for basic data types:
- `AtomicIntegerArray`: Supports atomic operations on an array of `int` values.
- `AtomicLongArray`: Supports atomic operations on an array of `long` values.
- `AtomicReferenceArray<T>`: Supports atomic operations on an array of object references of type `T`.
3. **Reference Type Atomic Classes**
These classes are used for objects and support atomic operations on references:
- `AtomicReference<T>`: Supports atomic operations on a reference to an object of type T.
- `AtomicMarkableReference<T>`: Supports atomic operations on a reference to an object of type T, along with a boolean "mark" that can be atomically updated alongside the reference.
- `AtomicStampedReference<T>`: Similar to AtomicMarkableReference, but instead of a boolean mark, it uses an integer "stamp" to manage versions of referenced objects, which can help solve the ABA problem in concurrent algorithms.
4. **Field Updater Classes**
These classes are advanced utilities that enable atomic updates to selected volatile fields of specified classes:
- `AtomicIntegerFieldUpdater<T>`: Enables atomic updates to int fields within an object.
- `AtomicLongFieldUpdater<T>`: Enables atomic updates to long fields within an object.
- `AtomicReferenceFieldUpdater<T,V>`: Enables atomic updates to Reference fields within an object.

### Code Examples
`AtomicInteger`
```
public class AtomicDemo {
   private static AtomicInteger atomicInteger = new AtomicInteger(1);

   public static void main(String[] args){
      System.out.println(atomicInteger.getAndIncrement());
      System.out.println(atomicInteger.get());
   }
}
```
`AtomicBoolean`
```
public class AtomicBooleanExample {
   private static AtomicBoolean atomicBool = new AtomicBoolean(false);
   public static void main(String[] args) {
      
      // Set to true
      boolean previousValue = atomicBool.getAndSet(true);
      System.out.println("Previous value: " + previousValue);
      System.out.println("Current value: " + atomicBool.get());

      // Compare and set
      boolean updated = atomicBool.compareAndSet(true, false);
      System.out.println("Successfully changed to false: " + updated);
      System.out.println("Current value: " + atomicBool.get());
   }
}
```
**Use cases**: Thread safe calculator, Thread safe stop watch.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)?
Concurrent collections are synchronized in nature. Traditional collections are non-synchronized in nature.
- List: `CopyOnWriteArrayList`
- Map: `ConcurrentHashMap`
- Set: `CopyOnWriteArraySet`
- Queue: `ArrayBlockingQueue`/`LinkedBlockingQueue`
- Deque: `LinkedBlockingDeque`

## 20. What kind of locks do you know? What is the advantage of each lock?
### **ReentrantLock**
ReentrantLock offers the same concurrency and memory semantics as the implicit monitor lock accessed using synchronized methods and statements.
```
public class Lock {
   ReentrantLock lock = new ReentrantLock()l
   int counter = 0;

   public void perform() {
      lock.lock();
      try {
         count++;
      } finally {
         lock.unlock();
      }
   }
}
```
### **ReentrantReadWriteLock**
ReentrantReadWriteLock class implements the ReadWriteLock interface. It contains two locks:
- **ReadLock** - if no thread acquired the write lock or requested for it, multiple threads can acquire the read lock.
- **WriteLock** - if no thread are reading or writing, only one thread can acquire the write lock.
```
public class SynchronizedHashMapWithReadWriteLock {

   Map<String,String> syncHashMap = new HashMap<>();
   ReadWriteLock lock = new ReentrantReadWriteLock();
   Lock writeLock = lock.writeLock();
   Lock readLock = lock.readLock();

   public void put(String key, String value) {
      try {
         writeLock.lock();
         syncHashMap.put(key, value);
      } finally {
         writeLock.unlock();
      }
   }

   public String remove(String key){
      try {
         writeLock.lock();
         return syncHashMap.remove(key);
      } finally {
         writeLock.unlock();
      }
   }

   public String get(String key){
      try {
         readLock.lock();
         return syncHashMap.get(key);
      } finally {
         readLock.unlock();
      }
}

   public boolean containsKey(String key) {
      try {
         readLock.lock();
         return syncHashMap.containsKey(key);
      } finally {
         readLock.unlock();
      }
   }
}
```
### **StampedLock**
StampedLock supports both read and write locks. However, lock acquisition methods return a stamp that is used to release a lock or to check if the lock still valid.
```
public class StampedLockDemo {
   Map<String,String> map = new HashMap<>();
   private StampedLock lock = new StampedLock();

   public void put(String key, String value){
      long stamp = lock.writeLock();
      try {
         map.put(key, value);
      } finally {
         lock.unlockWrite(stamp);
      }
   }

   public String get(String key) throws InterruptedException {
      long stamp = lock.readLock();
      try {
         return map.get(key);
      } finally {
         lock.unlockRead(stamp);
      }
   }
}
```
**Optimistic locking**: read operations don’t need to wait for write operation completion, and as a result of this, the full-fledged read lock isn’t required.
```
public String readWithOptimisticLock(String key) {
   long stamp = lock.tryOptimisticRead();
   String value = map.get(key);

   if(!lock.validate(stamp)) {
      stamp = lock.readLock();
      try {
         return map.get(key);
      } finally {
         lock.unlock(stamp);               
      }
   }
   return value;
}
```

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.
An instance of a `Future` is a placeholder for a result that will be produced by an asynchronous process and may not yet be available.
```
class Task implements Callable<String> {
   @Override
   public String call() throws Exception {
      return longTimeCalculation();
   }
}

class Main{
   public static void main(String[] args){
      ExecutorService es = Executors.newFixedThreadPool(4);
      Callable<String> task = new Task();

      Future<String> future = es.submit(task);
      String res = future.get();
      if(future.isDone()){
         future.get();
      } else {
         future.cancel();
      }
   }
}
```
`CompletableFuture` is an implementation of the `Future` interface. It extends the basic functionality of `Future` to let us have a lot more control over the results of out asynchronous operations. It has the option to chain function calls onto the result of the initial task.
```
class Main{
   public static void main(String[] args) throws Exception{
      CompletableFuture<Double> cf = CompletableFuture.supplyAsync(Main::fetchPrice);

      // If success
      cf.thenAccept((result) -> {
         System.out.println("Price: " + result);
      });

      // If not
      cf.exceptionally((e) -> {
         e.printStackTrace();
         return null;
      })

      System.out.println("Hello");

      Thread.sleep(200);
   }

   static Double fetchPrice(){
      try {
         Thread.sleep(100);
      } catch (InterruptedException e){

      }

      if(Math.random() < 0.3) {
         throw new RuntimeException("fetch price failed");
      }
      return 5 + Math.random * 20;
   }
}
```
### Main methods of CompletableFuture
1. Creating a CompletableFuture
- `CompletableFuture.supplyAsync(Supplier<U> supplier)`: Executes the given task asynchronously and **returns the result**.
- `CompletableFuture.runAsync(Runnable runnable)`: Executes the given task asynchronously, **without returning a value**.
```
supplyAsync(fetch price). -> cf.thenAccept((result) -> {
            System.out.println("price: " + result);
});
supplyAsync(fetch price). -> cf.thenApply((result) -> {
            System.out.println("price: " + result);
        }).thenAccept();
```
2. Result Processing and Transformation
- `CompletableFuture.thenApply(Function<T, U> fn) `: Processes the return value and transforms it into another type.
- `CompletableFuture.thenAccept(Consumer<T> action)`: Processes the return value **without returning a new CompletableFuture**.
- `CompletableFuture.thenRun(Runnable action)`: Ignores the return value and runs a runnable.
3. Combination and Linking
- `CompletableFuture.thenCompose(Function<T, CompletionStage<U>> fn)`: Links another CompletableFuture and passes the result to the next task.
- `CompletableFuture.thenCombine(CompletionStage<U> other, BiFunction<T, U, V> fn)`: Combines the results of two CompletableFutures and returns a new CompletableFuture.
4. Exception Handling
- `CompletableFuture.exceptionally(Function<Throwable, T> fn)`: Handles exceptions and returns an alternative value.
- `CompletableFuture.handle(BiFunction<T, Throwable, U> fn)`: Handles exceptions while allowing access to the result value (if any).

## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.
1. One solution use synchronized and wait notify
```
public class Printer {
   private static final Object monitor = new Object();
   private static int value = 1;

   public static void main(String[] args){
      Runnable r = new PrintRunnable();
      new Thread(r).start();
      new Thread(r).start();
   }

   static class PrintRunnable implements Runnable {
      @Override
      public void run(){
         synchronized(monitor){
            while(value <= 10){
               System.out.println(Thread.currentThread().getName() + ": " + value++);
               monitor.notifyAll();
               try {
                  if(value <= 10) {
                     monitor.wait();
                  }
               } catch (InterruptedException e){
                  e.printStackTrace();
               }
            }
         }
      }
   }
}
```
2. One solution use ReentrantLock and await, signal
```
public class Printer {
    private static final Object monitor = new Object();
    private static int value = 1;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start();//t0
        new Thread(runnable).start();//t1
    }

    static class PrintRunnable implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        @Override
        public void run() {
            lock.lock();
            try   {
                while (value <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    condition.signalAll();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
```

## 24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.
```
public class Printer{
   private static int num = 1;

   public static void main(String[] args){
      Thread t0 = new Thread(() -> printNum());
      Thread t1 = new Thread(() -> printNum());
      Thread t2 = new Thread(() -> printNum());
      t0.start();
      t1.start();
      t2.start();
   }

   public static synchronized void printNum(){
      int count = 10;
      while(count > 0){
         System.out.println(Thread.currentThread().getName() + ": " + num++);
         try {
            Thread.sleep(500);
         } catch (InterruptedException e){
            e.printStackTrace();
         }
      }
      Printer.class.notifyAll();
   }
}
```

## 25. Completable future
1. Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
```
public class Main {
   public static void main(String[] args){
      int num1 = 2; 
      int num2 = 3;

      CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
         System.out.println(Thread.currentThread().getName() + ": " + "Calculating Sum...");
         return num1 + num2;
      });

      CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
         System.out.println(Thread.currentThread().getName() + ": " + "Calculating Product...");
         return num1 * num2;
      });

      sumFuture.thenCombine(productFuture, (sum, product) -> {
         System.out.println("Sum: " + sum);
         System.out.println("Product: " + product);
         return null;
      }).join();

      System.out.println("Calculation completed");
   }
}
```
2. Assume there is an online store that needs to fetch data from three APIs:products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. 
```
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class FetchApi {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static CompletableFuture<String> getProductInformation() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .header()
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
```
3. Implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.
```
public class FetchApi {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static CompletableFuture<String> getProductInformation() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    ex.printStackTrace(); 
                    return "Default Product Information";
                });
    }

    public static CompletableFuture<String> getReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    ex.printStackTrace(); 
                    return "Default Reviews";
                });
    }

    public static CompletableFuture<String> getInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    ex.printStackTrace();  
                    return "Default Inventory";
                });
    }
}
```
