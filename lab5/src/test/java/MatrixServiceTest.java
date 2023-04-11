import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixServiceTest {

    @Test
    public void arrangeMatrices() {
        Matrix[] m1 = new Matrix[10];
        Matrix[] m2 = new Matrix[10];
        for (int i = 1; i <= 10; ++i) {
            m1[i - 1] = new Matrix(2, new double[]{i, i, -i, -i});
            m2[i - 1] = new Matrix(2, new double[]{i, i, -i, -i});
        }

        MatrixService.arrangeMatrices(m2);
        assertArrayEquals(m1, m2);

        Matrix[] m = new Matrix[]{
                new DiagMatrix(3, new double[]{1, 2, 3}), // 6
                new UpTriangleMatrix(2, new double[]{10, -323294, 0, -1}), // -10
                new DiagMatrix(10, new double[]{-1, 1, -1, 1, -1, 2, 0.5, -1, 2, -2}), //-4
                new Matrix(4, new double[]{1, 1, 1, 1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1}), //8
                new Matrix(3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9}) //0
        };
        MatrixService.arrangeMatrices(m);

        Matrix[] sortedM = new Matrix[]{
                new UpTriangleMatrix(2, new double[]{10, -323294, 0, -1}), // -10
                new DiagMatrix(10, new double[]{-1, 1, -1, 1, -1, 2, 0.5, -1, 2, -2}), //-4
                new Matrix(3, new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), //0
                new DiagMatrix(3, new double[]{1, 2, 3}), // 6
                new Matrix(4, new double[]{1, 1, 1, 1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1}) //8
        };

        assertArrayEquals(sortedM, m);

        DiagMatrix[] d = new DiagMatrix[]{
                new DiagMatrix(3, new double[]{1, 2, 3}), // 6
                new DiagMatrix(4, new double[]{1, 1, 1, 1}), // 1
                new DiagMatrix(2, new double[]{-1, 8}), // -8
                new DiagMatrix(1, new double[]{-8}) // -8
        };

        // устойчивая
        MatrixService.arrangeMatrices(d);

        DiagMatrix[] sortedD = new DiagMatrix[]{
                new DiagMatrix(2, new double[]{-1, 8}),
                new DiagMatrix(1, new double[]{-8}),
                new DiagMatrix(4, new double[]{1, 1, 1, 1}),
                new DiagMatrix(3, new double[]{1, 2, 3})
        };

        assertArrayEquals(sortedD, d);
    }
}