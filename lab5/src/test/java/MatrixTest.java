import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void setElem() {
        Matrix m1 = new Matrix(3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        assertThrows(IllegalArgumentException.class, () -> m1.setElem(3, 3, 1));

        m1.setElem(0, 0, 0);
        assertEquals(0, m1.getElem(0, 0), 1e-9);

        assertThrows(IllegalArgumentException.class, () -> new DiagMatrix(3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));

        DiagMatrix d1 = new DiagMatrix(3, new double[]{1, 2, 3});

        assertThrows(IllegalArgumentException.class, () -> d1.setElem(0, 2, 2));

        d1.setElem(0, 2, 0);

        assertThrows(IllegalArgumentException.class, () -> new UpTriangleMatrix(2, new double[]{1, 0, 1, 1}));

        UpTriangleMatrix u1 = new UpTriangleMatrix(4, new double[]{1, -1, 1, 1, 0, -2, -2, 2, 0, 0, -3, 3, 0, 0, 0, -4});

        assertThrows(IllegalArgumentException.class, () -> u1.setElem(1, 0, 3));

        u1.setElem(3, 3, -1);
    }

    @org.junit.Test
    public void getDeterminant() {
        Matrix m1 = new Matrix(3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        assertEquals(0, m1.getDeterminant(), 1e-9);
        m1.setElem(0, 0, 0);
        assertEquals(3, m1.getDeterminant(), 1e-9);
        m1.setElem(0, 2, 0);
        assertEquals(12, m1.getDeterminant(), 1e-9);

        Matrix m2 = new Matrix(3, new double[]{
                0, 2, 3,
                0, 5, 6,
                0, 8, 9});
        assertEquals(0, m2.getDeterminant(), 1e-9);
        m2.setElem(2, 0, 1);
        assertEquals(-3, m2.getDeterminant(), 1e-9);

        Matrix m3 = new Matrix(4, new double[]{
                1, 1, 1, 1,
                -1, 1, -1, -1,
                1, 1, -1, 1,
                1, 1, 1, -1});
        assertEquals(8, m3.getDeterminant(), 1e-9);

        DiagMatrix d1 = new DiagMatrix(3, new double[]{1, 2, 3});
        assertEquals(6, d1.getDeterminant(), 1e-9);
        d1.setElem(1, 1, 0);
        assertEquals(0, d1.getDeterminant(), 1e-9);
        d1.setElem(1, 1, 1);
        assertEquals(3, d1.getDeterminant(), 1e-9);

        UpTriangleMatrix u1 = new UpTriangleMatrix(4, new double[]{
                1, -1, 1, 1,
                0, -2, -2, 2,
                0, 0, -3, 3,
                0, 0, 0, -4});
        assertEquals(-24, u1.getDeterminant(), 1e-9);
        u1.setElem(0, 2, 0);
        assertEquals(-24, u1.getDeterminant(), 1e-9);
        u1.setElem(2, 3, -100);
        assertEquals(-24, u1.getDeterminant(), 1e-9);
        u1.setElem(2, 2, 1);
        assertEquals(8, u1.getDeterminant(), 1e-9);
    }

    @Test
    public void testN4() {
        Matrix m = new Matrix(4, new double[]{0, 1, 1, 1, 5, 0, 0, 0, 6, 0, 0, 1, 7, 11, 1, 8});
        assertEquals(-50, m.getDeterminant(), 1e-9);
        Matrix g = new Matrix(4, new double[]{1,1,1,1, 0, 0,3, 2, 0, 0, 1, 1, 0, 0, 1, 1});
        assertEquals(0, g.getDeterminant(), 1e-9);
        Matrix d = new Matrix(4, new double[]{1, 2, 1, 1, 0, 0, 3, 2, 0, 0, 1, 1, 0, 3, 1, 1});

        assertEquals(3, d.getDeterminant(), 1e-9);
        Matrix e = new Matrix(4, new double[]{1, 2, 1, 1, 0, -2, 3, 2, 0, 0, 1, 1, 0, 0, 0, 1});
        assertEquals(-2, e.getDeterminant(), 1e-9);
        Matrix f = new Matrix(4, new double[]{1, 2, 1, 1, 0, 0, 3, 2, 0, 0, 1, 1, 1, -2, 0, 1});
        assertEquals(-4, f.getDeterminant(), 1e-9);

        Matrix h = new Matrix(4, new double[]{1, 2, 0, 1, 0, 1, 3, 2, 0, 0, 0, 0, 1, 0, 0, 1});
        assertEquals(0, h.getDeterminant(), 1e-9);
    }

    @Test
    public void isCorrectDoubles() {
//        double x = 4.0181814073817855E15;
//        double y =4.018181407381806E15;
//        for (int i = 1; i <= 6; i++) {
        for (int i = 1; i <=10; ++i){
            double[] mat = new double[i * i];
            for (int j = 0; j < i * i; ++j) {
                mat[j] = ((Math.random() - .5));
            }
            Matrix matrix = new Matrix(i, mat);
            assertEquals(DetService.detByDef(matrix), matrix.getDeterminant(), 1e-3);
        }
    }

    @Test
    public void isCorrectInts() {
        for (int i = 1; i <= 11; i++) {
            double[] mat = new double[i * i];
            for (int j = 0; j < i * i; ++j) {
                mat[j] = (int) ((Math.random() - .5) * 10);
            }
            Matrix matrix = new Matrix(i, mat);
            assertEquals(DetService.detByDef(matrix), matrix.getDeterminant(), 1e-6);
        }
    }

}