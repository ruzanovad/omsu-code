import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class RationalFunction implements ISegmentFunction{
    private double a;
    private double b;

    private double A;
    private double B;
    private double C;
    private double D;

    public RationalFunction(double a, double b, double a1, double b1, double c, double d) {
        if ((a > b)) {
            throw new IllegalArgumentException("Wrong segment");
        }
        this.a = a;
        this.b = b;
        A = a1;
        B = b1;
        C = c;
        D = d;
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
        if (x > b || x < a)
            throw new IllegalArgumentException("Wrong x");
        return (A * x + B) / (C * x + D);
    }
}
