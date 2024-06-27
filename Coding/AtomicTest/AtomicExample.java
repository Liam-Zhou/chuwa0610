package AtomicTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static AtomicReference<String> atomicReference = new AtomicReference<>("Default Value");
    public void increamentAtomicInteger() {
        atomicInteger.incrementAndGet();
    }
    public int getAtomicInteger() {
        return atomicInteger.get();
    }
    public String getAtomicReference() {
        return atomicReference.get();
    }
    public void setAtomicReference(String atomicReference) {
        this.atomicReference.set(atomicReference);
    }

    public static void main(String[] args) throws InterruptedException {

        AtomicExample atomicExample = new AtomicExample();

//        Thread t1 = new Thread(atomicExample::increamentAtomicInteger);
//        Thread t2 = new Thread(atomicExample::increamentAtomicInteger);
//        t1.start();
//        t2.start();
//        System.out.println("Final counter value: " + atomicExample.getAtomicInteger());

        Thread t3 = new Thread(() -> atomicExample.setAtomicReference("thread 3 hello"));
        Thread t4 = new Thread(() -> atomicExample.setAtomicReference("thread 4 hello"));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("Final message: " + atomicExample.getAtomicReference());


        System.out.println(atomicInteger.get());
    }



}
