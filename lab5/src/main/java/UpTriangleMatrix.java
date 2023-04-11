public class UpTriangleMatrix extends Matrix {

    public UpTriangleMatrix(int N){
        super(N);
    }
    public UpTriangleMatrix(int N, double[] arr) {
        super(N);
        if (arr.length != N * N) {
            throw new IllegalArgumentException("Wrong size of array");
        }
        this.arr = new double[N * N];
        for (int i = 0; i < arr.length; ++i) {
            if (Math.abs(arr[i]) > 1e-9 && i / N > (i % N))
                throw new IllegalArgumentException("Trying to make wrong element nonzero");
            this.arr[i] = arr[i];
        }
    }

    @Override
    public double getDeterminant() {
        if (detFlag) return det;
        detFlag = true;
        det = 1;
        for (int i = 0; i < N; ++i) {
            det *= this.arr[i * (N + 1)];
            if (Math.abs(det) < 1e-9)
                return 0;
        }
        return det;
    }

    @Override
    public void setElem(int i, int j, double value) {
        if (Math.abs(value) > 1e-9 && (i > j)) {
            throw new IllegalArgumentException("Trying to make wrong element nonzero");
        }

        super.setElem(i, j, value);
    }
}
