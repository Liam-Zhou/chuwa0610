package HW5.coding.createthread;

public class MyRunnable implements  Runnable{
    @Override
    public void run() {
        // runnable 接口没有返回值
        System.out.println("start a new thread using runnable");
    }

    public static void main(String[] args) {
        MyRunnable t = new MyRunnable();
        Thread thread = new Thread(t);// new Thread(MyRunnable());
        thread.start();
    }
}

