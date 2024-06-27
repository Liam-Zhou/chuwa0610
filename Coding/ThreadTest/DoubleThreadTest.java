package ThreadTest;




public class DoubleThreadTest {

    public void PrintNum(int num,int del) {
        for(int i=0;i<=num;i++) {
            if(i%2==del) {
                System.out.print(i+" ");
            }
        }
    }

    public static void main(String[] args) {
        DoubleThreadTest test = new DoubleThreadTest();

        Thread t1 = new Thread(() -> test.PrintNum(10,1));
        Thread t2 = new Thread(() -> test.PrintNum(10,0));

        t1.start();
        t2.start();

    }

}
