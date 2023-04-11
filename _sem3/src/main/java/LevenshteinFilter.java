public class LevenshteinFilter implements Filter{
    private final String pattern;

    LevenshteinFilter(String p) {
        this.pattern = p;
    }


    private int lev(String a, String b){
        /*    Редакторским расстоянием (или расстоянием Левенштейна) между
двумя строками называется наименьшее число
операций редактирования, преобразующих первую строку во вторую. Разрешены следующие операции:
• вставка символа (например, ABC → ABCA);
• удаление символа (например, ABC → AC);
• замена символа (например, ABC → ADC).*/
        if ("".equals(a))
            return b.length();
        if ("".equals(b))
            return a.length();
        if (a.charAt(0) == b.charAt(0))
            return lev(a.substring(1), b.substring(1));
        return 1 + Integer.min(lev(a.substring(1), b.substring(1)),
                Integer.min(lev(a, b.substring(1)), lev(a.substring(1), b)));
    }
    @Override
    public boolean apply(String str) {
        return lev(str, pattern) < 10;
    }
}
