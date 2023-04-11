import org.junit.Test;

import static org.junit.Assert.*;

public class ComparatorDemoTest {

    @Test
    public void sortGoods() {
        Product p1 = new PieceProduct("a", "b", 10);
        Product p2 = new PieceProduct("a", "c", 10);
        Product p3 = new WeightedProduct("b", "c");
        Product p4 = new PackagedWeightedProduct("b", "c", 10, new Packaging("1", 2));
        Product p5 = new PieceProduct("d", "a", 1);
        Product p6 = new PieceProduct("e", "a", 1);

        Product[] pp1 = new Product[]{p1, p2, p3, p4, p5, p6};
        Product[] pp2 = new Product[]{p1, p2, p4, p3, p5, p6};
        Product[] pp3 = new Product[]{p6, p1, p3, p2};
        Product[] pp4 = new Product[]{p1, p2, p3, p6};

        ProductComparator p = new ProductComparator();

        assertArrayEquals(pp1, ComparatorDemo.sortGoods(pp1, p));
        assertArrayEquals(pp2, ComparatorDemo.sortGoods(pp2, p));
        assertArrayEquals(pp4, ComparatorDemo.sortGoods(pp3, p));
    }
}