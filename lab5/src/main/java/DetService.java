public class DetService {
    public static double detByDef(Matrix matrix) {
        if (matrix.N == 1) {
            return matrix.getElem(0, 0);
        }
        if (matrix.N == 2) {
            return (matrix.getElem(0, 0) * matrix.getElem(1, 1)) -
                    (matrix.getElem(0, 1) * matrix.getElem(1, 0));
        }
        double sum = 0.0;
        int sign = -1;
        for (int i = 0; i < matrix.N; ++i) {
            sign *= -1;
            sum += sign * matrix.getElem(0, i) * detByDef(createSubMatrix(matrix, 0, i));
        }
        return sum;
    }


    public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(matrix.N - 1);
        int r = -1;
        for (int i = 0; i < matrix.N; ++i) {
            if (i == excluding_row)
                continue;
            r++;
            int c = -1;
            for (int j = 0; j < matrix.N; ++j) {
                if (j == excluding_col)
                    continue;
                mat.setElem(r, ++c, matrix.getElem(i, j));
            }
        }
        return mat;
    }
}


