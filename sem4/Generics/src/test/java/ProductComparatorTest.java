import org.junit.Test;

import static org.junit.Assert.*;

public class ProductComparatorTest {

    @Test
    public void compare() {
        Product p1 = new PieceProduct("a", "b", 10);
        Product p2 = new PieceProduct("a", "c", 10);
        Product p3 = new WeightedProduct("b", "c");
        Product p4 = new PackagedWeightedProduct("b", "c", 10, new Packaging("1", 2));

        ProductComparator p = new ProductComparator();

        assertTrue(p.compare(p1, p2)<0);
        assertEquals(0, p.compare(p3, p4));
        assertTrue(p.compare(p1, p3)<0);
    }
}