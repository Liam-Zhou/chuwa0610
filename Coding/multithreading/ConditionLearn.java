package multithreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLearn {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue2 q = new TaskQueue2();
        List<Thread> ts = new ArrayList<>();

        // create 5 threads
        for (int i = 0; i < 5; i++) {
            // execute task
            Thread consumer = new Thread(() -> {
                while (true) {
                    try {
                        String task = q.getTask();
                        System.out.println(task + " is executed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("Consumer Thread " + i + " start...");
            consumer.start();
            ts.add(consumer);
        }

        Thread producer = new Thread(() -> {
            System.out.println("Producer Thread Run...");
            // create task
            for (int i = 0; i < 15; i++) {
                String s = "t-" + (int) (Math.random() * 1000);
                System.out.println("add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        producer.join();
        Thread.sleep(1000);
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue2 extends Thread {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }

            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}