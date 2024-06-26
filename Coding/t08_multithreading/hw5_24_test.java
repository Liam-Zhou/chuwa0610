package com.chuwa.exercise.t08_multithreading;

import static com.chuwa.exercise.t08_multithreading.hw5_24.print_number;

public class hw5_24_test {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->print_number());
        Thread t2 = new Thread(() ->print_number());
        Thread t3 = new Thread(() ->print_number());

        t1.start();
        t2.start();
        t3.start();
    }

}
