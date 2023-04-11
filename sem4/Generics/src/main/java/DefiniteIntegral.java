import static java.lang.Math.abs;

public class DefiniteIntegral<T extends ISegmentFunction> implements Functional<T> {
    private double a;
    private double b;

    public DefiniteIntegral(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getValue(T func) {

        double result = 0;
        double h = (b - a) / 1e8;

        for (int i = 0; i < 1e8; i++) {
            result += func.getValue(a + h / 2 + i * h);
        }

        result *= h;
        return result;
    }
}