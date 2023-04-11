public class PolynomialService {
    public static Polynomial add(final Polynomial p1, final Polynomial p2){
        int deg1 = p1.degree();
        int deg2 = p2.degree();
        double[] coefs;
        if (deg1>=deg2){
            coefs = new double[deg1+1];
            System.arraycopy(p1.arr, deg2 + 1, coefs, deg2 + 1, deg1 - deg2);
            for (int i = deg2; i >= 0; --i){
                coefs[i] = p1.arr[i] + p2.arr[i];
            }
        }
        else {
            coefs = new double[deg2+1];
            System.arraycopy(p2.arr, deg1 + 1, coefs, deg1 + 1, deg2 - deg1);
            for (int i = deg1; i >= 0; --i) {
                coefs[i] = p1.arr[i] + p2.arr[i];
            }
        }
        return new Polynomial(coefs);
    }
    public static Polynomial sub(final Polynomial p1, final Polynomial p2){
        int deg1 = p1.degree();
        int deg2 = p2.degree();
        double[] coefs;
        if (deg1>=deg2){
            coefs = new double[deg1+1];
            System.arraycopy(p1.arr, deg2 + 1, coefs, deg2 + 1, deg1 - deg2);
            for (int i = deg2; i >= 0; --i){
                coefs[i] = p1.arr[i] -p2.arr[i];
            }
        }
        else {
            coefs = new double[deg2+1];
            for (int i = deg2; i > deg1; --i){
                coefs[i] = -p2.arr[i];
            }
            for (int i = deg1; i >= 0; --i) {
                coefs[i] = -p2.arr[i] + p1.arr[i];
            }
        }
        return new Polynomial(coefs);
    }
    public static Polynomial mul(final Polynomial p1, final Polynomial p2){
        int deg1 = p1.degree();
        int deg2 = p2.degree();

        int deg = deg1 + deg2;
        double[] coefs = new double[deg+1];
        for (int i = 0; i <= deg1; ++i){
            for (int j = 0; j <= deg2; ++j){
                coefs[i+j]+=p1.arr[i]*p2.arr[j];
            }
        }
        return new Polynomial(coefs);
    }


    public static Polynomial[] div(final Polynomial p1, final Polynomial p2){
        int deg1 = p1.degree();
        int deg2 = p2.degree();
        if (deg1 < deg2)
            return new Polynomial[]{new Polynomial(new double[]{0}),new Polynomial(p1.arr)};
        Polynomial div = new Polynomial(p1.arr);
        Polynomial res = new Polynomial(new double[]{0});
        int deg = div.degree();
        int i = 0;
        while (deg >= deg2 && i <= deg1){
            Polynomial cur = new Polynomial(new double[1+deg - deg2]);
            cur.arr[deg - deg2] = div.arr[deg]/p2.arr[deg2];
            res = PolynomialService.add(res, cur);
            div = PolynomialService.sub(div, PolynomialService.mul(cur, p2));
            deg = div.degree();
            ++i;
        }
        return new Polynomial[]{res, div};
    }
}
