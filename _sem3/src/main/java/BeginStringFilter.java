public class BeginStringFilter implements Filter {
    private final String pattern;

    BeginStringFilter(String p) {
        this.pattern = p;
    }

    @Override
    public boolean apply(String str) {
        return str.startsWith(pattern);
    }
}
