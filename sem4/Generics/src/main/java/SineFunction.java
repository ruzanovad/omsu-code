import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class SineFunction implements ISegmentFunction{
    private double a;
    private double b;

    private double A;
    private double B;

    public SineFunction(double a, double b, double a1, double b1) {
        if (a > b) {
            throw new IllegalArgumentException("Wrong segment");
        }
        this.a = a;
        this.b = b;
        A = a1;
        B = b1;
    }

    @Override
    public double getLeft() {
        return a;
    }

    @Override
    public double getRight() {
        return b;
    }

    @Override
    public double getValue(double x) {

        if (x> b || x < a)
            throw new IllegalArgumentException("Wrong x");
        return A * sin(B*x);
    }
}
