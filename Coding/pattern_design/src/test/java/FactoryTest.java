import chuwa.com.factory.Shape;
import chuwa.com.factory.ShapeFactory;
import org.junit.Test;


public class FactoryTest {
    @Test
    public void test() {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
