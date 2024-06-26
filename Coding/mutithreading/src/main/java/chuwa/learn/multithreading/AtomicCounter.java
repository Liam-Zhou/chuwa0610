package chuwa.learn.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public AtomicInteger incrementCount() {
        int k = 10000;
        while(k-- > 0) {
            count.incrementAndGet();
        }
        return count;
    }

    public AtomicInteger decrementCount() {
        int k = 10000;
        while(k-- > 0) {
            count .decrementAndGet();
        }
        return count;
    }

    public AtomicInteger getCount() {
        return count;
    }
}
