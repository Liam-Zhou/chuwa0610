package com.chuwa.exercise.t08_multithreading;

public class MyThread extends Thread{

    private int threadNumber;
    public MyThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for(int i=0; i < 5; i++){
            System.out.println(i + "From thread " + threadNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
