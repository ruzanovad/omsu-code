import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.abs;

public class Matrix implements IMatrix {
    protected final int N;
    protected double[] arr;
    protected boolean detFlag = false;
    protected double det;

    public Matrix(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("Wrong size of matrix");
        }
        this.N = N;
        arr = new double[N * N];
    }

    public Matrix(int N, double[] array) {
        if (N < 0) {
            throw new IllegalArgumentException("Wrong size of matrix");
        }
        if (array.length != N * N) {
            throw new IllegalArgumentException("Wrong size of array");
        }
        this.N = N;
        arr = new double[N * N];
        System.arraycopy(array, 0, arr, 0, array.length);
    }

    private void checkIndex(int i, int j) {
        if ((i < 0 || i >= N) && (j < 0 || j >= N)) {
            throw new IllegalArgumentException("Wrong values of row and column");
        }
        if (i < 0 || i >= N) {
            throw new IllegalArgumentException("Wrong value of row");
        }
        if (j < 0 || j >= N) {
            throw new IllegalArgumentException("Wrong value of column");
        }
    }

    @Override
    public double getElem(int i, int j) {
        checkIndex(i, j);
        return arr[i * N + j];
    }

    @Override
    public void setElem(int i, int j, double value) {
        checkIndex(i, j);
        if (abs(value - arr[i * N + j]) > 1e-9) detFlag = false;
        arr[i * N + j] = value;

    }

    @Override
    public double getDeterminant() {
        if (detFlag) return det;
        detFlag = true;
        det = 0;
        int swap = 1;
        double[] array = arr.clone();
        // идем не по всем строкам, последняя строка станет такой как надо автоматически
        for (int i = 0; i < N - 1; ++i) {
            //так как при прямом ходе первые i столбцов в i строке 0
            // а если диагональный уже 0?
            int row = i;

            // ищем строчку, в которой iтый столбец не 0
//            j+N -  строка, i - столбец
            for (int j = i; j < N && abs(array[j * N + i]) < 1e-9; ++j) {
                row = j;
            }
            // 4x4:
            // 0 1 2 3
            // 4 5 6 7
            // 8 9 10 11
            // 12 13 14 15
            // i* (N+1) - диагональный
            // 0, 5, 10, 15
            if (abs(array[i * (N + 1)]) < 1e-9) {
                if (row == N - 1) {
                    // если row == N-1, то цикл выше прошелся до конца и не нашел ненулевого элемента в i строке
                    if (i == 0)
                        //нулевая строка
                        return det;
                } else {
                    swap *= -1;
                    //row +1 так как в цикле сработало условие на неравенство нулю и до самого столбца не дошли
                    swapRows(i, row + 1, array);
                }
            }
            for (int j = i + 1; j < N; ++j) {
                // зануляем ведущий столбец под текущей строкой
                if (abs(array[j * N + i]) > 1e-9) {
                    // нет смысла отнимать целую строчку если уже нулевой
                    addRow(i, j, array, -array[i * (N + 1) + (j - i) * N] / array[i * (N + 1)]);
                }
            }
        }
        det = 1;
        for (int i = 0; i < N; ++i) {
            det *= array[i * (N + 1)];
        }
        return det * swap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return N == matrix.N && Arrays.equals(arr, matrix.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    private void swapRows(int i, int j, double[] arr) {
        for (int x = 0; x < N; x++) {
            double temp = arr[i * N + x];
            arr[i * N + x] = arr[j * N + x];
            arr[j * N + x] = temp;
        }
    }

    private void addRow(int from, int to, double[] arr, double coef) {
        for (int x = 0; x < N; x++) {
            arr[to * N + x] += arr[from * N + x] * coef;
        }
    }

}
