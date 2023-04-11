import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialServiceTest {
    static Polynomial p1;
    static Polynomial p2;
    static Polynomial p3;
    static Polynomial p4;
    static Polynomial p5;
    static Polynomial a1;
    static Polynomial a2;
    static Polynomial a3;
    static Polynomial s1;
    static Polynomial m1;


    @BeforeClass
    public static void setUp(){
        p1 = new Polynomial(new double[] {1, 3, 3, 1});
        p2 = new Polynomial(new double[] {1, 2, 1});
        p3 = new Polynomial(new double[] {1, 1});
        p4 = new Polynomial(new double[] {1, 4, 6, 4, 1});
        p5 = new Polynomial(new double[] {0, 1, 1});

        a1 = new Polynomial(new double[]{2, 6, 7, 4, 1});
        a2 = new Polynomial(new double[]{2, 2});
        a3 = new Polynomial(new double[]{2});

        s1 = new Polynomial(new double[] {0, -1, -2, -1});
        m1 = new Polynomial(new double[] {0, 1});

    }


    @Test
    public void add() {
        assertEquals(p2, PolynomialService.add(p5, p3));
        assertEquals(a1, PolynomialService.add(p2, p4));
        assertEquals(a2, PolynomialService.add(p3, p3));
    }

    @Test
    public void sub() {
        assertEquals(new Polynomial(new double[]{0}), PolynomialService.sub(p1, p1));
        assertEquals(s1, PolynomialService.sub(p2, p1));
        assertEquals(p3, PolynomialService.sub(a2, p3));
        assertEquals(p5, PolynomialService.sub(p2, p3));
    }

    @Test
    public void mul() {
        assertEquals(new Polynomial(new double[]{0}),
                PolynomialService.mul(p1, new Polynomial(new double[]{0})));
        assertEquals(p4, PolynomialService.mul(p3, p1));
        assertEquals(p4, PolynomialService.mul(p1, p3));
        assertEquals(p5, PolynomialService.mul(m1, p3));

        assertEquals(a2, PolynomialService.mul(a3, p3));
        assertEquals(p1, PolynomialService.mul(p2, p3));

    }

    @Test
    public void div() {
        assertArrayEquals(new Polynomial[]{
                p3, new Polynomial(new double[]{0})
        },PolynomialService.div(p4, p1));
        assertArrayEquals(new Polynomial[]{
                new Polynomial(new double[]{0}), p1
        },PolynomialService.div(p1, p4));
        assertArrayEquals(new Polynomial[]{
                p2, new Polynomial(new double[]{0})
        },PolynomialService.div(p4, p2));
        assertArrayEquals(new Polynomial[]{
                new Polynomial(new double[]{2, 1}), new Polynomial(new double[]{1})
        },PolynomialService.div(p2, m1));
    }
}