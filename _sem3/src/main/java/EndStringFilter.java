public class EndStringFilter implements Filter{
    private final String pattern;

    EndStringFilter(String p) {
        this.pattern = p;
    }

    @Override
    public boolean apply(String str) {
        return str.endsWith(pattern);
    }
}
