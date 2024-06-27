
import chuwa.com.behavior.BinaryObserver;
import chuwa.com.behavior.HexaObserver;
import chuwa.com.behavior.Subject;
import org.junit.Test;

public class BehaviorTest {
    @Test
    public void test() {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
