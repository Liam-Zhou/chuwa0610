package SingletonClass;


public class Singleton {

    private static Singleton instance = null;

    private Singleton(){

    }
    public static synchronized  Singleton getInstance(){
         if(instance == null) {
             instance = new Singleton();
         }
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1.instance);
        System.out.println(singleton2.instance);
        System.out.println(singleton1 == singleton2);  // Output: true
    }

}
