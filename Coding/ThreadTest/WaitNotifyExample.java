package ThreadTest;

public class WaitNotifyExample {
    private final Object lock = new Object();
    private boolean dataAvailable = false;

    public void produce() {
        synchronized (lock) {
            try {
                System.out.println("Producer waiting...");
                while (!dataAvailable) {
                    lock.wait();
                }
                System.out.println("Producer resumed");
                dataAvailable = false; // Reset the condition
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void consume() {
        synchronized (lock) {
            dataAvailable = true;
            lock.notify();
            System.out.println("Consumer notified producer");
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread producer = new Thread(example::produce);
        Thread consumer = new Thread(example::consume);

        producer.start();
        consumer.start();
    }
}
