package ThreadTest;

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();

        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedObject.writeData(i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        Thread readerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedObject.readData();
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        writerThread.start();
        readerThread.start();
    }
}
