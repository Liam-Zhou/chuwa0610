package com.chuwa.learn.multithread.hw5.c02_basic_thread_handling;

// GC is deamon
// question: 当其它线程都运行完后，守护线程会结束么？
//  yes,
// question: 当main 运行完后，守护线程会结束么？
// not necessary, will check if other non-daemon thread run
public class Daemon {
    public static void main(String[] args) {
        Thread ppl = new Thread(new People());
        Thread god = new Thread(new God());
        Thread god2 = new Thread(new God());

        god.setDaemon(true);
        god.start();
        ppl.start();
        god2.setDaemon(true);
        god2.start();// non-deamon thread finished, jvm will exit
        System.out.println("");

    }
}

class People implements Runnable {
    @Override
    public void run () {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ppl live");
        }
        System.out.println("Peole thread end");
    }
}

class God implements Runnable {
    @Override
    public void run () {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("God is with you");
        }
    }
}
