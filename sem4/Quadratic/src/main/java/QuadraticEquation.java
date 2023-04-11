import java.util.Objects;

public class QuadraticEquation {
    private double quadratic;
    private double linear;
    private double constant;

    public QuadraticEquation(double quadratic, double linear, double constant) {
        this.quadratic = quadratic;
        this.linear = linear;
        this.constant = constant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadraticEquation that = (QuadraticEquation) o;
        return Double.compare(that.quadratic, quadratic) == 0 && Double.compare(that.linear, linear) == 0 && Double.compare(that.constant, constant) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quadratic, linear, constant);
    }

    public double getQuadratic() {
        return quadratic;
    }

    public void setQuadratic(double quadratic) {
        this.quadratic = quadratic;
    }

    public double getLinear() {
        return linear;
    }

    public void setLinear(double linear) {
        this.linear = linear;
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    public double[] solve() {
        double D = linear * linear - 4 * quadratic * constant;
        if (quadratic != 0) {
            if (D > 0) {
                double x1 = (-linear - Math.sqrt(D)) * 0.5 / quadratic;
                double x2 = (-linear + Math.sqrt(D)) * 0.5 / quadratic;
                return new double[]{x1, x2};
            } else if (D < 0) {
                return new double[]{Double.NEGATIVE_INFINITY};
            } else {
                double x = (-linear) * 0.5 / quadratic;
                return new double[]{x};
            }
        } else {
            if (linear == 0.0) {
                if (constant == 0.0) {
                    return new double[]{Double.POSITIVE_INFINITY};
                } else {
                    return new double[]{};
                }
            } else {
                double x1 = -constant / linear;
                return new double[]{x1};
            }
        }
    }
}
