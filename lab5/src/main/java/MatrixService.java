import java.util.Arrays;

public class MatrixService {

    //    public static<T extends Matrix> void arrangeMatrices(T[] matrices){
    public static void arrangeMatrices(Matrix[] matrices) {
        Arrays.sort(matrices, new MatrixComparator());
    }
}
