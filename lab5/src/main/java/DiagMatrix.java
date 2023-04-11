public class DiagMatrix extends Matrix {

    public DiagMatrix(int N){
        super(N);
    }

    public DiagMatrix(int N, double[] arr) {
        super(N);
        if (arr.length != N) {
            throw new IllegalArgumentException("Wrong size of array");
        }
        for (int i = 0; i < N; ++i) {
            this.arr[i * (N + 1)] = arr[i];
        }
    }

    @Override
    public void setElem(int i, int j, double value) {
        if (Math.abs(value) > 1e-9 && (i != j)) {
            throw new IllegalArgumentException("Trying to make non-diagonal element nonzero");
        }
        super.setElem(i, j, value);
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
}
