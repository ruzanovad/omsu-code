import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTest {

    private Goods g1;
    private Goods g2;

    @Before
    public void setUp(){
        Packaging brownPackage = new Packaging("Brown package", 0.22);
        Packaging bluePackage = new Packaging("Blue package", 0.1);

        PackagedWeightedProduct whiteRice = new PackagedWeightedProduct(
                new WeightedProduct("White rice", "Very good rice"), 1, brownPackage);
        PackagedWeightedProduct brownRice = new PackagedWeightedProduct(
                new WeightedProduct("Brown rice", "That rice is even better"), 1.5, brownPackage);
        PackagedPieceProduct meringues = new PackagedPieceProduct(
                new PieceProduct("Meringue", "Candy", 0.12), 5,  bluePackage);
        PackagedPieceProduct lollies = new PackagedPieceProduct(
                new PieceProduct("Lollipop", "Candy", 0.06), 100, bluePackage);
        PackagedPieceProduct cake1 = new PackagedPieceProduct(
                new PieceProduct("Cake", "From Wonderland", 1.3), 1, brownPackage);
        PackagedPieceProduct cake2 = new PackagedPieceProduct(
                new PieceProduct("Cake", "From Wonderland", 1.3), 1, bluePackage);
        PackagedWeightedProduct apples = new PackagedWeightedProduct(
                new WeightedProduct("Apples", "Green apples"), 2.157, bluePackage);

        IFoldable[] packagedProducts1 = {whiteRice, cake1, lollies, brownRice};
        IFoldable[] packagedProducts2 = {meringues, apples, cake2, cake1};

        g1 = new Goods("Goods 1", packagedProducts1);
        g2 = new Goods("Goods 2", packagedProducts2);
    }

    @Test
    public void getWeight() {
        assertEquals( 10.56, g1.getWeight(), 1e-10);
        assertEquals( 5.877, g2.getWeight(), 1e-10);
    }
}