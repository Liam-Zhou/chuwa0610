package ThreadTest;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DoubleThreadTestWithReentrantLock {
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private boolean oddturn = false;

    public void printOdd(int max){
        for(int i = 1; i <= max; i += 2){
            lock.lock();
            try{
                while (!oddturn) {
                    oddCondition.await();
                }
                System.out.println("Odd : "+ i);
                oddturn = false;
                evenCondition.signal();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.unlock();
            }
        }
    }

    public void printEven(int max){
        for(int i = 2; i <= max; i += 2){
            lock.lock();
            try{
                while(oddturn){
                    evenCondition.await();
                }
                System.out.println("Even : "+ i);
                oddturn = true;
                oddCondition.signal();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.unlock();
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
