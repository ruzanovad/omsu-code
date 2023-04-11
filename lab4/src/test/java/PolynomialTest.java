import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialTest {
    private static Polynomial p1;
    private static Polynomial p2;
    private static Polynomial p3;
    private static Polynomial p4;
    private static Polynomial p5;
    @BeforeClass
    public static void setUp(){
        double[] arr1 = new double[]{1, 1, 1, 1, 1};
        double[] arr2 = new double[]{1, 2, 0};
        double[] arr3 = new double[]{2, 1};
        double[] arr4 = new double[]{1, -2, 1};
        double[] arr5 = new double[]{6};
        p1 = new Polynomial(arr1);
        p2 = new Polynomial(arr2);
        p3 = new Polynomial(arr3);
        p4 = new Polynomial(arr4);
        p5 = new Polynomial(arr5);


    }

    @Test
    public void degree() {
        assertEquals(4, p1.degree());
        assertEquals(1, p2.degree());
        assertEquals(1, p3.degree());
        assertEquals(2, p4.degree());
        assertEquals(0, p5.degree());
    }

    @Test
    public void getValue() {
        assertEquals(1, p1.getValue(0), 1e-9);
        assertEquals(3, p2.getValue(1), 1e-9);
        assertEquals(1, p3.getValue(-1), 1e-9);
        assertEquals(0, p4.getValue(1), 1e-9);
        assertEquals(6, p5.getValue(34345234), 1e-9);
    }

    @Test
    public void getDerivative() {
        assertEquals(10, p1.getDerivative(1), 1e-5);
        assertEquals(2, p2.getDerivative(1), 1e-5);
        assertEquals(1, p3.getDerivative(0), 1e-5);
        assertEquals(2, p4.getDerivative(2), 1e-5);
        assertEquals(0, p5.getDerivative(-14343), 1e-5);
    }

    @Test
    public void toStringTest(){
        double[] arr1 = {1, -2, 1};
        double[] arr2 = {2, 1, -1};
        double[] arr3 = new double[]{1, 2.2, 0, 0};
        double[] arr4 = new double[]{-1};
        // {1, -2}
        Polynomial polynomial = new Polynomial(arr1);
        Polynomial p = new Polynomial(arr2);
        Polynomial p1 = new Polynomial(arr3);
        assertEquals("x^2 - 2,000000x + 1,000000", polynomial.toString());
        assertEquals("-x^2 + x + 2,000000", p.toString());
        assertEquals("2,200000x + 1,000000", p1.toString());
    }
    @Test
    public void equalsTest(){
        double[] arr1 = new double[]{1, 1, 1, 1, 1};
        double[] arr2 = new double[]{1, 2, 0};
        double[] arr3 = new double[]{1, 2, 0, 0};
        double[] arr4 = new double[]{1, 1, 1, 1, 1};
        Polynomial q1 = new Polynomial(arr1);
        Polynomial q2 = new Polynomial(arr2);
        Polynomial q3 = new Polynomial(arr3);
        Polynomial q4 = new Polynomial(arr4);
        assertNotEquals(q1, q2);
        assertEquals(q2, q3);
        assertEquals(q1, q4);
    }
}