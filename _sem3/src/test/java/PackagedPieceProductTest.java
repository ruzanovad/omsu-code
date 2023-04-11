import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackagedPieceProductTest {
    private PackagedPieceProduct p1;
    private PackagedPieceProduct p2;
    private PackagedPieceProduct p3;

    @Before
    public void setUp(){
        PieceProduct w1 = new PieceProduct("Product 1", "Description", 1.5);
        p1 = new PackagedPieceProduct(w1,10, new Packaging("Packaging 1", 0.2));
        PieceProduct w2 = new PieceProduct("Product 2", "Description", 0.08);
        p2 = new PackagedPieceProduct(w2, 1, new Packaging("Packaging 2", 0.44));
        PieceProduct w3 = new PieceProduct("Product 3", "Description", 12);
        p3 = new PackagedPieceProduct(w3, 5, new Packaging("Packaging 3", .024));
    }

    @Test
    public void getNetWeight() {
        assertEquals(15, p1.getNetWeight(), 1e-10);
        assertEquals(0.08, p2.getNetWeight(), 1e-10);
        assertEquals(60, p3.getNetWeight(), 1e-10);
    }

    @Test
    public void getGrossWeight() {
        assertEquals(15.2, p1.getGrossWeight(), 1e-10);
        assertEquals(0.52, p2.getGrossWeight(), 1e-10);
        assertEquals(60.024, p3.getGrossWeight(), 1e-10);
    }

    @Test
    public void getNumber() {
        assertEquals(10, p1.getNumber());
        assertEquals(1, p2.getNumber());
        assertEquals(5, p3.getNumber());

    }
}