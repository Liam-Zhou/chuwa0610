package HW5.coding.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "注意callable 是泛型类，返回值就是泛型的类型，即string";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<String> ft  = new FutureTask<>(mc);//多用了个future对象。来获取call的返回值结果

        Thread newThread = new Thread(ft);
        newThread.start();

        System.out.println(ft.get());// 获取call方法的返回值，是必须的
    }
}
