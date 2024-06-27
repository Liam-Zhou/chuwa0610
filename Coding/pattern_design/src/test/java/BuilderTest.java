import chuwa.com.builder.House;
import org.junit.Test;

public class BuilderTest {
    @Test
    public void test() {
        House house = new House.HouseBuilder()
                .setFoundation("Concrete")
                .setStructure("Wood")
                .setRoof("Tiles")
                .setFurnished(true)
                .setPainted(false)
                .build();

        System.out.println(house);
    }
}
