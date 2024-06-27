package ThreadTest;

public class SharedObject {
    private int data;

    public synchronized void writeData(int data) {
        this.data = data;
        System.out.println("Data written: " + data);
    }

    public synchronized int readData() {
        System.out.println("Data read: " + data);
        return data;
    }
}