package chuwa.learn.multithreading;

public class SynchronizedCounter {
    private Integer count = 0;
    final Object locker = new Object();

    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // use synchronize locker
            synchronized (locker) {
                count++;
            }
        }
        return count;
    }
    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // use synchronize locker
            synchronized (locker) {
                count--;
            }
        }
        return count;
    }

    public Integer getCount() {
        return count;
    }
}
