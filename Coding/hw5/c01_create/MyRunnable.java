package com.chuwa.learn.multithread.hw5.c01_create;

public class MyRunnable implements  Runnable{
    @Override
    public void run() {
        System.out.println("Start a new thread from Runnable");
    }
}
