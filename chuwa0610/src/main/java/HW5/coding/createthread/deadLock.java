package HW5.coding.createthread;

public class deadLock extends  Thread {
    static final Object o1 = new Object();// static mean all instance share the same object
    static final Object o2 = new Object();
    boolean flag;
    public  deadLock(boolean flag) {
        this.flag = flag;
    }
    @Override
    public  void  run() {
        if (flag) {
            synchronized(o1) {
                System.out.println(Thread.currentThread().getName() + "进入1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName()+"进入2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "进入4");
                }
            }
        }
    }


}
