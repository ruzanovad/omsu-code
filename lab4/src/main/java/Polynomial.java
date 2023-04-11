import java.util.Arrays;

public class Polynomial {
    protected final double[] arr;

    public Polynomial(double[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException();
        }
        double[] a = new double[arr.length];
        System.arraycopy(arr, 0, a, 0, arr.length);
        this.arr = a;
    }

    public int degree() {
        if (arr.length > 1) {
            for (int i = arr.length - 1; i > 0; --i) {
                if (arr[i] != 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    public double getValue(double x) {
        double result = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            result += arr[i] * Math.pow(x, i);
        }
        return result;
    }

    public double getDerivative(int x) {
/*        double eps = 1e-9;
        return (getValue(x + eps) - getValue(x)) / eps;*/
        if (this.degree() < 1){
            return 0;
        }
        double[] f = new double[degree()];

        for (int i = f.length; i > 0; i--){
            f[i-1] = this.arr[i]* i;
        }
        return new Polynomial(f).getValue(x);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polynomial that = (Polynomial) o;
        Polynomial comp;
        int MAX = Integer.max(that.degree(), degree());
        int MIN = Integer.min(that.degree(), degree());
        if (that.degree() == MAX) {
            comp = that;
        } else {
            comp = this;
        }
        for (int i = 0; i < MIN; ++i) {
            if (that.arr[i] != arr[i]) {
                return false;
            }
        }
        for (int i = MIN; i < MAX; ++i) {
            if (comp.arr[i] != 0)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    @Override
    public String toString() {

        if (degree() > 0) {
            StringBuilder l = new StringBuilder();
            for (int i = degree(); i >= 0; --i) {
                if (Math.abs(arr[i]) > 1e-9) {
                    String x = "";
                    if (i > 1) {
                        x = String.format("x^%d", i);
                    } else if (i == 1) {
                        x = "x";
                    }
                    if (i == degree()) {
                        if (arr[i] < 0) {
                            l.append("-");
                        }
                    } else {
                        if (arr[i] < 0) {
                            l.append(" - ");
                        } else {
                            l.append(" + ");
                        }
                    }
                    if ((Math.abs(arr[i]-1) <= 1e-9 || Math.abs(arr[i]+1) <= 1e-9) && i!=0)
                    {
                        l.append(String.format("%s", x));
                    }
                    else
                    {
                        l.append(String.format("%f%s", Math.abs(arr[i]), x));
                    }

                }
            }
            return l.toString();
        }
        return Double.toString(arr[0]);
    }

}
