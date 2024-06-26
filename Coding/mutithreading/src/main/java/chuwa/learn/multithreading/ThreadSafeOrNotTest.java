package chuwa.learn.multithreading;


public class ThreadSafeOrNotTest {
//    public static void main(String[] arg) throws InterruptedException {
//        System.out.println("===Start testGlobalCount ===");
//        testGlobalCount();
//        System.out.println("===End testGlobalCount ===");
//
//        System.out.println("===Start testCount ===");
//        testCount();
//        System.out.println("===End testCount ===");
//
//        System.out.println("===Start testSyncronizedCountMethod ===");
//        testSyncronizedCountMethod();
//        System.out.println("===End testSyncronizedCountMethod ===");
//
//        System.out.println("===Start testSyncronizedCountWithTwoLocks ===");
//        testSyncronizedCountWithTwoLocks();
//        System.out.println("===End testSyncronizedCountWithTwoLocks ===");
//
//        System.out.println("===Start testAtomicCount ===");
//        testAtomicCount();
//        System.out.println("===End testAtomicCount ===");
//    }
    public static void testGlobalCount() {
        new Thread(() -> Counter.setGlobalCount(1)).start();
        new Thread(() -> Counter.setGlobalCount(2)).start();
        new Thread(() -> Counter.setGlobalCount(3)).start();

        System.out.println(Counter.getGlobalCount());
    }

    public static void testCount() throws InterruptedException {

        Counter counter = new Counter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    public static void testSyncronizedCountMethod() throws InterruptedException {

        Counter counter = new Counter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    public void testSyncronizedCount() throws InterruptedException {

        SynchronizedCounter counter = new SynchronizedCounter();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }

    public static void testSyncronizedCountWithTwoLocks() throws InterruptedException {

        SynchronizedCounterWithTwoLocks counter = new SynchronizedCounterWithTwoLocks();
        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }


    public static void testAtomicCount() throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> System.out.println("t1: " + counter.incrementCount()));
        Thread t2 = new Thread(() -> System.out.println("t2: " + counter.decrementCount()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final result: " + counter.getCount());
    }
}


