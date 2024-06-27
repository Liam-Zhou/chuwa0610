package ThreadTest;

public class ThreeThreadTest {



    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 11; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
           public void run() {
               for (int i = 21; i <= 22; i++) {
                   System.out.println(Thread.currentThread().getName() + ":" + i);
               }
           }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}
