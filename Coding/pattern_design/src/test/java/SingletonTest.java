import chuwa.com.Singleton;
import org.junit.Test;

public class SingletonTest {
    @Test
    public void test() {
        Singleton singleton = Singleton.getInstance();
        singleton.doSomething();
    }
}
