package com.chuwa.learn.multithread.hw5.c02_basic_thread_handling;

import org.junit.Test;

public class InterrruptTest {
    @Test
    public void testIsInterrupted() throws InterruptedException {
        Thread t = new myThreadWithIsInterrupted();
        execute(t);
    }

    @Test
    public void testInterruptWithWhileTrue() throws InterruptedException {
        Thread t = new WhileTrue();
        execute(t);
    }

    @Test
    public void testInterruptSleep() throws InterruptedException {
        Thread t = new SleepForever();
        execute(t);
    }

    private void execute(Thread t) throws InterruptedException {
        t.start();
        Thread.sleep(500);
        // 用interrupt 发起中断请求, 但线程什么时候相应 要看代码
        t.interrupt();
        t.join();
        System.out.println("end");
    }

}


class myThreadWithIsInterrupted extends Thread {
    @Override
    public void run() {
        int n = 0;
        // Question, can be interrupted? yes
        // Question, where is isInterrupted() from? isInterrupted() from Thread
        // 这里用while 一直检查 所以能response
        while(!isInterrupted()) {
            n ++;

            System.out.println(n + " " + "hello!");
        }
    }
}

class WhileTrue extends Thread {
    @Override
    public void run() {
        int n = 0;
        // cannot be interrupted
        while(true) {
            n ++;

            System.out.println(n + " hello");
        }
    }
}

class SleepForever extends Thread {
    @Override
    public void run() {
        int n = 0;
        // Question, can be interrupted? Yes
        try {
            Thread.sleep(5000 * 1000);
        } catch (InterruptedException e) {
            System.out.println("t thread is interrupted");
            e.printStackTrace();
        }
    }
}