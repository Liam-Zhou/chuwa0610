package HW5.coding;

public class ThreadSafeClass {
    /**
     * when there are multi thread access intance, only has one intance be created
     */
}
 class  Singleton {
        //method1 -- lazy initialization
        // when  first time to  request instance, the instance is creacted
        private  static   Singleton instance;
        private  Singleton() {};
        public  static  synchronized Singleton getInstance() {
            //静态方法属于类，所以要锁定整个singlton的类，才能保证getInstance方法是线程安全的
            //即使多个线程同时调用 getInstance 方法，只有一个线程能够进入同步块并创建实例
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
}
class  Singleton1 {
    private  static  volatile  Singleton1 intance;
    private  Singleton1(){};
    public  static  Singleton1 getInstance() {
        if (intance == null) {// 先判断实例是否存在，不存在才创建新的实例，但是创建的时候，给类加了锁，
            //多线程下，只能有一个进入，创建一个对象，避免多个实例对象。符合单例模式
            synchronized (Singleton1.class) {
                if (intance == null) {
                    intance = new Singleton1();
                }
            }
        }
        return intance;
    }
}

