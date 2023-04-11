import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PackagedProductSetTest {

    private PackagedProductSet p1;
    private PackagedProductSet p2;

    @Before
    public void setUp() throws Exception {
        Packaging brownPackage = new Packaging("Brown package", 0.22);
        Packaging bluePackage = new Packaging("Blue package", 0.1);
        PackagedWeightedProduct whiteRice = new PackagedWeightedProduct(new WeightedProduct("White rice", "Very good rice"), 1, brownPackage);//
        PackagedWeightedProduct brownRice = new PackagedWeightedProduct(new WeightedProduct("Brown rice", "That rice is even better"), 1.5, brownPackage);
        PackagedPieceProduct meringues = new PackagedPieceProduct(new PieceProduct("Meringue", "Candy", 0.12), 5, bluePackage);//
        PackagedPieceProduct lollies = new PackagedPieceProduct(new PieceProduct("Lollipop", "Candy", 0.06), 100, bluePackage);//
        PackagedPieceProduct cake1 = new PackagedPieceProduct(new PieceProduct("Cake", "From Wonderland", 1.3), 1, brownPackage);//
        PackagedPieceProduct cake2 = new PackagedPieceProduct(new PieceProduct("Cake", "From Wonderland", 1.3), 1, bluePackage);

        IPackaged[] packagedProducts1 = {whiteRice, meringues, cake1, lollies};
        IPackaged[] packagedProducts2 = {whiteRice, cake1,  meringues, cake2, lollies, brownRice};

        p1 = new PackagedProductSet(new Packaging("Package 1", 0.429), packagedProducts1, "Set 1");
        p2 = new PackagedProductSet(new Packaging("Package 2", 0.543), packagedProducts2, "Set 2");
    }

    @Test
    public void getNetWeight() {
        assertEquals(8.9, p1.getNetWeight(), 1e-10);
        assertEquals(11.7, p2.getNetWeight(), 1e-10);
    }


    @Test
    public void getGrossWeight() {
        assertEquals(9.969, p1.getGrossWeight(), 1e-10);
        assertEquals(13.203, p2.getGrossWeight(), 1e-10);
    }
}