import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private Goods g1;
    private Goods g2;
    private Goods g3;

    private Goods deep;
    private Goods deepDeep;
    private Goods deepDeep2;

    private Goods pieceInside;

    private Filter filter;


    @Before
    public void setUp() {
        filter = new BeginStringFilter("Crab");

        Packaging bluePackage = new Packaging("Blue package", 0.1);

        PackagedWeightedProduct crab = new PackagedWeightedProduct(
                new WeightedProduct("Crab (weighted)", "The best crab!"), 1.23, bluePackage);
        PackagedWeightedProduct crabImitation = new PackagedWeightedProduct(
                new WeightedProduct("Imitation crab", "Now on sale"), 0.8, bluePackage);
        PackagedWeightedProduct whiteRice = new PackagedWeightedProduct(
                new WeightedProduct("White rice", "Very good rice"), 1, bluePackage);
        PackagedWeightedProduct brownRice = new PackagedWeightedProduct(
                new WeightedProduct("Brown rice", "That rice is even better"), 1.5, bluePackage);

        PackagedPieceProduct meringues = new PackagedPieceProduct(
                new PieceProduct("Meringue", "Candy", 0.12), 5, bluePackage);
        PackagedPieceProduct crabSticks = new PackagedPieceProduct(
                new PieceProduct("Crab sticks", "Surimi sticks", 0.1), 12, bluePackage);

        IPackaged[] packagedProducts1 = {crab, crabImitation};
        IPackaged[] packagedProducts2 = {crab, crabImitation, crabSticks, brownRice, meringues};
        IPackaged[] packagedProducts3 = {brownRice, whiteRice, crabImitation};
        IPackaged[] packagedProducts4 = {brownRice, whiteRice, crabSticks};

        PackagedProductSet pSet1 = new PackagedProductSet(bluePackage, packagedProducts1, "Crabs");
        PackagedProductSet pSet2 = new PackagedProductSet(bluePackage, packagedProducts1, "No crab");

        PackagedProductSet pSet3 = new PackagedProductSet(bluePackage, packagedProducts4, "");


        IPackaged[] noCrab = {pSet2, crabImitation, whiteRice};
        IPackaged[] pieceProductInside = {pSet3, pSet1, crab};

        PackagedProductSet deepPackagedSet = new PackagedProductSet(
                bluePackage, noCrab, "Crab is in string actually...");

        PackagedProductSet deepPackagedSet_noCrab = new PackagedProductSet(
                bluePackage, noCrab, "Don't look!");

        PackagedProductSet piece = new PackagedProductSet(
                bluePackage, pieceProductInside, "");

        IPackaged[] deepDeepPackagedCrabs = {deepPackagedSet, pSet1, crabSticks};

        IPackaged[] crabsInside = {deepPackagedSet_noCrab, deepPackagedSet, crabImitation};

        IPackaged[] pieceInsideSet = {whiteRice, brownRice, piece, crab};

        g1 = new Goods("No. 1", packagedProducts1);
        g2 = new Goods("No. 2", (IFoldable[]) packagedProducts2);
        g3 = new Goods("No. 3", (IFoldable[]) packagedProducts3);

        deep = new Goods("Depth lvl 1", (IFoldable[]) noCrab);
        deepDeep = new Goods("Depth lvl 2", (IFoldable[]) deepDeepPackagedCrabs);
        deepDeep2 = new Goods("Depth lvl 2, but with crabs inside", (IFoldable[]) crabsInside);
        pieceInside = new Goods("Depth lvl 2 with piece product inside", (IFoldable[]) pieceInsideSet);
    }

        @Test
    public void countByFilter() {
        assertEquals(1, ProductService.countByFilter(filter, g1));
        assertEquals(2, ProductService.countByFilter(filter, g2));
        assertEquals(0, ProductService.countByFilter(filter, g3));

        assertEquals(0, ProductService.countByFilter(filter, deep));
        assertEquals(3, ProductService.countByFilter(filter, deepDeep));
    }

    @Test
    public void countByFilterDeep() {
        assertEquals(1, ProductService.countByFilterDeep(filter, g1));
        assertEquals(2, ProductService.countByFilterDeep(filter, g2));
        assertEquals(0, ProductService.countByFilterDeep(filter, g3));

        assertEquals(1, ProductService.countByFilterDeep(filter, deep));
        assertEquals(3, ProductService.countByFilterDeep(filter, deepDeep));
        assertEquals(2, ProductService.countByFilterDeep(filter, deepDeep2));
    }

    @Test
    public void checkAllWeighted() {
        assertTrue(ProductService.checkAllWeighted(g1));
        assertFalse(ProductService.checkAllWeighted(g2));
        assertTrue(ProductService.checkAllWeighted(g3));

        assertTrue(ProductService.checkAllWeighted(deep));
        assertFalse(ProductService.checkAllWeighted(deepDeep));
        assertTrue(ProductService.checkAllWeighted(deepDeep2));
        assertFalse(ProductService.checkAllWeighted(pieceInside));
    }
}