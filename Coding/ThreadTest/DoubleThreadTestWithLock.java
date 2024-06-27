package ThreadTest;

public class DoubleThreadTestWithLock {
    private final Object lock = new Object();
    private boolean oddturn = true;

    public void printOdd(int max){
        for(int i = 1; i <= max; i += 2){
            synchronized (lock){
                while (!oddturn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("odd : " + i);
                oddturn = false;
                lock.notifyAll();
            }
        }
    }

    public void printEven(int max){
        for(int i = 2; i <= max; i += 2){
            synchronized (lock){
                while(oddturn){
                    try{
                        lock.wait();
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("even : " + i);
                oddturn = true;
                lock.notifyAll();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DoubleThreadTestWithLock test = new DoubleThreadTestWithLock();
        Thread t1 = new Thread(() -> test.printEven(10));
        Thread t2 = new Thread(() -> test.printOdd(10));
        t1.start();
        t2.start();
    }



}
