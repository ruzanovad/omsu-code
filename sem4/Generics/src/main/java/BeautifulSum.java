public class BeautifulSum<T extends ISegmentFunction> implements Functional<T> {
    @Override
    public double getValue(T func) {
        double A = func.getValue(func.getLeft());
        double B = func.getValue(func.getRight());
        double C = func.getValue((func.getLeft() + func.getRight()) * .5);
        return A+B+C;
    }
}
