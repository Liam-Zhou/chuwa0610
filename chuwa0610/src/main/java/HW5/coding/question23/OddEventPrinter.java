package HW5.coding.question23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** solution2: ReentrantLock and await, signal
 *
 */
public class OddEventPrinter {
     private  static  final  Object monitor  = new Object();
     private  static  int value = 1;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        new Thread(runnable).start();// thread1
        new Thread(runnable).start();// thread2

    }
    // create a thread
    static class  PrintRunnable implements  Runnable {
        private  final Lock lock = new ReentrantLock();
        private  final Condition condition  = lock.newCondition();

        @Override
        public void run() {
            lock.lock();//get lock
            try {
                while (value < 10) {
                    //打印出1
                    System.out.println(Thread.currentThread().getName() + ":" + value++);// value return first, then + 1
                    condition.signalAll();//wake up another thread, to gain lock
                    try {
                        condition.await(); // wait, and release lock，then another thread can get lock
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } finally {
                lock.unlock();// release lock
            }
        }
    }
}
