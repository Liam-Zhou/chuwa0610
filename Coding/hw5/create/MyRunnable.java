
public class MyRunnable implements Runnable {
   @Override
   public void run() {
      System.out.println("Start new thread using Runnable");
      try {
         Thread.sleep(5000);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }
}