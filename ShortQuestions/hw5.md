# Hw5
## 1.  Read: https://www.interviewbit.com/multithreading-interview-questions/#class-level-lock-vs-object-level-lock
Done Reading. \
Class lock acts on static block and make static object in the class thread-safe. Example:\
```
public class LockedClass{
    public void classLock(){
        synchronized(LockedClass.class){
            ...
        }
    }
}
```
Object lock acts on non-synchronized bock or method to protect non-static data. Example:\
```
public class LockedClass{
    public void objectLock(){
        synchronized(this){
            ...
        }
    }
}
```
## 2.  Write a thread-safe singleton class
```
public class SingletonPractice {
    public static SingletonPractice instance;
    private SingletonPractice() {}
    public synchronized static SingletonPractice getInstance() {
        if(instance == null) {
            instance = new SingletonPractice();
        }
        return instance;
    }
}

```
## 3.  How to create a new thread(Please also consider Thread Pool approach)?
- Extend Thread clss
```
public class Foo extends Thread{
     public void run(){
     ...
     }
     Thread t = new Foo();
     t.start();
}
```
- Implement Runnable
```
public class Foo implements Runnable{
     public void run(){
     ...
     }
     Thread t = new Thread(new Foo());
}
```
- Implement Callable
```
public class Foo implements Callable{
     public String call(){
     ...
     }
     Thread t = new Thread(new Foo());
}
```
- Thread Pool
```
public class Main{
     public static void main(String[] args) {
          ExecutorService es = new Executors.newFixedThreadPool(10);
          es.submit(new // add runable...);
     }
}
```
## 4.  Difference between Runnable and Callable?
### Callable
- has return values
- can throw exceptions
- often used with ThreadPool class to return Future results
### Runnable
- has no return values
- cannot throw exceptions
- used often in Thread class to create thread that only executes
## 5.  What is the difference between t.start() and t.run()?
- t.start() initialized the thread, and make it ready to run. It the calls run() to run the thread. Start can be called only once.\
- t.run() runs the code in the run() in a thread. It can be called multiple times. 
## 6.  Which way of creating threads is better: Thread class or Runnable interface?
It is believed that using Runnable is generally better than Thread. 
- Since a class can only extend one other class we cannot extend another class if we use Thread class. If we implement Runnable class we can still extend another class.
- When we extend Thread, every thread creates its own object around it, which used more memory. In Runnable, all thread shares the same object.
## 7.  What are the thread statuses?
- New: When a thread is created. The thread has not started and no code in the thread is run.
- Runnable: It is when the thread is running or able to run at any moment.
- Blocked: It is when a thread tries to get a lock but was taken by another thread. It will become runnable when the thread gets the lock.
- Waiting: It is when the thread calls wait() or join(). The thread will wait and back to runnable when other thread notifies it or terminates.
- Timed Waiting: It is when a thread is called to wait with a specific time. The thread weill back to runnable one the time finishes or when it is notified.
- Terminated: It is when the thread finishes normally where all thread code is executed, or when it has encountered some error.
## 8.  Demonstrate deadlock and how to resolve it in Java code.
Here, t1 holds on to resource1 and t2 holds on to resource2, causing a deadlock
```
public class Test {
    public static void main(String[] args) {
        final String resource1 = "resource1";
        final String resource2 = "resource2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");

                try { Thread.sleep(100); } catch (Exception e) {}

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
To resolve the deadlock, a simple way is to always the same order of accessing the lock. If we change them both to synchronize resource1 first then there will be no more dead lock
```
public class Test {
    public static void main(String[] args) {
        final String resource1 = "resource1";
        final String resource2 = "resource2";

        // Thread 1
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // Thread 2
        Thread t2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: locked resource 2");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

```
## 9.  How do threads communicate each other?
The threads can communicate with each other using wait(), notify(), notifyAll() when we are using Synchronized, or await(), signal, signalAll when we are using Lock.
Synchronized Example
```
class SharedResource {
    private boolean condition = false;

    public synchronized void doWait() {
        while (!condition) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Perform action when condition is true
        condition = false;
    }

    public synchronized void doNotify() {
        condition = true;
        notify();
    }
}

class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(sharedResource::doWait);
        Thread thread2 = new Thread(sharedResource::doNotify);

        thread1.start();
        thread2.start();
    }
}

```
Lock Example
```
class SharedResource {
    private boolean condition = false;
    private final Lock lock = new ReentrantLock();
    private final Condition conditionVar = lock.newCondition();

    public void doWait() {
        lock.lock();
        try {
            while (!condition) {
                conditionVar.await();
            }
            // Perform action when condition is true
            condition = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void doNotify() {
        lock.lock();
        try {
            condition = true;
            conditionVar.signal();
        } finally {
            lock.unlock();
        }
    }
}

class LockConditionExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(sharedResource::doWait);
        Thread thread2 = new Thread(sharedResource::doNotify);

        thread1.start();
        thread2.start();
    }
}

```
## 10.  What’s the difference between class lock and object lock?
Class lock acts on static block and make static object in the class thread-safe. Example:\
```
public class LockedClass{
    public void classLock(){
        synchronized(LockedClass.class){
            ...
        }
    }
}
```
Object lock acts on non-synchronized bock or method to protect non-static data. Example:\
```
public class LockedClass{
    public void objectLock(){
        synchronized(this){
            ...
        }
    }
}
```
## 11.  What is join() method?
Join() is used to pause the execution of the current thread until the thread that calls join() finished. It is useful to order the threads, like the example code below that t2 waits until t1 finishes
```
public class JoinExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        try {
            t1.join(); // Main thread waits until t1 finishes
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        t2.start();
    }
}
```
## 12.  what is yield() method
When Yield is called, that method gives a chance to other thread with the same priority, though it does not guarantee that another thread will be scheduled immediately.
## 13.  What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A ThreadPool is the idea that there are a pool of threads waiting to be assigned tasks. After their task is completed, the thread is returned to the pool and to be reused for new tasks.\
There are three types of ThreadPools:
- FixedThreadPool: A pool with fixed number of thread
```
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
```
- CachedThreadPool: Creates new thread when needed, while also reusing returned threads.
```
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```
SingleThreadExecutor: Creates a pool with only 1 thread.
```
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
```
TaskQueue is a queue that holds tasks that is waiting to be executed by the threads in pool. For example, in fixedThreadPool when all threads are in use, other in coming tasks will be hold in TaskQueue.
## 14.  Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
The java.util.concurrent library is used to create and manage thread pools. This library provides various utilities for concurrent programming, including thread pools, atomic variables, locks, and more.\
The main interface that provides the core functions of a thread pool is the ExecutorService interface. It is part of the java.util.concurrent package and extends the Executor interface.
## 15.  How to submit a task to ThreadPool?
It can be done by using execute() with Runnable tasks when no return is expected, or submit() with Runnable and Callable when there are return values
```
ExecutorService executorService = Executors.newFixedThreadPool(5);
executorService.execute(task);
Future<?> future = executorService.submit(task);
```
## 16.  What is the advantage of ThreadPool?
- Improve performance: Creating and destroying thread can consume many resource. By controlling the number of threads and reusing thread many resources can be saved
- Better management of Thread: ThreadPool provides automatic lifecycle management of the threads simplifies debugging and maintenance.
## 17.  Difference between shutdown() and shutdownNow() methods of executor
### shutDown()
- It shuts down by that previously submitted tasks are executed, but no new tasks will be accepted.
- The method does not block, it returns immediately. The service continues executing existing tasks until all have completed.
### shutDownNow()
- It attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were waiting to be executed.
- The method does not block; it returns immediately.
## 18.  What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic classes is meant to provide lock-free, thread-safe variables. It is used when we want to use a variable for different thread, while wanting the threads not to interfere each other on that variable.\
The main types of Atomic Classes used are AtomicBoolean, AtomicInteger and AtomicLong.
```
public class AtomicIntegerExample {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("Initial value: " + atomicInteger.get());
        atomicInteger.incrementAndGet();
        System.out.println("After increment: " + atomicInteger.get());
        atomicInteger.addAndGet(10);
        System.out.println("After adding 10: " + atomicInteger.get());
    }
}
```
## 19.  What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections are the thread-version of many popular collection classes we often use.\
Array                   ->   CopyOnWriteArray\
HashMap                 ->   ConcurrentHashMap\
HashSet/TreeSet         ->   CopyOnWriteArraySet\
ArrayDeque/LinkedList   ->   ArrayBlockingQueue/LinkedBlockingQueue/LinkedBlockingDeque
## 20.  What kind of locks do you know? What is the advantage of each lock?
### synchronized
- Simple to use and understand
- Other threads are on wait while current thread is using the lock
### ReentrantLock:
- More flexible control than synchronized
- Allows methods like timed lock and interrupt lock.
### ReadWriteLock
- Allows multiple read at the same time, but one writing has to wait for rad and blocks all other threads
- Efficient when read is often, but write is rare.
### StampedLockLearn
- Similar to ReadWriteLock, but allows writing during reading.
## 21.  What is future and completableFuture? List some main methods of CompletableFuture.
### Future
- represents the result of a thread. It provides methods to check if the result is ready, to retrieve the result once it is ready, and to cancel it if desired.
- get(), might be blocked, or specify a wait time
- cancel()
- isDone()
### CompletableFuture
- Extends Future to provide non-blocking synchronized code.
- Can handel multiple CompletableFuture results
- thenAccept()/thenApplyAsync(), applies functions to result
- exceptionally() 
- anyOf()/AllOf()
## 22.  Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
See Coding/hw5/src/main/java/com/chuwa/learn
## 23.  Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
1.  One solution use synchronized and wait notify
2.  One solution use ReentrantLock and await, signal
```
     Thread-0: 1
     Thread-1: 2
     Thread-0: 3
     Thread-1: 4
     Thread-0: 5
     Thread-1: 6
     Thread-0: 7
     Thread-1: 8
     Thread-0: 9
     Thread-1: 10
     Process finished with exit code 0
```
Here's the solution using synchronized:
```
public class Test {
    public static int value = 1;
    public static Object object = new Object();
    public static void main(String[] args) {
        RunnableClass runnable = new RunnableClass();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class RunnableClass implements Runnable {
        public void run() {
            while (true) {

                synchronized (object) {
                    if(value > 10) {
                        break;
                    }else{
                        System.out.println(Thread.currentThread().getName()+": "+value);
                        value++;
                        object.notifyAll();
                        try {
                            if(value <= 10) {
                                object.wait();
                            }

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }


        }
    }

}

```
Here's solution using Lock
```
public class Test2 {
    public static int value = 1;
    //public static Object object = new Object();
    public static void main(String[] args) {
        RunnableClass runnable = new RunnableClass();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class RunnableClass implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void run() {
            while(true){
                lock.lock();
                try {
                    while(value <= 10) {
                        System.out.println(Thread.currentThread().getName()+": "+value);
                        value++;
                        condition.signalAll();
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }finally {
                    lock.unlock();
                }
            }


        }
    }

}

```
## 24.  create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run
     sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
```
     Thread-0: 1
     Thread-0: 2
     Thread-0: 3
     Thread-0: 4
     Thread-0: 5
     Thread-0: 6
     Thread-0: 7
     Thread-0: 8
     Thread-0: 9
     Thread-0: 10
     Thread-2: 11
     Thread-2: 12
     Thread-2: 13
     Thread-2: 14
     Thread-2: 15
     Thread-2: 16
     Thread-2: 17
     Thread-2: 18
     Thread-2: 19
     Thread-2: 20
     Thread-1: 21
     Thread-1: 22
     Thread-1: 23
     Thread-1: 24
     Thread-1: 25
     Thread-1: 26
     Thread-1: 27
     Thread-1: 28
     Thread-1: 29
     Thread-1: 30
```
Here's the solution
```
public class Test3 {
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

        }

        Test3.class.notifyAll();
    }
}
```
## 25.  completable future:
### Homework 1: Write a simple program that uses CompletableFuture to asynchronously get the sum and product of two integers, and print the results.
See [Q25a.java](..%2FCoding%2Fhw5%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchuwa%2Flearn%2FQ25a.java)
### Homework 2: Assume there is an online store that needs to fetch data from three APIs: products, reviews, and inventory. Use CompletableFuture to implement this scenario and merge the fetched data for further processing. (需要找public api去模拟，)
- Sign In to Developer.BestBuy.com
- Best Buy Developer API Documentation (bestbuyapis.github.io)
- 可以用fake api https://jsonplaceholder.typicode.com/ \
See [Q25b.java](..%2FCoding%2Fhw5%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchuwa%2Flearn%2FQ25b.java) Because Developer.BestBuy.Com no longer gives API to free emails, I have to use https://jsonplaceholder.typicode.com/ for fake api. Products -> Posts, Reviews -> Comments, Inventory -> Users 
### Homework 3: For Homework 2, implement exception handling. If an exception occurs during any API call, return a default value and log the exception information.
See [Q25c.java](..%2FCoding%2Fhw5%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchuwa%2Flearn%2FQ25c.java) used .exceptionally to the .sendAsync function to handle exceptions