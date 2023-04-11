public class QuadraticPolynomial extends Polynomial {
    public QuadraticPolynomial(double[] arr) {
        super(arr);
        if (degree()>2) {
            throw new RuntimeException();
        }
    }

    public double discriminant() {
        if (arr[2] == 0) {
            throw new IllegalArgumentException();
        }
        return arr[1]*arr[1] - 4 * arr[2] * arr[0];
    }

    public double[] roots() {

        if (arr[2] != 0) {
            double D = discriminant();
            if (D > 0) {
                double x1 = (-arr[1] - Math.sqrt(D)) * 0.5 / arr[2];
                double x2 = (-arr[1] + Math.sqrt(D)) * 0.5 / arr[2];
                if (arr[2]<0){
                    double x = x1;
                    x1 = x2;
                    x2 = x;
                }
                return new double[]{x1, x2};
            } else if (D < 0) {
                return new double[]{Double.NEGATIVE_INFINITY};
            } else {
                double x = (-arr[1]) * 0.5 / arr[2];
                return new double[]{x, x};
            }
        } else {
            if (arr[1] == 0.0) {
                if (arr[0] == 0.0) {
                    return new double[]{Double.POSITIVE_INFINITY};
                } else {
                    return new double[]{};
                }
            } else {
                double x1 = -arr[0] / arr[1];
                return new double[]{x1};
            }
        }
    }

    public Polynomial[] decomposition() {
        if (arr[2] != 0) {
            if (arr[0] != 0) {
                double D = discriminant();
                if (D < 0) {
                    throw new IllegalArgumentException();
                } else {
                    double[] roots = roots();
                    if (roots.length == 0 || roots[0] == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException();
                    } else {
                        return new Polynomial[]{
                                new Polynomial(new double[]{-roots[0], 1}),
                                new Polynomial(new double[]{-roots[1], 1})};
                    }
                }
            } else {
                return new Polynomial[]{new Polynomial(new double[]{0, 1}), new Polynomial(new double[]{arr[1], arr[2]})};
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
