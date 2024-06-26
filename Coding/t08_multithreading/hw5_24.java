package com.chuwa.exercise.t08_multithreading;

public class hw5_24 {
    private static int n = 1;

    static synchronized void print_number(){
        int count =10;

        while(count > 0){
            System.out.println(Thread.currentThread()+ ": " + n++);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            count --;
        }
    }
}
