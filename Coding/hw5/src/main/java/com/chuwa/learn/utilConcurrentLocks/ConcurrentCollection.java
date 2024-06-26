package com.chuwa.learn.utilConcurrentLocks;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * interface	non-thread-safe	    thread-safe
 * List	        ArrayList	        CopyOnWriteArrayList
 * Map	        HashMap	            ConcurrentHashMap
 * Set	        HashSet/TreeSet	    CopyOnWriteArraySet
 * Queue	    ArrayDeque/LinkedList	ArrayBlockingQueue/LinkedBlockingQueue
 * Deque	    ArrayDeque/LinkedList	LinkedBlockingDeque
 */
public class ConcurrentCollection {
    private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 10;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//        HashMap<Integer, Integer> map = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        // 启动多个线程对ConcurrentHashMap进行写操作
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    map.put(threadId * OPERATIONS_PER_THREAD + j, threadId);
                }
            });
        }

        // 等待所有写线程完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // 启动多个线程对ConcurrentHashMap进行读操作
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < THREAD_COUNT * OPERATIONS_PER_THREAD; j++) {
                    if (map.get(j) == null) {
                        System.out.println("Missing key: " + j);
                    }
                }
            });
        }

        // 等待所有读线程完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final map size: " + map.size());
        System.out.println("Expected map size: " + (THREAD_COUNT * OPERATIONS_PER_THREAD));
    }
}