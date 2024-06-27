package HW5.coding.createthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    // 里面的参数是一个runable的实现类
    // es.submit(new Task("name"));
    // new Thread(new Task("name"))
    public static void main(String[] args) {
        ExecutorService es =  Executors.newFixedThreadPool(4);// like new thread
        for (int i = 0; i < 6; i++) {
            es.submit(new myRunnable());// like start();
        }
        es.shutdown();
    }
}
class myRunnable implements  Runnable {

    @Override
    public void run() {
        System.out.println("thread is start");
    }
}
