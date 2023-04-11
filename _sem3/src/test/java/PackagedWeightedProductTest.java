import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackagedWeightedProductTest {

    private PackagedWeightedProduct p1;
    private PackagedWeightedProduct p2;
    private PackagedWeightedProduct p3;

    @Before
    public void setUp(){
        WeightedProduct w1 = new WeightedProduct("Product 1", "Description");
        p1 = new PackagedWeightedProduct(w1, 10, new Packaging("Packaging 1", 0.4));
        WeightedProduct w2 = new WeightedProduct("Product 2", "Description");
        p2 = new PackagedWeightedProduct(w2, 14, new Packaging("Packaging 2", 1));
        WeightedProduct w3 = new WeightedProduct("Product 3", "Description");
        p3 = new PackagedWeightedProduct(w3, 2.3, new Packaging("Packaging 3", .2));
    }

    @Test
    public void getNetWeight() {
        assertEquals(10, p1.getNetWeight(), 1e-10);
        assertEquals(14, p2.getNetWeight(), 1e-10);
        assertEquals(2.3, p3.getNetWeight(), 1e-10);
    }

    @Test
    public void getGrossWeight() {
        assertEquals(10.4, p1.getGrossWeight(), 1e-10);
        assertEquals(15, p2.getGrossWeight(), 1e-10);
        assertEquals(2.5, p3.getGrossWeight(), 1e-10);
    }
}