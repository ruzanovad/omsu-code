import org.junit.Test;
//добавить единицу в toString
//копирование массива глубокое
import static org.junit.Assert.*;

public class QuadraticPolynomialTest {

    @Test
    public void discriminant() {
        QuadraticPolynomial poly = new QuadraticPolynomial(new double[]{1, 2, 1});
        QuadraticPolynomial except = new QuadraticPolynomial(new double[]{1, 1, 0});
        assertThrows(IllegalArgumentException.class,
                except::discriminant);
        assertEquals(0, poly.discriminant(), 1e-7);
        QuadraticPolynomial poly1 = new QuadraticPolynomial(new double[]{2, 2, 2});
        assertEquals(-12, poly1.discriminant(), 1e-7);
    }

    @Test
    public void roots() {
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{0, 0, 0});
            assertArrayEquals(new double[]{Double.POSITIVE_INFINITY}, qp1.roots(), 1e-6);
        }
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{1, 0, 0});
            assertArrayEquals(new double[0], qp1.roots(), 1e-6);
        }
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{-2, 2, 0});
            assertArrayEquals(new double[]{1}, qp1.roots(), 1e-6);
        }
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{1, 2, 1});
            assertArrayEquals(new double[]{-1, -1}, qp1.roots(), 1e-6);
        }
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{2, 1, -1});
            assertArrayEquals(new double[]{-1, 2}, qp1.roots(), 1e-6);
        }
        {
            QuadraticPolynomial qp1 = new QuadraticPolynomial(new double[]{2, 2, 2});
            assertArrayEquals(new double[]{Double.NEGATIVE_INFINITY}, qp1.roots(), 1e-6);
        }
    }

    @Test
    public void decomposition() {
        {
            QuadraticPolynomial QuadraticPolynomial = new QuadraticPolynomial(new double[]{1, 1, 0});
            assertThrows(IllegalArgumentException.class,
                    QuadraticPolynomial::decomposition);
        }
        {
            QuadraticPolynomial QuadraticPolynomial = new QuadraticPolynomial(new double[]{2, 2, 2});
            assertThrows(IllegalArgumentException.class,
                    QuadraticPolynomial::decomposition);
        }
        {
            QuadraticPolynomial QuadraticPolynomial = new QuadraticPolynomial(new double[]{1, 2, 1});
            Polynomial[] expected = new Polynomial[]{
                    new Polynomial(new double[]{1, 1}),
                    new Polynomial(new double[]{1, 1})
            };
            Polynomial[] result = QuadraticPolynomial.decomposition();
            assertArrayEquals(expected, result);
        }
        {
            QuadraticPolynomial QuadraticPolynomial = new QuadraticPolynomial(new double[]{2, 1, -1});
            Polynomial[] expected = new Polynomial[]{
                    new Polynomial(new double[]{1, 1}),
                    new Polynomial(new double[]{-2, 1})
            };
            Polynomial[] result = QuadraticPolynomial.decomposition();
            assertArrayEquals(expected, result);
        }
    }
}