package ThreadTest;

public class YieldExample {
    public static void main(String[] args) {
        Thread producer = new Thread(new Producer(), "Thread 1 ");
        Thread consumer = new Thread(new Consumer(), "Thread 2 ");

        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " produced: " + i);
            Thread.yield();  // Suggests yielding control to other threads
        }
    }
}

class Consumer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " consumed: " + i);
            Thread.yield();  // Suggests yielding control to other threads
        }
    }
}
