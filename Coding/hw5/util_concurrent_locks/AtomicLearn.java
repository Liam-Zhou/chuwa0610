import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLearn {
   public static int incrementAndGet(AtomicInteger var) {
      int prev, next;
      do {
         prev = var.get();
         next = prev + 1;
         // Keep looping until actual value equals to expected value.
         // which means latest var.get() == current var.get()
         // return false if actual value is NOT equal to expected value
      } while (!var.compareAndSet(prev, next));
      return next;
   }
}

class IdGenerator {
   AtomicLong var = new AtomicLong(0);

   public long getNextId() {
      return var.incrementAndGet();
   }
}