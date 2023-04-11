import java.util.NoSuchElementException;

public class QuadraticService {
    private final QuadraticEquation equation;

    public QuadraticService(QuadraticEquation q) {
        this.equation = q;
    }

    public double findLargestRoot(){
        double[] roots = equation.solve();
        if (roots.length == 0){
            throw new NoSuchElementException("No roots");
        }
        else {
            if (roots[0] == Double.NEGATIVE_INFINITY)
                throw new NoSuchElementException("No roots");
            else{
                return roots[roots.length-1];
            }
        }
    }
}
