
public class JoinTest {
   public static void main(String[] args) {
      Thread t = new Thread(() -> {
         System.out.println("Thread start");
         try {
            Thread.sleep(2000); // question, who sleep?
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         System.out.println("Thread end");
      });

      System.out.println("Main start");
      t.start();
      try {
         System.out.println("Main thread is stopped and waiting for t thread end");
         t.join(); // main thread wait for t thread end. main thread's status is Timed Waiting.
         // 超时等待状态（timed waiting), 当线程在等待某个条件的发生并且有一个指定的超时时间时进入此状态。
         System.out.println("Main thread continues...");
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("Main stop");
   }
}
