## 4.  Difference between Runnable and Callable?
- runnable has no return
- callable has a return
## 5.  What is the difference between t.start() and t.run()?
- t.start() starts a new thread to excute the new task
- t.run() excute the stask in current thread;
## 6.  Which way of creating threads is better: Thread class or Runnable interface?
runnable is better;
Thread class is  a class, if the class already be extend, we can not extend again,
but, runnable is interface,we can use it to execute a lot of implemented class, 
## 7.  What are the thread statuses?
in thread lifecycle, thread has 5 status
- new: create a new thread object, but not call start()
- runnable: ready and running 
- blocked: thread is blocked by lock
- waiting: the thread in this status is waiting other thread to do something,(notification, interupt)
- timed_waiting: will return after waiting a certain time 
- terminated: the thread has executed.
## 8.  Demondtrate deadlock and how to resolve it in Java code.
- how to resolve： 1.avoid one thread gain multiple lock
- 2 Avoid a thread occupying multiple resources（这里o1锁内占用了o2） in a lock at the same time
```dbn-psql
public class deadLock extends  Thread {
    static final Object o1 = new Object();// static mean all instance share the same object
    static final Object o2 = new Object();
    boolean flag;
    public  deadLock(boolean flag) {
        this.flag = flag;
    }
    @Override
    public  void  run() {
        if (flag) {
            synchronized(o1) {
                System.out.println(Thread.currentThread().getName() + "进入1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName()+"进入2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "进入4");
                }
            }
        }
    }
}

```
## 9.  How do threads communicate each other?
- share variable：multiple thread can access shared variable or object to communication
- wait() or notify(): thread can call wait() to enter waiting status, until other thread call notify() or notifyAll() on the same object to wake up them

## 10.  What’s the difference between class lock and object lock?
- object lock： for instance，every object has own lock， using synchronized(this),a thread can only access the synchronized code block or method of the object when it holds the object lock.
- class lock: The class lock is for the Class object of the class. All instance objects of the class share the same class lock, which can control concurrent access to class-level data members.
  Use the synchronized keyword on static methods, such as public static synchronized void method().
  Use the synchronized keyword on class objects, such as synchronized(MyClass.class)
## 11.  What is join() method?
when there are new thread join, main thread begin to wait, until the thread that called join() method end.
## 12.  what is yield() method?
Give up the CPU privileges of the current thread, change its state from Running to Runnable, and let threads with the same priority get the opportunity to run.
## 13.  What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
### What is ThreadPool
- A mechanism for managing and reusing threads, which can effectively control the creation, destruction and reuse of threads
### How many types of ThreadPool?
  - three types： ExecutorService just a interface， there has several implement class
    - FixedThreadPool：Thread pool with fixed number of threads；
    - CachedThreadPool：Thread pool with dynamically adjusted number of threads based on tasks；
    - SingleThreadExecutor：Thread pool with only single thread execution；
### What is the TaskQueue in ThreadPool
  - A buffer for storing tasks to be executed, effectively managing and scheduling multi-threaded tasks, and improving the system's response speed.
## 14.  Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
  - Usually use the related classes and interfaces provided by the **java.util.concurrent** package in Java
  - ExecutorService provide main functions： such as submit(), shutdown()
## 15.  How to submit a task to ThreadPool?
- call submit() in executorService interface, the Parameters in submit() is a runnable instance（线程通常使用runnable接口实现）.
- then submit the thread task to thread pool.
## 16.  What is the advantage of ThreadPool?
- Using a thread pool usually has better performance and resource management than directly creating a single thread
- The thread pool can control the number of concurrent tasks
- reduce the overhead of thread creation and destruction
- Queues tasks waiting to be executed, automatically manages the life cycle of threads,
- provides a more flexible error handling mechanism.
## 17.  Difference between shutdown() and shutdownNow() methods of executor
- The shutdown() method will wait for the submitted tasks to complete before shutting down the thread pool. There is no return value.
- shutdownNow()will try to stop all executing tasks immediately and return a list of unexecuted tasks.
## 18.  What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
- A class that can ensure atomicity and thread safety of operations in a multi-threaded environment
    - AtomicInteger
    - AtomicLong
    - AtomicBoolean
    - AtomicReference
    - AtomicStampedReference
    - AtomicMarkableReference
- often used method in these atomic class：
     - AddAndGet((int delta): add the input value to original value, return the final result of  the addition.
     - incrementAndGet(): add 1 to original value, then return the final result of the addition.
     - getAndSet(int newValue): Update the value in the instance to the new value and return the old value;
     - getAndIncrement()：Atomically increment the original value in the instance by 1, and return the old value before the increment. 
## 19.  What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
- In a multi-threaded environment, it can ensure thread-safe data structures and provide thread-safe collection operations.
- thread safe collections：
     - CopyOnWriteArrayList - List
     - ConcurrentHashMap - Map
     - CopyOnWriteArraySet -Set
     - ArrayBlockingQueue / LinkedBlockingQueue - Queue
     - LinkedBlockingDeque - Deque
## 20.  What kind of locks do you know? What is the advantage of each lock?
1. synchronized lock：
- synchronized keyword to implement, modify the code block or method
- Automatically manages the acquisition and release of the lock, preventing deadlock and issues related to forgetting to release the lock.
2. ReentrantLock
- Need to manually acquire and release locks, which allows more flexible control of the lock mechanism
## 21.  What is future and completableFuture? List some main methods of ComplertableFuture.
1. Future and CompletableFuture are tools for handling asynchronous computation results. 
They allow you to start other tasks before the computation is completed
and get the result after the computation is complete.
- thenAccept()  handle normal results；
- exceptionally()  handle the exception result；
- thenApplyAsync() Used to serialize another CompletableFuture ；
- anyOf() 和allOf() Used to parallelize multiple CompletableFuture；
## 22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
## 23. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
1. One solution use synchronized and wait notify
2. One solution use ReentrantLock and await, signal
- the code in coding/question23 package
## 24 create 3 threads, one thread ouput 1-10,one thread output 11-20, one thread output 21-22. threads run sequence is random.(solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
- the code in coding/question24 package
## 25. completable future: 
- finished in coding file/completable_future/