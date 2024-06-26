## HW5

2. Write a thread-safe singleton class

   ```java
   public class Singleton {
     private static Singleton instance = new Singleton(); 
     private Singleton (){}
     public static Singleton getInstance() {
       return instance; 
     }
   }
   ```

3. How to create a new thread(Please also consider Thread Pool approach)? 

   >We can extend Thread, implement callable, runnable, or use thread pool

4. Difference between Runnable and Callable?

   >Runnable is a void method, while Callable can return value.

5. What is the difference between t.start() and t.run()?

   >t.start() executes on a new thread, while t.run() executes on the current thread.

6. Which way of creating threads is better: Thread class or Runnable interface?

   >Runnable is better:
   >
   >1. Can use a lambda expression, which is much simpler.
   >2. More flexible as an implementation can be passed to thread to utilize composition instead of inheritance
   >3. If a class extends Threads, it cannot extend other classes.

7. What are the thread statuses?

   >1. New 
   >2. Runnable 
   >3. Blocked
   >4. Waiting
   >5. Timed_waiting
   >6. Terminated.

8. Demondtrate deadlock and how to resolve it in Java code.

   >Deadlock is a situation in which two or more competing actions are each waiting for the other to finish, and thus neither ever does. 
   >
   >1. **Mutual Exclusion –** A resource can be used by only one process at a time. If another process requests for that resource then the requesting process must be delayed until the resource has been released.
   >2. **Hold and wait –** Some processes must be holding some resources in the non-shareable mode and at the same time must be waiting to acquire some more resources, which are currently held by other processes in the non-shareable mode.
   >3. **No pre-emption –** Resources granted to a process can be released back to the system only as a result of voluntary action of that process after the process has completed its task.
   >4. **Circular wait –** Deadlocked processes are involved in a circular chain such that each process holds one or more resources being requested by the next process in the chain.
   >
   >Solve it:
   >
   >1. Deadlock Prevention
   >2. Deadlock avoidance (Banker's Algorithm)
   >3. Deadlock detection & recovery
   >4. Deadlock Ignorance (Ostrich Method)

9. How do threads communicate each other?

   >wait(), notify(), notifyAll()

10. What's the difference between class lock and object lock?

    >class lock is a class-level lock by keyword `static synchronized` to make static data thread-safe. Object lock is a object-level lock by keyword `synchronized` to make non-static data thread-safe.

11. What is join() method?

    >By using Join() method, the Main thread will stop and wait for thread t completes its task

12. what is yield() method

    >Yield is to release the CPU resource to other threads but the thread status is still running.

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?

    >The thread pool is an executor service that contains several threads that can be used to recycle the threads instead of frequently creating and destroying threads.
    >
    >1. FixedThreadPool
    >2. CachedThreadPool
    >3. SingleThreadExecutor
    >
    >Task queue is used to store the task waiting for threads to execute.

14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?

    >java.util.concurrent.Executor
    >
    >ExecutorService

15. How to submit a task to ThreadPool?

    >executor.submit()

16. What is the advantage of ThreadPool?

    >Using thread pools generally offers better performance and resource management compared to creating single threads directly. Thread pools control the number of concurrent tasks, reduce the overhead of thread creation and destruction, and improve performance. Moreover, thread pools allow task queuing for pending execution, automatically manage thread lifecycles, and provide more flexible error handling mechanisms. However, in some simple scenarios, using a single thread might be more straightforward.

17. Difference between shutdown() and shutdownNow() methods of executor

    >Two **different methods** are provided **for shutting down** an **ExecutorService**. The **shutdown() method** will allow previously submitted tasks to execute before terminating, while the **shutdownNow() method** prevents waiting tasks from starting and attempts to stop currently executing tasks.

18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?

    >The atomic class is used to provide atomic operations.
    >
    >1. AtomicBoolean
    >2. AtomicInteger
    >3. AtomicLong
    >
    >```java
    >public class AtomicDemo {
    >private static AtomicInteger atomicInteger = new AtomicInteger(1);
    >public static void main(String[] args) {
    >    System.out.println(atomicInteger.getAndIncrement()); //count++ vs ++count
    >    System.out.println(atomicInteger.get());//count
    > 	}
    >}
    >```
    >
    >

19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)

    >Concurrent collection in Java refers to **a set of classes that allow multiple threads to access and modify a collection concurrently, without the need for explicit synchronization**
    >
    >1. CopyOnWriteArrayList
    >2. ConcurrentHashMap
    >3. CopyOnWriteArraySet
    >4. ArrayBlockingQueue / LinkedBlockingQueue
    >5. LinkedBlockingDeque

20. What kind of locks do you know? What is the advantage of each lock?

    >Synchronized: easy to use. No deadlock because the lock will automatically be released when the error happens.
    >
    >ReentrantLock: More flexible. Can use `interrupt` to interrupt the waiting.
    >
    >StampedLock: Can read even one thread is writing but needs to handle such a situation.
    >
    >ReadWriteLock: allows multi-read but cannot read or write when one thread is writing

21. What is future and completableFuture? List some main methods of ComplertableFuture.

    >Future is a blocking way of multithreading. CompletableFuture is a non-blocking way of multithreading
    >
    >1. thenAccept() 
    >
    >2. exceptionally() 
    >
    >3. thenApplyAsync() 
    >
    >4. anyOf()
    >
    >5. allOf() 

22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

    >Codes are in multithreading

23. Write a code to create 2 threads, one thread print $1,3,5,7,9$, another thread print $2,4,6,8,10$. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)

    >Codes are in multithreading.OddEventPrinter

24. create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random.

    >Codes are in multithreading.PrintNumber1

25. completable future: 

    1. Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results. 
    2. Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，) 1. Sign In to Developer.BestBuy.com 2. Best Buy Developer API Documentation (bestbuyapis.github.io) 3. 可以⽤fake api https://jsonplaceholder.typicode.com/ 
    3. Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.

    >Codes are in multithreading.FakeApi
