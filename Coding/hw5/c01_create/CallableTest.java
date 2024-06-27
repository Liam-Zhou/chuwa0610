package com.chuwa.learn.multithread.hw5.c01_create;

import java.util.concurrent.Callable;

public class CallableTest {
    public static void main(String[] args) {
        final int n = 10; // should be final or effective final because of used by lambda
        Callable<Integer> sumTask = () -> {
            int sum = 0;
            for (int i = 0; i <= n; ++i)
                sum += i;
            return sum;
        };

        try {
            Integer call = sumTask.call();
            System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
