package com.chuwa.exercise.t08_multithreading;

public class MyThreadRunnable implements Runnable {
    private String name;

    public MyThreadRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("this is a thread" + i + " " + name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
