package chuwa.learn.multithreading;

public class SynchronizedCounterWithTwoLocks {
    private Integer count = 0;
    final Object locker1 = new Object();
    final Object locker2 = new Object();
    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // use synchronize locker
            synchronized (locker1) {
                count++;
            }
        }
        return count;
    }
    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            // use synchronize locker
            synchronized (locker2) {
                count--;
            }
        }
        return count;
    }
    public Integer getCount() {
        return count;
    }
}
