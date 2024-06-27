package com.chuwa.learn.multithread;

public class c24 {
    private static int n = 1;
    public static synchronized void printNumber() {
        int count = 10;
        while (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void exercie() {
        new Thread(() -> printNumber()).start();
        new Thread(() -> printNumber()).start();
        new Thread(() -> printNumber()).start();


    }
    public static void main(String[] args) {
        exercie();
    }
}
