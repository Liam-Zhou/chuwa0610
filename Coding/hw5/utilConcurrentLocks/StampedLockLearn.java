package com.chuwa.learn.utilConcurrentLocks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockLearn {
    public static void main(String[] args) {
        Point point = new Point();
        Thread tWrite = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                point.move(100, 200);
            }
            System.out.println("Write Done");
        });

        Thread tRead = new Thread(() -> System.out.println(point.distanceFromOrigin()));

        tWrite.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tRead.start();
    }
}

class Point {
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();

        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
