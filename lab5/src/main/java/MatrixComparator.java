import java.util.Comparator;

public class MatrixComparator implements Comparator<Matrix> {
    @Override
    public int compare(Matrix o1, Matrix o2) {
        if (Math.abs(o1.getDeterminant() - o2.getDeterminant())< 1e-9)
            return 0;
        if (o1.getDeterminant()>o2.getDeterminant())
            return 1;
        return -1;
    }
}
