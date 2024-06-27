package HW5.coding.createthread;

public class MyThread extends  Thread {
    @Override
    public  void run () {
        System.out.println("start new thread ");
    }
    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
    }
}


