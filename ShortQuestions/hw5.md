## 2. Write a thread-safe singleton class
``` java
public class ASingleton {

	private static volatile ASingleton instance;
	private static Object mutex = new Object();

	private ASingleton() {
	}

	public static ASingleton getInstance() {
		ASingleton result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new ASingleton();
			}
		}
		return result;
	}

}
```

## 3.  How to create a new thread(Please also consider Thread Pool approach)?
``` java
//1. Extends Thread Class
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread using extends thread");
    }
}
Thread t = new MyThread();  
t.start();

//2. Implements Runnable 
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Start new thread using Runnable");
    }
}
Thread t2 = new Thread(new MyRunnable());

//3. Implements Callable
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Start new thread using Callable";
    }
}

//4. thread pool

ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(task);
```

## 4.  Difference between Runnable and Callable?
* The runnable interface has a run( ) method that does not return anything.
* The Callable interface has a call( ) method that returns a value.

## 5. What is the difference between t.start() and t.run()?
* start() creates a new thread and the run() method is executed on the newly created thread.
* run() method is executed on the calling thread.

## 6.  Which way of creating threads is better: Thread class or Runnable interface?
Runnable
* When extending the Thread class, we're not overriding any of its methods. Instead, we override the method of Runnable (which Thread happens to implement).
* Creating an implementation of Runnable and passing it to the Thread class utilizes composition and not inheritance - which is more flexible
* After extending the Thread class, we can't extend any other class
* From Java 8 onwards, Runnables can be represented as lambda expressions

## 7. What are the thread statuses?
* NEW: A thread that has not yet started is in this state.
* RUNNABLE: thread executing in the Java virtual machine is in this state.
* BLOCKED: A thread that is blocked waiting for a monitor lock is in this state.
* WAITING: A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
* TIMED_WAITING: A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
* TERMINATED: A thread that has exited is in this state.

## 8. Demondtrate deadlock and how to resolve it in Java code.
* Thread T1: lock1 acquired, waiting to acquire lock2.
* Thread T2: lock2 acquired, waiting to acquire lock1.
thread acquires the locks in the same order

## 9. How do threads communicate each other?
* wait(), notify(), notifyAll()

## 10. What's the difference between class lock and object lock?
* Object Level Locks - It can be used when you want non-static method or non-static block of the code should be accessed by only one thread.
* Class Level locks - It can be used when we want to prevent multiple threads to enter the synchronized block in any of all available instances on runtime. It should always be used to make static data thread safe. 

## 11. What is join() method?
The calling thread goes into a waiting state. It remains in a waiting state until the referenced thread terminates.

## 12. what is yield() method
The current thread is willing to relinquish its current use of processor but it'd like to be scheduled back soon as possible.

## 13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
* When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks.
* Single Thread Executor, Cached Thread Pool, Fixed Thread Pool, Scheduled Thread Pool, Single Thread Scheduled Pool
* Task submitters always send the tasks to the queue.

## 14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
* ThreadPoolExecutor
* ExecutorService

## 15. How to submit a task to ThreadPool?
``` java
// 创建固定大小的线程池:
ExecutorService executor = Executors.newFixedThreadPool(4);
// 提交任务:
executor.submit(() -> "ganhuo");
executor.submit(task2);
executor.submit(task3);
executor.submit(task4);
executor.submit(task5);
```

## 16. What is the advantage of ThreadPool?
* reduce the overhead associated with creating and destroying threads.

## 17. Difference between shutdown() and shutdownNow() methods of executor
shutdown(): Can't accept new tasks, but the already submitted tasks continue to run.
shutdownNow(): Also try to cancel the already submitted tasks.

## 18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
* Atomic classes provide a lock-free and thread-safe environment or programming on a single variable.
* AtomicInteger, AtomicLong, AtomicBoolean and AtomicReference.
``` java
class Counter extends Thread {
 
    // Atomic counter Variable
    AtomicInteger count;
 
    // Constructor of class
    Counter()
    {
        count = new AtomicInteger();
    }
 
    // method which would be called upon
    // the start of execution of a thread
    public void run()
    {
 
        int max = 1_000_00_000;
 
        // incrementing counter total of max times
        for (int i = 0; i < max; i++) {
            count.addAndGet(1);
        }
    }
}
 
public class AtomicCounter {
    public static void main(String[] args)
        throws InterruptedException
    {
        // Instance of Counter Class
        Counter c = new Counter();
 
        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");
 
        // Threads start executing
        first.start();
        second.start();
 
        // main thread will wait for both
        // threads to complete execution
        first.join();
        second.join();
 
        // Printing final value of count variable
        System.out.println(c.count);
    }
}
```
* A shared entity such as, mutable object or variable, might be changed, which may result in the inconsistency of the program or database. So, it becomes crucial to deal with the shared entity while accessed concurrently. An atomic variable can be one of the alternatives in such a scenario.

## 19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe).
* Concurrent collections are thread-safe data structures provided by the java.util.concurrent package.
* ConcurrentHashMap, CopyOnWriteArrayList, ConcurrentSkipListMap.

## 20. What kind of locks do you know? What is the advantage of each lock?
### ReentrantLock
* more flexible
### ReadWriteLock
* Allowed simultaneous reading
### StampedLock
* Higher throughput

## 21. What is future and completableFuture? List some main methods of ComplertableFuture.
* Future and CompletableFuture are both abstractions for representing a result that will be available in the future
* Future is a blocking API, whereas CompletableFuture is non-blocking. 
* CompletableFuture provides a more powerful composition API than Future.
* thenAccept(), supplyAsync(), thenCompose(), complete(), cancel()
